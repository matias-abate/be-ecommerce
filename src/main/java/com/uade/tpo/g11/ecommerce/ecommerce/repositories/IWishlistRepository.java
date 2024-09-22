package com.uade.tpo.g11.ecommerce.ecommerce.repositories;

import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWishlistRepository extends JpaRepository<WishlistEntity, Integer> {

    // Find wishlist by user id
    WishlistEntity findByUserUserId(Integer userId);

}
