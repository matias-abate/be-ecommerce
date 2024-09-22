package com.uade.tpo.g11.ecommerce.ecommerce.services;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDetailDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderDetailEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.OrderEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.entities.ProductEntity;
import com.uade.tpo.g11.ecommerce.ecommerce.mappers.OrderDetailMapper;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IOrderDetailRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IOrderRepository;
import com.uade.tpo.g11.ecommerce.ecommerce.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {

    @Autowired
    IOrderDetailRepository orderDetailRepository;

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    IProductRepository productRepository;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    // GET ALL
    public List<OrderDetailDTO> getAllOrderDetails() {
        List<OrderDetailEntity> orderDetailsEntity = orderDetailRepository.findAll();
        List<OrderDetailDTO> orderDetails = orderDetailsEntity.stream()
                .map(orderDetailMapper::toDTO)
                .collect(Collectors.toList());

        return orderDetails;
    }

    // GET BY ID
    public OrderDetailDTO getOrderDetailById(int id) {
        OrderDetailEntity orderDetailEntity = orderDetailRepository.findById(id).orElse(null);
        OrderDetailDTO orderDetailDTO = orderDetailMapper.toDTO(orderDetailEntity);

        return orderDetailDTO;
    }

    // CREATE
    public OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetailEntity orderDetailEntity = orderDetailMapper.toEntity(orderDetailDTO);
        orderDetailRepository.save(orderDetailEntity);

        return orderDetailDTO;
    }


    // UPDATE
    public OrderDetailDTO updateOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetailEntity orderDetailEntity = orderDetailRepository.findById(orderDetailDTO.getId()).orElse(null);

        orderDetailEntity.setOrderDetailId(orderDetailDTO.getId());
        orderDetailEntity.setUnitPrice(orderDetailDTO.getUnitPrice());
        orderDetailEntity.setQuantity(orderDetailDTO.getQuantity());
        orderDetailEntity.setTotalPrice(orderDetailDTO.getTotalPrice());

        OrderEntity orderEntity = orderRepository.findById(orderDetailDTO.getOrderId()).orElse(null);
        orderDetailEntity.setOrder(orderEntity);

        ProductEntity productEntity = productRepository.findById(orderDetailDTO.getProductId()).orElse(null);
        orderDetailEntity.setProduct(productEntity);

        orderDetailRepository.save(orderDetailEntity);
        return orderDetailDTO;
    }

    // DELETE
    public void deleteOrderDetail(int id) {
        OrderDetailEntity orderDetailEntity = orderDetailRepository.findById(id).orElse(null);
        orderDetailRepository.delete(orderDetailEntity);
    }

}
