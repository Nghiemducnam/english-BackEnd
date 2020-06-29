package code.begin.englishbackend.daos;

import code.begin.englishbackend.models.Role;
import code.begin.englishbackend.models.RoleName;
import code.begin.englishbackend.models.User;

import java.util.Optional;

public interface UserDAO extends BaseDAO{
    User existsByUsername (String userName);
    User existsByEmail (String email);
    Optional<User> findByUsername(String userName);
}
