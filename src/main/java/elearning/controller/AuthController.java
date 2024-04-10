package elearning.controller;
import elearning.dto.request.UserLogin;
import elearning.dto.response.JwtResponse;
import elearning.exception.CustomException;
import elearning.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> handleLogin(@RequestBody UserLogin userLogin) throws CustomException {
        return new ResponseEntity<>(userService.login(userLogin), HttpStatus.OK);
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> handleRegister(@RequestBody UserRegister userRegister) {
//        userService.register(userRegister);
//        return new ResponseEntity<>("success",HttpStatus.CREATED);
//    }

//    @PostMapping("/refreshToken")
//    public ResponseEntity<JwtResponse> handleRefreshToken(HttpServletRequest request, HttpServletResponse response) {
//        return new ResponseEntity<>(userService.handleRefreshToken(request, response), HttpStatus.OK);
//    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> handleLogout(Authentication authentication) {
        return new ResponseEntity<>(userService.handleLogout(authentication), HttpStatus.OK);
    }

}