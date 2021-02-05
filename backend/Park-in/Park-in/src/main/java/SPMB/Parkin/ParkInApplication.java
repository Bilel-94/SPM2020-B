package SPMB.Parkin;

import SPMB.Parkin.enums.Roles;
import SPMB.Parkin.models.User;
import SPMB.Parkin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ParkInApplication {

    @Autowired
    private UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ParkInApplication.class, args);
    }



}