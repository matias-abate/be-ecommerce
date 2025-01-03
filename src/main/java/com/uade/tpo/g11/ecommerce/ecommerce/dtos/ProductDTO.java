package com.uade.tpo.g11.ecommerce.ecommerce.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {

    private int id;
    private String name;
    private String description;
    private String images;
    private float price;
    private int stock;
    private String category;
    private List<OrderDetailDTO> orderDetails = new ArrayList<>();;
    private boolean isFeatured;
    private boolean isVisto;


}
