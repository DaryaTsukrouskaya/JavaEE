package by.teachmeskills.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Data
@ToString
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private String imageName;
    private String category;
    private BigDecimal price;
}