package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.DBConnectionException;
import by.teachmeskills.eshop.exceptions.ExecuteQueryException;

public interface UserRepository extends BaseRepository<User> {
    User findById(int id) throws DBConnectionException, ExecuteQueryException;

    User findByEmail(String email) throws DBConnectionException, ExecuteQueryException;

    void updatePassword(String password,String email) throws DBConnectionException;

    void updateEmail(String previousEmail,String newEmail) throws DBConnectionException;
}
