package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.DBConnectionException;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product> {
    Product findById(int id) throws DBConnectionException;
    List<Product> getProductsByCategory(int id) throws DBConnectionException;
    List<Product> findProductsByKeywords(String words) throws DBConnectionException;

}
