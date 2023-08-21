package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.exceptions.DBConnectionException;

public interface CategoryRepository extends BaseRepository<Category> {
    Category findById(int id) throws DBConnectionException;
}
