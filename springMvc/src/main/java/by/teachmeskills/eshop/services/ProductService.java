package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.DBConnectionException;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    Product findById(int id) throws DBConnectionException;

    List<Product> getProductsByCategory(int id) throws DBConnectionException;
    List<Product> findProductsByKeywords(String words) throws DBConnectionException;
}
