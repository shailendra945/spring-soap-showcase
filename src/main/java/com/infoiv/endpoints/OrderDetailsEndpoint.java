package com.infoiv.endpoints;

import com.infoiv.exception.OrderNotFoundException;
import com.infoiv.model.Order;
import com.infoiv.orders.*;
import com.infoiv.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Collection;

@Endpoint
public class OrderDetailsEndpoint {

    @Autowired
    private OrderService orderSrvc;

    @PayloadRoot(namespace = "http://infoiv.com/orders",
            localPart = "OrderDetailsRequest")
    @ResponsePayload
    public OrderDetailsResponse processOrderRequest(@RequestPayload OrderDetailsRequest request){
        Order order = orderSrvc.findById(request.getId());

        if (order == null)
            throw new OrderNotFoundException("Invalid order Id " + request.getId());

        return mapOrderDetails(order);

    }

    @PayloadRoot(namespace = "http://infoiv.com/orders",
            localPart = "allOrderDetailsRequest")
    @ResponsePayload
    public AllOrderDetailsResponse processAllOrderRequest(@RequestPayload AllOrderDetailsRequest request){
        Collection<Order> allOrder = orderSrvc.findAll();

        if (allOrder == null || allOrder.isEmpty())
            throw new OrderNotFoundException("No Order Found");

        return mapOrderSDetails(allOrder);

    }

    @PayloadRoot(namespace = "http://infoiv.com/orders",
            localPart = "DeleteOrderDetailsRequest")
    @ResponsePayload
    public DeleteOrderDetailsResponse processDeleteOrderRequest(@RequestPayload DeleteOrderDetailsRequest request){
       System.out.println("******************* processDeleteOrder called **********");
        Status status = orderSrvc.deleteById(request.getId());
        DeleteOrderDetailsResponse deleteOrderDetailsResponse = new DeleteOrderDetailsResponse();
        deleteOrderDetailsResponse.setStatus(status);
        return deleteOrderDetailsResponse;

    }


    private AllOrderDetailsResponse mapOrderSDetails(Collection<Order> allOrder) {
        AllOrderDetailsResponse allOrderDetailsResponse = new AllOrderDetailsResponse();
        for (Order order : allOrder) {
            allOrderDetailsResponse.getOrderDetails().add(mapOrderToOrderDetails(order));

        }
        return allOrderDetailsResponse;
    }

    private OrderDetailsResponse mapOrderDetails(Order order) {
        OrderDetailsResponse odr= new OrderDetailsResponse();
        odr.setOrderDetails(mapOrderToOrderDetails(order));
        return odr;
    }

    private OrderDetails mapOrderToOrderDetails(Order order) {
        OrderDetails od = new OrderDetails();
        od.setOrderId(order.getOderId());
        od.setOrderBy(order.getOdereeName());
        od.setDescription(order.getOrderDescription());
        return od;
    }

}
