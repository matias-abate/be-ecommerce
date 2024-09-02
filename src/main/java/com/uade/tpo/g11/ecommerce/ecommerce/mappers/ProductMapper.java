package com.uade.tpo.g11.ecommerce.ecommerce.mappers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDetailDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ProductDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderDetailEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
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
        productDTO.setImages(productEntity.getImages());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setStock(productEntity.getStock());
        productDTO.setCategory(productEntity.getCategory());

        List<OrderDetailDTO> orderDetailsDTO = productEntity.getOrderDetails().stream()
                .map(orderDetailMapper::toDTO)
                .collect(Collectors.toList());

        productDTO.setOrderDetails(orderDetailsDTO);

        return productDTO;

    }

    public ProductEntity toEntity(ProductDTO productDTO) {

        

    }

}
