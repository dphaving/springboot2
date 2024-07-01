package org.tad.config;

import ch.qos.logback.core.db.DBHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.tad.bean.Car;
import org.tad.bean.Pet;
import org.tad.bean.User;
import org.tad.converter.TadMessageConverter;
import org.tad.interceptor.TokenInterceptor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MyConfig
 * @Description Configuration用法
 * @Author Tad
 * @Date 2024/4/21 22:36
 * @Version 1.0
 */
@Configuration(proxyBeanMethods = true)
@Import({User.class, DBHelper.class})
@ImportResource("classpath:beans.xml")
@EnableConfigurationProperties(Car.class)
public class MyConfig {

    @Bean
    public Pet miXian() {
        return new Pet("米线",10.0);
    }

    @Bean
    @ConditionalOnBean(name = "miXian")
    public User jack() {
        User jack = new User("jack", 18);
        jack.setPet(miXian());
        return jack;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>() {
                    @Override
                    public Pet convert(String source) {
                        if (!StringUtils.isEmpty(source)) {
                            String[] split = source.split(",");
                            Pet pet = new Pet();
                            pet.setName(split[0]);
                            pet.setWeight(Double.valueOf(split[1]));
                            return pet;
                        }
                        return null;
                    }
                });
            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new TadMessageConverter());
            }

            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                Map<String, MediaType> mediaTypes = new HashMap<>();
                mediaTypes.put("json", MediaType.APPLICATION_JSON);
                mediaTypes.put("xml", MediaType.APPLICATION_XML);
                mediaTypes.put("x-tad", MediaType.parseMediaType("application/x-tad"));
                ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
                HeaderContentNegotiationStrategy headerStrategy = new HeaderContentNegotiationStrategy();
                configurer.strategies(Arrays.asList(parameterStrategy, headerStrategy));
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new TokenInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/test/**");
            }
        };
    }


}
