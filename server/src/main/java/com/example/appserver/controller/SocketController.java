package com.example.appserver.controller;

import com.example.appserver.websocket.WebSocketCommunicationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class SocketController {
    @Autowired
    private WebSocketCommunicationHandler webSocketHandler;

    @MessageMapping("/send-message")
    @SendTo("/api/ws/msg")
    public void sendMessage() throws IOException {
        webSocketHandler.sendPeriodicMessages();
    }
}
