package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.exceptions.DBConnectionException;
import by.teachmeskills.shop.exceptions.ExecuteQueryException;

public interface UserService extends BaseService<User>{
    User findById(int id) throws ExecuteQueryException, DBConnectionException;

    User findByEmail(String email) throws ExecuteQueryException, DBConnectionException;

    void updatePassword(String password,String email) throws DBConnectionException;

    void updateEmail(String previousEmail,String newEmail) throws DBConnectionException;
}
