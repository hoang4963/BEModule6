package com.codegym.webthuenha.controller.login;
import com.codegym.webthuenha.model.DTO.UserRegisterDTO;
import com.codegym.webthuenha.model.JwtResponse;
import com.codegym.webthuenha.model.User;
import com.codegym.webthuenha.service.jwt.JwtService;
import com.codegym.webthuenha.service.role.IRoleService;
import com.codegym.webthuenha.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtService.createToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.findByUsername(user.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
        } catch (Exception e) {
            return ResponseEntity.ok("Not Found User");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDTO user) {
      User users = new User();
      users.setUsername(user.getUserName());
      users.setPassword(user.getPassword());
      users.setEmail(user.getEmail());
      users.setPhoneNumber(user.getPhone());
      String role = "1";
      Long role1 = Long.parseLong(role);
      users.setRole(roleService.findById(role1).get());
      userService.save(users);
      return new ResponseEntity<>(userService.save(users),HttpStatus.OK);
    }
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return new ResponseEntity<>("Admin", HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return new ResponseEntity<>("User", HttpStatus.OK);
    }
}
