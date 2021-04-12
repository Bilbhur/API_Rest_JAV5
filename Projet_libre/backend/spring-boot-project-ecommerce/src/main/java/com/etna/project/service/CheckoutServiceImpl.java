package com.etna.project.service;

import com.etna.project.dao.CustomerRepository;
import com.etna.project.dto.Purchase;
import com.etna.project.dto.PurchaseResponse;
import com.etna.project.entity.Customer;
import com.etna.project.entity.Order;
import com.etna.project.entity.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve order info from dto
        Order order = purchase.getOrder();

        // generate order number
        String orderNumber = generaOrderNumber();
        order.setOrderNumber(orderNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // save to the db
        customerRepository.save(customer);

        // return response
        return new PurchaseResponse(orderNumber);
    }

    private String generaOrderNumber() {

        return UUID.randomUUID().toString();
    }
}
