package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDetailDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.dtos.ProductDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderDetailEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.UserEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.OrderDetailMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.ProductMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public ProductDTO getProductById(int id) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        ProductDTO product = productMapper.toDTO(productEntity);

        return product;
    }

    //READ PRODUCTO DESTACADO
    public List<ProductDTO> getFeaturedProducts() {
        List<ProductEntity> productsEntity = productRepository.findByIsFeaturedTrue();
        return productsEntity.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());


    }

    //READ PRODUCTO POR CATEGORIA
    public Map<String, List<ProductDTO>> obtenerProductosAgrupadosPorCategoria() {
        // Obtener todas las entidades de productos
        List<ProductEntity> productsEntity = productRepository.findAll();

        // Convertir las entidades a DTO usando un mapper y luego agrupar por categoría
        return productsEntity.stream()
                .map(productMapper::toDTO) // Convertir cada entidad a DTO
                .collect(Collectors.groupingBy(ProductDTO::getCategory)); // Agrupar por la categoría
    }

    // CREATE
    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productMapper.toEntity(productDTO);
       // if (productEntity.isFeatured() == null) {
         //   productEntity.isFeatured(false);
        //}
        ProductEntity savedProduct= productRepository.save(productEntity);

        return productMapper.toDTO(savedProduct);
    }

    // UPDATE
    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        ProductEntity existingProduct = productRepository.findById(productDTO.getId()).orElse(null);
        productMapper.updateEntityFromDTO(productDTO, existingProduct);
        if (existingProduct == null) {
            return null;
        } else {
            ProductEntity updatedProduct = productRepository.save(existingProduct);
            return productMapper.toDTO(updatedProduct);
        }



    }

//        productEntity.setProductId(productDTO.getId());
//        productEntity.setName(productDTO.getName());
//        productEntity.setDescription(productDTO.getDescription());
//        productEntity.setImages(productDTO.getImages());
//        productEntity.setPrice(productDTO.getPrice());
//        productEntity.setStock(productDTO.getStock());
//        productEntity.setCategory(productDTO.getCategory());
//
//        List<OrderDetailEntity> orderDetails = productDTO.getOrderDetails().stream()
//                        .map(orderDetailMapper::toEntity)
//                        .collect(Collectors.toList());
//
//        productEntity.setOrderDetails(orderDetails);
//
//        productEntity = productRepository.save(productEntity);
//
//        return productMapper.toDTO(productEntity);
//    }

        // DELETE
        public void deleteProduct ( int id){
            ProductEntity productEntity = productRepository.findById(id).orElse(null);
            productRepository.delete(productEntity);
        }
    }