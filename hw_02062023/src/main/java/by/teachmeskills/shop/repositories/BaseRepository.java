package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.BaseEntity;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.UserAlreadyExistsException;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {
    ConnectionPool pool = ConnectionPool.getInstance();

    void create(T entity) throws DBConnectionException, UserAlreadyExistsException;

    void delete(int id) throws DBConnectionException;

    List<T> read() throws DBConnectionException;

}
