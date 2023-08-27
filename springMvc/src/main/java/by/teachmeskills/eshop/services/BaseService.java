package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.BaseEntity;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    List<T> read() throws DBConnectionException;

    void create(T entity) throws DBConnectionException, UserAlreadyExistsException;

    void delete(int id) throws DBConnectionException;
}
