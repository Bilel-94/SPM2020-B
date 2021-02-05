package SPMB.Parkin.controllers;

import SPMB.Parkin.models.Driver;
import SPMB.Parkin.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class DriverController {




    @Autowired
    private DriverRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")


    @PostMapping(path="api/registration",consumes = "application/json" )
    public void registration(@Valid @RequestBody Driver driver) throws IOException {
        repository.save(driver);
    }
}
