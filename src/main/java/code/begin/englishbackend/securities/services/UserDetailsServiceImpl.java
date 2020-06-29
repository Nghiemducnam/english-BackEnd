package code.begin.englishbackend.securities.services;

import code.begin.englishbackend.daos.UserDAO;
import code.begin.englishbackend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userDAO.findByUsername(userName).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + userName));

        return UserPrinciple.build(user);
    }
}
