package com.example.ktpm_tuan6;

import com.google.gson.Gson;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;

@Component
public class ProductOrderListener {

    @JmsListener(destination = "order_products")
    @SendTo("order_responses")
    public String receiveMessage(final Message jsonMessage) throws JMSException {
        String messageData = null;
        String response = null;
        if (jsonMessage instanceof TextMessage) {
            // Đọc dữ liệu tin nhắn và giải mã JSON
            TextMessage textMessage = (TextMessage) jsonMessage;
            messageData = textMessage.getText();
            Gson gson = new Gson();
            Map<String, Object> orderData = gson.fromJson(messageData, Map.class);

            // Thực hiện xử lý đặt hàng
            // Kiểm tra số lượng sản phẩm, gửi email và trả về phản hồi
            response = processOrder(orderData);
        }
        return response;
    }

    private String processOrder(Map<String, Object> orderData) {
        // Xử lý đặt hàng ở đây và trả về phản hồi
        return "Order processed successfully!";
    }
}
