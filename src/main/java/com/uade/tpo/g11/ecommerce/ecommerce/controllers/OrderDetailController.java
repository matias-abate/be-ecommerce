package com.uade.tpo.g11.ecommerce.ecommerce.controllers;

import com.uade.tpo.g11.ecommerce.ecommerce.dtos.OrderDetailDTO;
import com.uade.tpo.g11.ecommerce.ecommerce.services.OrderDetailService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<OrderDetailDTO>> getAllOrderDetails() {
        List<OrderDetailDTO> orderDetails = orderDetailService.getAllOrderDetails();
        return ResponseEntity.ok(orderDetails);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> getOrderDetailById(@PathVariable Integer id) {
        OrderDetailDTO orderDetailDTO = orderDetailService.getOrderDetailById(id);
        return ResponseEntity.ok(orderDetailDTO);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<OrderDetailDTO> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailDTO orderDetailDTOCreated = orderDetailService.createOrderDetail(orderDetailDTO);
        return ResponseEntity.ok(orderDetailDTOCreated);
    }

    // UPDATE
    @PutMapping
    public ResponseEntity<OrderDetailDTO> updateOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailDTO orderDetailDTOUpdated = orderDetailService.updateOrderDetail(orderDetailDTO);
        return ResponseEntity.ok(orderDetailDTOUpdated);
    }

    // DELETE
    @DeleteMapping
    public ResponseEntity<Void> deleteOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        orderDetailService.deleteOrderDetail(orderDetailDTO.getId());
        return ResponseEntity.noContent().build();
    }

}
