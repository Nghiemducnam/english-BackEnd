package code.begin.englishbackend.controllers;

import javax.validation.Valid;

import code.begin.englishbackend.daos.RoleDAO;
import code.begin.englishbackend.daos.UserDAO;
import code.begin.englishbackend.message.request.Login;
import code.begin.englishbackend.message.request.SignUp;
import code.begin.englishbackend.message.response.JwtResponse;
import code.begin.englishbackend.message.response.ResponseMessage;
import code.begin.englishbackend.models.Role;
import code.begin.englishbackend.models.RoleName;
import code.begin.englishbackend.models.User;
import code.begin.englishbackend.securities.jwt.JwtProvider;
import code.begin.englishbackend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
    private static final Logger logger = LoggerFactory.getLogger(AuthRestAPIs.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUp signUpRequest) {
        logger.info("RESET : {}", signUpRequest);
        if (userService.existsByUsername(signUpRequest.getUserName())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User( signUpRequest.getUserName(), signUpRequest.getEmail(), signUpRequest.getPhoneNumber(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleDAO.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "pm":
                    Role pmRole = roleDAO.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    Role userRole = roleDAO.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userDAO.save(user);


        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }
}
