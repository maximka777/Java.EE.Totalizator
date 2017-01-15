package sport.totalizator.dao;

import sport.totalizator.dao.exception.DAOException;
import sport.totalizator.entity.Operation;
import sport.totalizator.entity.User;
import sport.totalizator.exception.UserException;

import java.math.BigDecimal;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws DAOException;

    User createUser(User user) throws DAOException, UserException;

    User getUserByLogin(String login) throws DAOException;

    User getFullUserInformationByLogin(String login) throws DAOException;

    int getUserIdByLogin(String login) throws DAOException;

    void fillUpBalanceForUser(int userId, BigDecimal money) throws DAOException;

    void withdrawMoneyFromUser(int userId, BigDecimal money) throws DAOException;

    boolean haveMoney(int userId, BigDecimal money) throws DAOException;

    void banUsers(List<Integer> idList) throws DAOException;

    void unbanUsers(List<Integer> idList) throws DAOException;

    void changeRoleForUsers(List<Integer> idList, String role) throws DAOException;
}
