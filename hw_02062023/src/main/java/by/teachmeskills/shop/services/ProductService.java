package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.exceptions.DBConnectionException;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    Product findById(int id) throws DBConnectionException;

    List<Product> getProductsByCategory(int id) throws DBConnectionException;
}
