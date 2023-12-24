package com.example.appserver.websocket;

import com.example.appserver.model.DTOs.OrdersDTO;
import com.example.appserver.model.Orders;
import com.example.appserver.repository.IOrdersRepo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Component
public class OrdersWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketCommunicationHandler.class);
    @Autowired
    private IOrdersRepo ordersRepo;
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Server connection opened");
        sessions.add(session);
        String msg = sendInstrumentBrandsData(session);
        TextMessage message = new TextMessage(msg);
        logger.info("Server sends: {}", message);
    }

    private String sendInstrumentBrandsData(WebSocketSession session) throws IOException {
        List<Orders> orders = ordersRepo.findAll();
        List<OrdersDTO> ordersDTOS = orders.stream()
                .map(order -> new OrdersDTO(
                        order.getOrderID(),
                        order.getMusicalInstrument().getMusicalInstrumentID(),
                        order.getUser().getUserID(),
                        order.getOrderedAt(),
                        order.getQuantity(),
                        order.getTotalPrice(),
                        order.isDeleted()
                ))
                .toList();
        String jsonData = convertDataToJson(ordersDTOS);
        session.sendMessage(new TextMessage(jsonData));
        return jsonData;
    }

    private String convertDataToJson(List<OrdersDTO> orders) {
        Gson gson = new Gson();
        return gson.toJson(orders);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("Server connection closed: {}", status);
        sessions.remove(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        logger.info("Server received: {}", request);

        String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(request));
        logger.info("Server sends: {}", response);
        session.sendMessage(new TextMessage(response));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        logger.info("Server transport error: {}", exception.getMessage());
    }


    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.demo.websocket");
    }
}