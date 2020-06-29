package code.begin.englishbackend.daos.impl;

import code.begin.englishbackend.daos.UserDAO;
import code.begin.englishbackend.models.Role;
import code.begin.englishbackend.models.RoleName;
import code.begin.englishbackend.models.User;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserDAOImpl  extends AbstractBaseDAO implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
    @Override
    public User existsByUsername(String userName) {
        logger.info("---start DAO get User by user name:{}", userName);
        Query<User> query = getSession().createQuery("select u from User u where u.userName=:userName", User.class);
        query.setParameter("userName", userName.trim());
        List<User> userList = query.list();
        return userList.isEmpty() ? null : userList.get(0);
    }

    @Override
    public User existsByEmail(String email) {
        logger.info("---start DAO get User by email:{}", email);
        Query<User> query = getSession().createQuery("select u from User u where u.email=:email", User.class);
        query.setParameter("email", email.trim());
        List<User> userList = query.list();
        return userList.isEmpty() ? null : userList.get(0);
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        logger.info("---start DAO get User by email:{}", userName);
        Query<User> query = getSession().createQuery("select u from User u where u.userName=:userName", User.class);
        query.setParameter("userName", userName.trim());
        List<User> userList = query.list();
        return userList.isEmpty() ? null : Optional.ofNullable(userList.get(0));
    }
}
