package code.begin.englishbackend.services.impl;


import code.begin.englishbackend.daos.UserDAO;
import code.begin.englishbackend.models.User;
import code.begin.englishbackend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public Boolean existsByUsername(String userName) {
        logger.info("Service to check email");
        User result = null;
        try {
            result = userDAO.existsByUsername(userName);
        } catch (Exception e) {
            logger.info(" checkUerNameExisted ---------- User not found");
        }
        return result != null;
    }

    @Override
    public Boolean existsByEmail(String email) {
        logger.info("Service to check email");
        User result = null;
        try {
            result = userDAO.existsByEmail(email);
        } catch (Exception e) {
            logger.info(" checkEmailExisted ---------- User not found");
        }
        return result != null;
    }

//    @Override
//    public Optional<User> findByUsername(String userName) {
//        return Optional.empty();
//    }
}
