package org.tad.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName ResPerson
 * @Description 测试返回值：内容协商
 * @Author Tad
 * @Date 2024/5/26 16:38
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResPerson {
    private String name;
    private Integer age;
    private Date birthday;
}
