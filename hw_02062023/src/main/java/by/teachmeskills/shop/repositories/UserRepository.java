package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;

public interface UserRepository extends BaseRepository<User> {
    User findById(int id) throws DBConnectionException, ExecuteQueryException;

    User findByEmail(String email) throws DBConnectionException, ExecuteQueryException;

    void updatePassword(String password,String email) throws DBConnectionException;

    void updateEmail(String previousEmail,String newEmail) throws DBConnectionException;
}
