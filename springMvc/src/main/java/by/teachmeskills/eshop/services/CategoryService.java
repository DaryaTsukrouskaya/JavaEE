package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.exceptions.DBConnectionException;

public interface CategoryService extends BaseService<Category> {
    Category findById(int id) throws DBConnectionException;
}
