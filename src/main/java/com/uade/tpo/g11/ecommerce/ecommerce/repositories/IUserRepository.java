package com.uade.tpo.g11.ecommerce.ecommerce.repositories;

import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

   // static boolean existsByEmail(String email) {
    //return true;
    //}
}
