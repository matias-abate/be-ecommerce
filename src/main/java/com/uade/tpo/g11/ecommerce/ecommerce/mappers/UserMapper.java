package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.UserDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(userEntity.getUserId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setBirth(userEntity.getBirthDate());
        userDTO.setFirstname(userEntity.getFirstname());
        userDTO.setLastname(userEntity.getLastname());
        userDTO.setRole(userEntity.getRole());

        return userDTO;
    }

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(userDTO.getUsername());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setBirthDate(userDTO.getBirth());
        userEntity.setFirstname(userDTO.getFirstname());
        userEntity.setLastname(userDTO.getLastname());
        userEntity.setRole(userDTO.getRole());

        return  userEntity;
    }

    public void updateEntityFromDTO(UserDTO userDTO, UserEntity userEntity) {
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setBirthDate(userDTO.getBirth());
        userEntity.setFirstname(userDTO.getFirstname());
        userEntity.setLastname(userDTO.getLastname());
        userEntity.setRole(userDTO.getRole());

        if (!userDTO.getPassword().equals(userEntity.getPassword())) {
            userEntity.setPassword(userDTO.getPassword()); // El hashing se debe hacer en el service
        }

    }

}
