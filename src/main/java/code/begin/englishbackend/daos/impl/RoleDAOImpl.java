package code.begin.englishbackend.daos.impl;

import code.begin.englishbackend.daos.RoleDAO;
import code.begin.englishbackend.models.Role;
import code.begin.englishbackend.models.RoleName;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RoleDAOImpl extends AbstractBaseDAO implements RoleDAO {
    @Override
    public Optional<Role> findByName(RoleName roleName) {
//        logger.info("---start DAO get User by email:{}", roleName);
        Query<Role> query = getSession().createQuery("select r from Role r where r.name=:roleName", Role.class);
        query.setParameter("roleName", roleName);
        List<Role> userList = query.list();
        return userList.isEmpty() ? null : Optional.ofNullable(userList.get(0));
    }
}
