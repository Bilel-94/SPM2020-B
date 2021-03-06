package SPMB.Parkin.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/driver")
    @PreAuthorize("hasRole('DRIVER') or hasRole('ADMIN')")
    public String userAccess() {
        return "Driver Content.";
    }

    @GetMapping("/parking_manager")
    @PreAuthorize("hasRole('PARKING_MANAGER')")
    public String moderatorAccess() {
        return "Parking Manager Only.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Only.";
    }
}