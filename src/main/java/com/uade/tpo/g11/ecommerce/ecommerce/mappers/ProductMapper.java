package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDetailDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ProductDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.UserDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderDetailEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    OrderDetailMapper orderDetailMapper;

    public ProductDTO toDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getProductId());
        productDTO.setName(productEntity.getName());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setImages(productEntity.getImages()); // Sin transformación
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setStock(productEntity.getStock());
        productDTO.setCategory(productEntity.getCategory());
        productDTO.setFeatured(productEntity.isFeatured());

        List<OrderDetailDTO> orderDetailsDTO = productEntity.getOrderDetails().stream()
                .map(orderDetailMapper::toDTO)
                .collect(Collectors.toList());
        productDTO.setOrderDetails(orderDetailsDTO);

        return productDTO;
    }


    public ProductEntity toEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setImages(productDTO.getImages()); // Sin transformación
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setStock(productDTO.getStock());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setFeatured(productDTO.isFeatured());

        List<OrderDetailEntity> orderDetailEntities = productDTO.getOrderDetails().stream()
                .map(orderDetailMapper::toEntity)
                .collect(Collectors.toList());
        productEntity.setOrderDetails(orderDetailEntities);

        return productEntity;
    }


    public void updateEntityFromDTO(ProductDTO productDTO, ProductEntity productEntity) {
        productEntity.setProductId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setImages(productDTO.getImages());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setStock(productDTO.getStock());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setFeatured(productDTO.isFeatured());
        List<OrderDetailEntity> orderDetails = productDTO.getOrderDetails().stream()
                        .map(orderDetailMapper::toEntity)
                        .collect(Collectors.toList());

        productEntity.setOrderDetails(orderDetails);


    }



}
