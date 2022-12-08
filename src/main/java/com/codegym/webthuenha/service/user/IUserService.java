package com.codegym.webthuenha.service.user;

import com.codegym.webthuenha.model.User;
import com.codegym.webthuenha.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);

<<<<<<< HEAD

=======
    Optional<User> checkDoubleUser(String username);
>>>>>>> acbcb3bed9d20e0115bcc222c16cfefbb14c1cdd
}