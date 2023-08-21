package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.BaseEntity;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.UserAlreadyExistsException;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {
    ConnectionPool pool = ConnectionPool.getInstance();

    void create(T entity) throws DBConnectionException, UserAlreadyExistsException;

    void delete(int id) throws DBConnectionException;

    List<T> read() throws DBConnectionException;

}

