package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ProductDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderDetailEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.exceptions.ResourceNotFoundException;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.OrderDetailMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.ProductMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    IProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    // READ ALL
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> productsEntity = productRepository.findAll();
        List<ProductDTO> products = productsEntity.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

        return products;
    }

    // READ BY ID
    public ProductDTO getProductById(int id) throws ResourceNotFoundException {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        if (!productEntity.isPresent()) {
            throw new ResourceNotFoundException("Product not found");
        } else {
            ProductDTO product = productMapper.toDTO(productEntity.get());
            return product;
        }


    }

    // CREATE
    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productMapper.toEntity(productDTO);
        productEntity = productRepository.save(productEntity);

        return productMapper.toDTO(productEntity);
    }

    // UPDATE
    public ProductDTO updateProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productRepository.findById(productDTO.getId()).orElse(null);

        if (productEntity == null) {
            return null;
        }

        productEntity.setProductId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setImages(productDTO.getImages());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setStock(productDTO.getStock());
        productEntity.setCategory(productDTO.getCategory());

        List<OrderDetailEntity> orderDetails = productDTO.getOrderDetails().stream()
                        .map(orderDetailMapper::toEntity)
                        .collect(Collectors.toList());

        productEntity.setOrderDetails(orderDetails);

        productEntity = productRepository.save(productEntity);

        return productMapper.toDTO(productEntity);
    }

    // DELETE
    public void deleteProduct(int id) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        productRepository.delete(productEntity);
    }
}
