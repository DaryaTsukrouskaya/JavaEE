package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import by.teachmeskills.eshop.repositories.impl.CategoryRepositoryImpl;
import by.teachmeskills.eshop.services.CategoryService;

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
