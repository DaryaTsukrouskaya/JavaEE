package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.BaseEntity;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.UserAlreadyExistsException;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    List<T> read() throws DBConnectionException;

    void create(T entity) throws DBConnectionException, UserAlreadyExistsException;

    void delete(int id) throws DBConnectionException;
}
