package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.Category;
import by.teachmeskills.shop.exceptions.DBConnectionException;

public interface CategoryService extends BaseService<Category> {
    Category findById(int id) throws DBConnectionException;
}
