package SPMB.Parkin.controllers;

import SPMB.Parkin.enums.Roles;
import SPMB.Parkin.models.DriverInfo;
import SPMB.Parkin.models.User;
import SPMB.Parkin.models.requestBody.Credentials;
import SPMB.Parkin.payload.reponse.JwtResponse;
import SPMB.Parkin.repositories.DriverInfoRepository;
import SPMB.Parkin.repositories.UserRepository;
import SPMB.Parkin.security.jwt.JwtUtils;
import SPMB.Parkin.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static SPMB.Parkin.constants.EndpointConstants.AUTH_ENDPOINT;
import static SPMB.Parkin.constants.EndpointConstants.LOGIN_ENDPOINT;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(AUTH_ENDPOINT)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DriverInfoRepository driverInfoRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(LOGIN_ENDPOINT)
    public ResponseEntity<?> authenticateUser(@RequestBody Credentials credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userDetails.getEmail()));
        JwtResponse response = new JwtResponse(jwt, user);
        if(user.getRole() == Roles.ROLE_DRIVER) {
            DriverInfo driverInfo = driverInfoRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Driver Not Found with username: " + userDetails.getEmail()));
            response.setPlate(driverInfo.getPlate());
            response.setVehicleType(driverInfo.getVehicleType());
            response.setIsHandicap(driverInfo.getHandicap());
        }
        return ResponseEntity.ok(response);
    }

}
