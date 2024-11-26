package com.uade.tpo.g11.ecommerce.ecommerce.repositories;

import com.uade.tpo.g11.ecommerce.ecommerce.entities.WishlistItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWishlistItemRepository extends JpaRepository<WishlistItemEntity, Integer> {
    boolean existsByWishlistWishlistIdAndProductProductId(Integer wishlistId, Integer productId);

}
