package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.entities.Category;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.shop.repositories.CategoryRepository;
import by.teachmeskills.shop.repositories.impl.CategoryRepositoryImpl;
import by.teachmeskills.shop.services.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    @Override
    public List<Category> read() throws DBConnectionException {
        return categoryRepository.read();
    }

    @Override
    public void create(Category category) throws DBConnectionException, UserAlreadyExistsException {
        categoryRepository.create(category);
    }

    @Override
    public void delete(int id) throws DBConnectionException {
        categoryRepository.delete(id);
    }

    @Override
    public Category findById(int id) throws DBConnectionException {
        return categoryRepository.findById(id);
    }
}
