package com.example.ktpm_tuan6.listener;

import com.example.ktpm_tuan6.entities.Product;
import com.example.ktpm_tuan6.entities.Product_order;
import com.example.ktpm_tuan6.repositories.ProductOrderRepository;
import com.example.ktpm_tuan6.repositories.ProductRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Optional;

@Component
public class ProductOrderListener {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @JmsListener(destination = "order_products")
    public void receiveMessage(final Message jsonMessage) throws JMSException {
        if (jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) jsonMessage;
            String messageData = textMessage.getText();

            Gson gson = new Gson();
            Product_order order = gson.fromJson(messageData, Product_order.class);

            // Check product availability
            Optional<Product> optionalProduct = productRepository.findById(Long.valueOf(order.getId()));
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                if (product.getQuantity() >= order.getQuantity()) {
                    // Sufficient quantity available, process order
                    product.setQuantity(product.getQuantity() - order.getQuantity());
                    productRepository.save(product);

                    // Save order
                    productOrderRepository.save(order);

                    // Send email confirmation to customer
                    sendEmailConfirmation(order.getCustomerName(), "Order processed successfully");
                } else {
                    // Insufficient quantity, reject order
                    sendEmailConfirmation(order.getCustomerName(), "Order rejected due to insufficient quantity");
                }
            } else {
                // Product not found, reject order
                sendEmailConfirmation(order.getCustomerName(), "Order rejected, product not found");
            }
        }
    }

    private void sendEmailConfirmation(String customerName, String message) {
        // Logic to send email
    }
}
