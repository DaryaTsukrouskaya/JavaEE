package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.shop.repositories.ProductRepository;
import by.teachmeskills.shop.repositories.impl.ProductRepositoryImpl;
import by.teachmeskills.shop.services.ProductService;

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
}
