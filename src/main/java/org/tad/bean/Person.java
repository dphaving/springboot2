package org.tad.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName Person
 * @Description yaml配置
 * @Author Tad
 * @Date 2024/4/26 10:39
 * @Version 1.0
 */
@Data
@Component
@ToString
@ConfigurationProperties(prefix = "person")
public class Person {
    private String userName;
    private Boolean boos;
    private Date birth;
    private Integer age;
    private Pet pet;
    private String[] interests;
    private List<String> animal;
    private Map<String, Object> score;
    private Set<Double> salaries;
    private Map<String, List<Pet>> allPets;
}
