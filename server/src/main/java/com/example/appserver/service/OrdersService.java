package com.example.appserver.service;

import com.example.appserver.model.DTOs.OrdersDTO;
import com.example.appserver.model.MusicalInstruments;
import com.example.appserver.model.Orders;
import com.example.appserver.model.Users;
import com.example.appserver.repository.IMusicalInstrumentsRepo;
import com.example.appserver.repository.IOrdersRepo;
import com.example.appserver.repository.IUsersRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    private final IOrdersRepo ordersRepository;
    private final IUsersRepo usersRepo;

    private final IMusicalInstrumentsRepo musicalInstrumentsRepo;

    @Autowired
    public OrdersService(IOrdersRepo ordersRepository, IUsersRepo usersRepo, IMusicalInstrumentsRepo musicalInstrumentsRepo) {
        this.ordersRepository = ordersRepository;
        this.usersRepo = usersRepo;
        this.musicalInstrumentsRepo = musicalInstrumentsRepo;
    }

    @Transactional
    public boolean createOrder(OrdersDTO order) {
        try {
            if(usersRepo.findById(order.getUserID()).isPresent() &&
                    musicalInstrumentsRepo.findById(order.getMusicalInstrumentID()).isPresent()){
                Orders orders = new Orders();
                Users users = usersRepo.findById(order.getUserID()).get();
                MusicalInstruments musicalInstruments = musicalInstrumentsRepo.findById(order.getMusicalInstrumentID()).get();

                orders.setOrderID(order.getOrderID());
                orders.setOrderedAt(order.getOrderedAt());
                orders.setDeleted(order.isDeleted());
                orders.setQuantity(order.getQuantity());
                orders.setTotalPrice(order.getTotalPrice());
                orders.setUser(users);
                orders.setMusicalInstrument(musicalInstruments);

                ordersRepository.save(orders);
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateOrder(int id, OrdersDTO order) {
        try {
            if(ordersRepository.findById(id).isPresent() &&
                usersRepo.findById(order.getUserID()).isPresent() &&
                    musicalInstrumentsRepo.findById(order.getMusicalInstrumentID()).isPresent()){
                Orders orders = ordersRepository.findById(id).get();
                Users users = usersRepo.findById(order.getUserID()).get();
                MusicalInstruments musicalInstruments = musicalInstrumentsRepo.findById(order.getMusicalInstrumentID()).get();

                orders.setOrderID(order.getOrderID());
                orders.setOrderedAt(order.getOrderedAt());
                orders.setDeleted(order.isDeleted());
                orders.setQuantity(order.getQuantity());
                orders.setTotalPrice(order.getTotalPrice());
                orders.setUser(users);
                orders.setMusicalInstrument(musicalInstruments);

                ordersRepository.save(orders);
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean deleteOrder(int orderId) {
        try {
            ordersRepository.deleteById(orderId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
