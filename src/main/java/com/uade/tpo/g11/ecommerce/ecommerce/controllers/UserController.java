package com.uade.tpo.g11.ecommerce.ecommerce.controllers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.UserDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.RoleEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.TransactionEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.services.TransactionService;
import com.uade.tpo.g11.ecommerce.ecommerce.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TransactionService transactionService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // Devuelve el email (porque getUsername() lo mapea así)
        UserDTO currentUser = userService.getUserByEmail(email);

        if (!"ROLE_ADMIN".equals(currentUser.getRole().name()) && currentUser.getId() != id) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No tenés permiso para acceder a los datos de otro usuario.");
        }

        try {
            UserDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("El usuario con ID " + id + " no existe.");
        }
    }


    @PostMapping("register")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        UserDTO newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserDTO user) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // Devuelve el email (porque getUsername() lo mapea así)
        UserDTO currentUser = userService.getUserByEmail(email);

        if (!"ROLE_ADMIN".equals(currentUser.getRole().name()) && currentUser.getId() != id) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No tenés permiso para acceder a los datos de otro usuario.");
        }
        try{
            UserDTO updateUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updateUser);

        }catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("El usuario con ID " + id + " no existe.");
        }

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Se elimino correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El usuario no existe ");
        }

    }

    @GetMapping("/{userId}/transactions")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<TransactionEntity>> getUserTransactions(@PathVariable Integer userId) {
        List<TransactionEntity> transactions = transactionService.getTransactionsByUserId(userId);
        return ResponseEntity.ok(transactions);
    }
}
