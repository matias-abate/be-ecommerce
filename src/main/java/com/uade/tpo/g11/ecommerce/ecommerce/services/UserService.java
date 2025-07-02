package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ProductDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.UserDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.RoleEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.exceptions.UserNotFoundException;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.UserMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    // READ
    // READ - Obtener solo usuarios con rol ADMIN
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = (List<UserEntity>) userRepository.findAll();
        if (userEntities.isEmpty()) {
            throw new UserNotFoundException("No se encontraron usuarios");
        }

        List<UserDTO> userDTOs = userEntities.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());

        return userDTOs;
    }



    public UserDTO getUserById(int id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            System.out.println("Usuario encontrado: " + userEntity.get().getUsername());
            return userMapper.toDTO(userEntity.get());
        } else {
            System.out.println("Usuario NO encontrado con ID: " + id);
            return null; // o lanzá excepción como te recomendé antes
        }
    }

    // CREATE
    public UserDTO createUser(UserDTO userDTO) {
        try {
            UserEntity userEntity = userMapper.toEntity(userDTO);
            UserEntity savedUser = userRepository.save(userEntity);
            return userMapper.toDTO(savedUser);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Username: '" + userDTO.getUsername() + "' ya existe");
        }
    }



    // UPDATE
    public UserDTO updateUser(int id, UserDTO userDTO) {
        UserEntity existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateEntityFromDTO(userDTO, existingUser);
        UserEntity updatedUser = userRepository.save(existingUser);
        return userMapper.toDTO(updatedUser);
    }
//    public UserDTO updateUser(Integer id, UserDTO userDTO) {
//
//        Optional<UserEntity> existingUser = userRepository.findById(id);
//        UserDTO updatedUser = null;
//
//        if(existingUser.isPresent()) {
//            UserEntity userEntity = existingUser.get();
//
//            userEntity.setUsername(userDTO.getUsername());
//            userEntity.setEmail(userDTO.getEmail());
//            userEntity.setBirthDate(userDTO.getBirth());
//            userEntity.setRole(userDTO.getRole());
//            userEntity.setFirstname(userDTO.getFirstname());
//            userEntity.setLastname(userDTO.getLastname());
//
//            UserEntity user = userRepository.save(userEntity);
//            updatedUser = userMapper.toDTO(user);
//        }
//
//        return updatedUser;
//    }


    // DELETE
    public void deleteUser(Integer id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    public UserDTO getUserByUsername(String username) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);

        if (userEntityOptional.isPresent()) {
            return userMapper.toDTO(userEntityOptional.get());
        } else {
            throw new UserNotFoundException("Usuario no encontrado con username: " + username);
        }
    }

    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }
}