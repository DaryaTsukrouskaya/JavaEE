package by.teachmeskills.eshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Order extends BaseEntity {
    private BigDecimal price;
    private LocalDate orderDate;
    private int userId;
    private String address;
    private List<Product> products;

    public Order(int id, LocalDate orderDate, List<Product> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.products = products;

    }
}
