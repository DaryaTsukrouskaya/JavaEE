package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.eshop.repositories.ProductRepository;
import by.teachmeskills.eshop.repositories.impl.ProductRepositoryImpl;
import by.teachmeskills.eshop.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public List<Product> read() throws DBConnectionException {
        return productRepository.read();
    }

    @Override
    public void create(Product product) throws DBConnectionException, UserAlreadyExistsException {
        productRepository.create(product);

    }

    @Override
    public void delete(int id) throws DBConnectionException {
        productRepository.delete(id);
    }

    @Override
    public Product findById(int id) throws DBConnectionException {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductsByCategory(int id) throws DBConnectionException {
        return productRepository.getProductsByCategory(id);
    }

    @Override
    public List<Product> findProductsByKeywords(String words) throws DBConnectionException {
        return productRepository.findProductsByKeywords(words);
    }
}
