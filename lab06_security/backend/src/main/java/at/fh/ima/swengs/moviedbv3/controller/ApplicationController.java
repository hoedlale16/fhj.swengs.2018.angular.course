package at.fh.ima.swengs.moviedbv3.controller;

import at.fh.ima.swengs.moviedbv3.model.Role;
import at.fh.ima.swengs.moviedbv3.model.RoleRepository;
import at.fh.ima.swengs.moviedbv3.model.User;
import at.fh.ima.swengs.moviedbv3.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Controller
public class ApplicationController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository userRoleRepo;

    @Autowired
    private BCryptPasswordEncoder passwortEncoder;

    @RequestMapping(value="/prepareApp", method = RequestMethod.GET)
    public String preparePlavent(Model model, UriComponentsBuilder ucBuilder) {

        // Create useroles if required
        Optional<Role> roleAdmin = userRoleRepo.findById("ADMIN");
        if ( ! roleAdmin.isPresent()) {
            Role role = new Role("ADMIN");
            userRoleRepo.save(role);
        }

        Optional<Role>  roleUser = userRoleRepo.findById("USER");
        if ( ! roleUser.isPresent() ) {
            Role role = new Role("USER");
            userRoleRepo.save(role);
        }

        // Create overall admin if required
        if ( ! userRepo.findById("admin").isPresent() ) {
            Role role = userRoleRepo.findById("ADMIN").get();
            User administrator = new User("admin",passwortEncoder.encode("12345"), "Hugo","Boss",role);
            userRepo.save(administrator);
        }

        // Create a host user if required
        if (! userRepo.findById("tester").isPresent()) {
            Role role = userRoleRepo.findById("USER").get();
            User tester = new User("tester", passwortEncoder.encode("12345"),"Kevin","Klein", role);
            userRepo.save(tester);
        }

        return "setupCompleted";
    }
}
