package com.infoiv.services;

import com.infoiv.model.Order;
import com.infoiv.orders.Status;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

@Service
public class OrderService {
    Collection<Order> orderContainer= Arrays.asList(
            new Order[]{
                    new Order(101,"Raj malhotra","some tasty food"),
                    new Order(102,"Simran Sharma","some tasty food"),
                    new Order(103,"Ajaad Singh","some tasty food")
            }
    );
    public Order findById(int id) {
        for (Order order : orderContainer) {
            if (order.getOderId() == id)
                return order;
        }
        return null;
    }


    public Collection<Order> findAll() {
        return orderContainer;
    }

    public Status deleteById(int id) {

        Iterator<Order> iterator = orderContainer.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOderId() == id) {
                //iterator.remove();
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
}
