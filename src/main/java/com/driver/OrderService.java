package com.driver;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class OrderService {  
    
    // @Autowired
    // OrderRepository orderRepository;  
    OrderRepository orderRepository = new OrderRepository();
    public void orderAdded(Order order){
        orderRepository.order_Added(order);
    }

    public void partnerAdded(String id){
        orderRepository.partner_Added(id);
    }

    public void orderPartnerPairAdded(String orderId, String partnerId){
        orderRepository.orderPartnerPair_Added(orderId, partnerId);
    }

    public Order getOrder(String orderId){
        return orderRepository.get_Order(orderId);
    }

    public DeliveryPartner getPartner(String partnerId){
        return orderRepository.get_Partner(partnerId);
    }

    public Integer getOrderCount(String partnerId){
        return orderRepository.get_OrderCount(partnerId);
    }

    public List<String> getOrdersByPartner(String partnerId){
        return orderRepository.getOrders_ByPartner(partnerId);
    }

    public List<String> getOrdersAll(){
        return orderRepository.get_OrdersAll();
    }

    public Integer getCountOf_UnassignedOrders(){
        return orderRepository.getCount_OfUnassignedOrders();
    }

    public Integer getOrders_LeftAfterGivenTimeByPartnerId(String time, String partnerId){
        return orderRepository.getOrdersLeft_AfterGivenTimeByPartnerId(time, partnerId);
    }
    public String getLastDelivery_TimeByPartnerId(String partnerId){
        return orderRepository.get_LastDeliveryTimeByPartnerId(partnerId);
    }

    public void delete_PartnerById(String partnerId){
        orderRepository.deletePartner_ById(partnerId);
    }

    public void delete_OrderById(String orderId){
        orderRepository.deleteOrder_ById(orderId);
    }
}