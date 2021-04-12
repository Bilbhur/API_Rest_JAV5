package com.etna.project.dto;

import com.etna.project.entity.Address;
import com.etna.project.entity.Customer;
import com.etna.project.entity.Order;
import com.etna.project.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
