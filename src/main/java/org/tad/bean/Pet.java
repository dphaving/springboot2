package org.tad.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Pet
 * @Description 宠物
 * @Author Tad
 * @Date 2024/4/21 22:38
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String name;
    private Double weight;
}
