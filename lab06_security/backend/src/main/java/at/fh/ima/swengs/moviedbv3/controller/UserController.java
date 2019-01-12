package at.fh.ima.swengs.moviedbv3.controller;

import at.fh.ima.swengs.moviedbv3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwortEncoder;


    // ----------------------------------------------------------------------------
    @RequestMapping(value="/users", method = RequestMethod.GET)
    // ----------------------------------------------------------------------------
    ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users= userRepository.findAll();

        //If no entities found , return NO_CONTENT
        if(users == null || ! users.iterator().hasNext() )
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        //Otherwhise return OK and content
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    // ----------------------------------------------------------------------------
    @RequestMapping(value="/users/{username}", method = RequestMethod.GET)
    // ----------------------------------------------------------------------------
    ResponseEntity<User> getUser(@PathVariable String username) {

        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent())
            return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // ----------------------------------------------------------------------------
    @RequestMapping(value="/users/{username}", method = RequestMethod.DELETE)
    // ----------------------------------------------------------------------------
    ResponseEntity<User> deleteUser(@PathVariable String username) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent()) {
            userRepository.deleteById(username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // ----------------------------------------------------------------------------
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    // ----------------------------------------------------------------------------
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

        if(user.getRole() == null ||user.getRole().getRoleName().isEmpty()) {
            //If role not given, set default USER Role
            Role userRole = roleRepository.findById("USER").get();
            user.setRole(userRole);
        }

        //Encrypt password if set
        String password = user.getPassword();
        if(password != null)
            //If password is set, set send one
            user.setPassword(passwortEncoder.encode(password));
        else {
            //Otherwise set default password.
            //Password should not send via REST/JSN. That's why I would set @JsonIgnore for password attribute.
            //In my opinion this is a security issue, that passwords can send via REST-Calls
            user.setPassword(passwortEncoder.encode("12345"));
        }

        //Save user
        userRepository.save(user);
        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(ucBuilder.path("/users/{username}").buildAndExpand(user.getUsername()).toUri());
        return new ResponseEntity<User>(headers, HttpStatus.CREATED); //201: Created
    }

    // ----------------------------------------------------------------------------
    @RequestMapping(value = "/users/{username}", method = RequestMethod.PUT)
    // ----------------------------------------------------------------------------
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User userUpdate) {
        Optional<User> optUser = userRepository.findById(username);
        if(! optUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //Set correct username if not set (Primary Key)
        userUpdate.setUsername(username);

        //If new passwort is send encrypt it
        String password;
        if(userUpdate.getPassword() == null || userUpdate.getPassword().isEmpty()) {
            //no password change, take old encrypted one
            password = optUser.get().getPassword();
        }else {
            //New password set, encrypt and store
            password = passwortEncoder.encode(userUpdate.getPassword());
        }
        userUpdate.setPassword(password);

        //Save(Update) data
        userRepository.save(userUpdate);

        //Return OK but no furhter body content.
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
