package code.begin.englishbackend.daos;

import code.begin.englishbackend.models.Role;
import code.begin.englishbackend.models.RoleName;

import java.util.Optional;

public interface RoleDAO {
    Optional<Role> findByName(RoleName roleName);
}
