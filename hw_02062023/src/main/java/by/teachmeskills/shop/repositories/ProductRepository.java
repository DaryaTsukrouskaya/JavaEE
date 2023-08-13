package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.exceptions.DBConnectionException;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product> {
    Product findById(int id) throws DBConnectionException;
    List<Product> getProductsByCategory(int id) throws DBConnectionException;
    List<Product> findProductsByKeywords(String words) throws DBConnectionException;

}
