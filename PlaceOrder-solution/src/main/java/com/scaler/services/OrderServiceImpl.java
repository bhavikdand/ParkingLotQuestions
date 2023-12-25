package com.scaler.services;

import com.scaler.exceptions.CustomerSessionNotFound;
import com.scaler.exceptions.InvalidMenuItem;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.*;
import com.scaler.repositories.CustomerSessionRepository;
import com.scaler.repositories.MenuItemRepository;
import com.scaler.repositories.OrderRepository;
import com.scaler.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService{

    private CustomerSessionRepository customerSessionRepository;
    private UserRepository userRepository;
    private MenuItemRepository menuItemRepository;
    private OrderRepository orderRepository;

    public OrderServiceImpl(CustomerSessionRepository customerSessionRepository, UserRepository userRepository, MenuItemRepository menuItemRepository, OrderRepository orderRepository) {
        this.customerSessionRepository = customerSessionRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(long userId, Map<Long, Integer> orderedItems) throws UserNotFoundException, InvalidMenuItem {
        Optional<CustomerSession> optionalCustomerSession = customerSessionRepository.findActiveCustomerSessionByUserId(userId);
        CustomerSession customerSession;
        if(optionalCustomerSession.isEmpty()){
            customerSession = new CustomerSession();
            Optional<User> optionalUser = userRepository.findById(userId);
            if(optionalUser.isEmpty()){
                throw new UserNotFoundException("User not found");
            }
            customerSession.setUser(optionalUser.get());
            customerSession.setCustomerSessionStatus(CustomerSessionStatus.ACTIVE);
            customerSession = customerSessionRepository.save(customerSession);
        } else {
            customerSession = optionalCustomerSession.get();
        }
        Order order = new Order();
        order.setCustomerSession(customerSession);
        Map<MenuItem, Integer> menuItemQuantityMap = new HashMap<>();
        for(Map.Entry<Long, Integer> entry: orderedItems.entrySet()){
            Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(entry.getKey());
            if(optionalMenuItem.isPresent()){
                menuItemQuantityMap.put(optionalMenuItem.get(), entry.getValue());
            } else {
                throw new InvalidMenuItem("Menu item not found");
            }
        }
        order.setOrderedItems(menuItemQuantityMap);
        order = orderRepository.save(order);
        return order;
    }

}
