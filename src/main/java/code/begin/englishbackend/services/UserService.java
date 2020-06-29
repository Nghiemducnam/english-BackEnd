package code.begin.englishbackend.services;

import code.begin.englishbackend.models.User;

import java.util.Optional;

public interface UserService {
    Boolean existsByUsername (String userName);
    Boolean existsByEmail (String email);
//    Optional<User> findByUsername(String userName);
}
