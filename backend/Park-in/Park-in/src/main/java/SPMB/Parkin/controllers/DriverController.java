package SPMB.Parkin.controllers;

import SPMB.Parkin.enums.Roles;
import SPMB.Parkin.models.Driver;
import SPMB.Parkin.models.DriverInfo;
import SPMB.Parkin.models.User;
import SPMB.Parkin.repositories.DriverInfoRepository;
import SPMB.Parkin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.IOException;

import static SPMB.Parkin.constants.EndpointConstants.DRIVER_ENDPOINT;
import static SPMB.Parkin.constants.EndpointConstants.DRIVER_REGISTRATION_ENDPOINT;

@RestController
@RequestMapping(DRIVER_ENDPOINT)
@CrossOrigin(origins = "*", maxAge = 3600)
public class DriverController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private DriverInfoRepository driverRepository;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping(path = DRIVER_REGISTRATION_ENDPOINT, consumes = "application/json")
    public ResponseEntity registration(@Valid @RequestBody Driver driver) throws IOException {

        if (!repository.existsByUsername(driver.getEmail())) {
            repository.save(new User(driver.getUsername(), driver.getFirstName(), driver.getLastName(), driver.getSsn(),
                    driver.getPhone(), driver.getEmail(), encoder.encode(driver.getPassword()), Roles.ROLE_DRIVER));
            driverRepository.save(new DriverInfo(driver));
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

    }
}