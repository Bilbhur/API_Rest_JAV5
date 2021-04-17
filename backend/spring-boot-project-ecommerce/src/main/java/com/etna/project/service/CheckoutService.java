package com.etna.project.service;

import com.etna.project.dto.Purchase;
import com.etna.project.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
