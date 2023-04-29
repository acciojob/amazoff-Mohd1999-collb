// package com.driver;

// import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;
// import java.util.List;

// @Service
// public class OrderService {  
    
//     @Autowired
//     OrderRepository orderRepository;        
//     public void orderAdded(Order order){
//         orderRepository.order_Added(order);
//     }

//     public void partnerAdded(String id){
//         orderRepository.partner_Added(id);
//     }

//     public void orderPartnerPairAdded(String orderId, String partnerId){
//         orderRepository.orderPartnerPair_Added(orderId, partnerId);
//     }

//     public Order getOrder(String orderId){
//         return orderRepository.get_Order(orderId);
//     }

//     public DeliveryPartner getPartner(String partnerId){
//         return orderRepository.get_Partner(partnerId);
//     }

//     public Integer getOrderCount(String partnerId){
//         return orderRepository.get_OrderCount(partnerId);
//     }

//     public List<String> getOrdersByPartner(String partnerId){
//         return orderRepository.getOrders_ByPartner(partnerId);
//     }

//     public List<String> getOrdersAll(){
//         return orderRepository.get_OrdersAll();
//     }

//     public Integer getCountOf_UnassignedOrders(){
//         return orderRepository.getCount_OfUnassignedOrders();
//     }

//     public Integer getOrders_LeftAfterGivenTimeByPartnerId(String time, String partnerId){
//         return orderRepository.getOrdersLeft_AfterGivenTimeByPartnerId(time, partnerId);
//     }
//     public String getLastDelivery_TimeByPartnerId(String partnerId){
//         return orderRepository.get_LastDeliveryTimeByPartnerId(partnerId);
//     }

//     public void delete_PartnerById(String partnerId){
//         orderRepository.deletePartner_ById(partnerId);
//     }

//     public void delete_OrderById(String orderId){
//         orderRepository.deleteOrder_ById(orderId);
//     }
// }

package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();

    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public void addPartner(String partnerId) {
        orderRepository.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        orderRepository.addOrderPartnerPair(orderId, partnerId);
    }

    public Order getOrderById(String orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return orderRepository.getPartnerById(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders() {
        return orderRepository.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId) {
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId) {
        orderRepository.deleteOrderById(orderId);
    }

}