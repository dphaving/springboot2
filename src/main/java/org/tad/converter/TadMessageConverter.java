package org.tad.converter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.tad.bean.ResPerson;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @ClassName TadMessageConverter
 * @Description 自定义MessageConverter
 * @Author Tad
 * @Date 2024/5/26 20:20
 * @Version 1.0
 */
public class TadMessageConverter implements HttpMessageConverter<ResPerson> {
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(ResPerson.class);
    }

    /**
     * 服务器能支持哪种自定义媒体类型：
     *  application/x-tad
     * @return
     */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return MediaType.parseMediaTypes("application/x-tad");
    }

    @Override
    public ResPerson read(Class<? extends ResPerson> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(ResPerson resPerson, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        //自定义协议数据的写出
        String data = resPerson.getName() + "; " + resPerson.getAge() + "; " + resPerson.getBirthday() + ";";
        OutputStream body = outputMessage.getBody();
        body.write(data.getBytes());
    }
}
