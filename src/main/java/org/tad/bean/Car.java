package org.tad.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName Car
 * @Description TODO
 * @Author Tad
 * @Date 2024/4/22 22:41
 * @Version 1.0
 */
//@Component
@Data
@ToString
@ConfigurationProperties(prefix = "my-car" )
public class Car {
    private String brand;
    private Integer price;
}
