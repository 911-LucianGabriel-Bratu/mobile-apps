package com.example.appserver.config;

import com.example.appserver.websocket.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@EnableScheduling
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/api/ws/msg");
        registry.addHandler(instrumentBrandsWebSocketHandler(), "/api/ws/fetch/instrument-brands");
        registry.addHandler(instrumentCategoriesWebSocketHandler(), "/api/ws/fetch/instrument-categories");
        registry.addHandler(musicalInstrumentsWebSocketHandler(), "/api/ws/fetch/musical-instruments");
        registry.addHandler(ordersWebSocketHandler(), "/api/ws/fetch/orders");
        registry.addHandler(usersWebSocketHandler(), "/api/ws/fetch/users");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new WebSocketCommunicationHandler();
    }

    @Bean
    InstrumentBrandsWebSocketHandler instrumentBrandsWebSocketHandler() { return new InstrumentBrandsWebSocketHandler();}

    @Bean
    InstrumentCategoriesWebSocketHandler instrumentCategoriesWebSocketHandler() { return new InstrumentCategoriesWebSocketHandler(); }

    @Bean
    MusicalInstrumentsWebSocketHandler musicalInstrumentsWebSocketHandler() { return new MusicalInstrumentsWebSocketHandler(); }

    @Bean
    OrdersWebSocketHandler ordersWebSocketHandler() { return new OrdersWebSocketHandler(); }

    @Bean
    UsersWebSocketHandler usersWebSocketHandler() { return new UsersWebSocketHandler(); }
}