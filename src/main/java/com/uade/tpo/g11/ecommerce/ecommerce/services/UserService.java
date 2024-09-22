package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.UserDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.TransactionEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.OrderMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.UserMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IOrderRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // READ
    public List<UserDTO> getAllUsers() {
        List<UserEntity> usersEntities = userRepository.findAll();

        List<UserDTO> userDTOs = usersEntities.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());

        return userDTOs;

    }

    public UserDTO getUserById(int id) {
        UserDTO userDTO = null;
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if(userEntity.isPresent()) {
            userDTO = userMapper.toDTO(userEntity.get());
        }

        return userDTO;
    }

    // CREATE
    public void createUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.toEntity(userDTO);
        userRepository.save(userEntity);
    }


    // UPDATE
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        Optional<UserEntity> existingUser = userRepository.findById(id);
        UserDTO updatedUser = null;

        if(existingUser.isPresent()) {
            UserEntity userEntity = existingUser.get();

            userEntity.setUsername(userDTO.getUsername());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setBirthDate(userDTO.getBirth());
            userEntity.setRole(userDTO.getRole());
            userEntity.setFirstname(userDTO.getFirstname());
            userEntity.setLastname(userDTO.getLastname());

            UserEntity user = userRepository.save(userEntity);
            updatedUser = userMapper.toDTO(user);
        }

        return updatedUser;
    }


    // DELETE
    public void deleteUser(Integer id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if(userEntity.isPresent()) {
            userRepository.delete(userEntity.get());
        }
    }

   /* public List<OrderDTO> getUserCheckoutHistory(Integer userId) {
        List<TransactionEntity> transactions = transactionRepository.findByUserUserId(userId);
        return transactions.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }*/

}
