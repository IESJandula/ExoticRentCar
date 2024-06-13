package com.ajb.exoticrentcar.controller;

import com.ajb.exoticrentcar.dtos.AuthRequest;
import com.ajb.exoticrentcar.dtos.PasswordUpdateRequest;
import com.ajb.exoticrentcar.entitie.User;
import com.ajb.exoticrentcar.repository.UserRepository;
import com.ajb.exoticrentcar.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityService securityService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, SecurityService securityService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityService = securityService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        try {
            List<User> users = userRepository.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username, Authentication authentication) {
        try {
            // Buscar el usuario por el username
            User userFind = userRepository.findByUsername(username);

            // Verificar si el usuario autenticado es el propietario del recurso
            if (!securityService.isOwner(authentication, userFind.getId())) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            Optional<User> optionalUser = userRepository.findById(userFind.getId());

            return optionalUser.map(user -> {
                // Crea una nueva instancia de User sin la contraseña
                User userWithoutPassword = new User();
                userWithoutPassword.setId(user.getId());
                userWithoutPassword.setEmail(user.getEmail());
                userWithoutPassword.setUsername(user.getUsername());
                userWithoutPassword.setName(user.getName());
                userWithoutPassword.setLastName(user.getLastName());
                userWithoutPassword.setAge(user.getAge());
                userWithoutPassword.setRol(user.getRol());

                return new ResponseEntity<>(userWithoutPassword, HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) {
        // Crear un objeto UsernamePasswordAuthenticationToken con el username y password proporcionados
        Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

        try {
            // Intentar autenticar usando el AuthenticationManager
            authentication = authenticationManager.authenticate(authentication);

            // Si la autenticación es exitosa, significa que el username y password son válidos
            return ResponseEntity.ok("Autenticación exitosa");
        } catch (Exception e) {
            // Si la autenticación falla, devolver una respuesta de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticación fallida");
        }
    }

    @PostMapping("/users/create")
    public ResponseEntity<Void> create(@RequestBody User user) {
        try {
            // Loguear la información del usuario recibido
            logger.info("Received user: {}", user);
            if (user.getId() != null || user.getAge() == null || user.getAge()<18 || user.getName() == null || user.getLastName() == null || user.getPassword() == null || user.getUsername() == null
                    || user.getEmail() == null || !user.getRol().equals("USER")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Verificar si el nombre de usuario ya existe en la base de datos
            if (userRepository.existsByUsername(user.getUsername())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT); // Nombre de usuario ya existe
            }

            // Hasheamos la contraseña antes de guardarla
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

            userRepository.save(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        try {
            // Verificar la autenticación del usuario antes de actualizar la contraseña
            Authentication authentication = new UsernamePasswordAuthenticationToken(passwordUpdateRequest.getUsername(), passwordUpdateRequest.getOldPassword());
            authentication = authenticationManager.authenticate(authentication);

            // Si la autenticación es exitosa, proceder con la actualización de la contraseña
            User user = userRepository.findByUsername(passwordUpdateRequest.getUsername());
            if (user != null) {
                // Hasheamos la nueva contraseña antes de guardarla
                String hashedNewPassword = passwordEncoder.encode(passwordUpdateRequest.getNewPassword());
                user.setPassword(hashedNewPassword);

                userRepository.save(user);
                return ResponseEntity.ok("Contraseña actualizada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticación fallida");
        }
    }
}
