package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.Category;
import by.teachmeskills.shop.exceptions.DBConnectionException;

public interface CategoryRepository extends BaseRepository<Category> {
    Category findById(int id) throws DBConnectionException;
}
