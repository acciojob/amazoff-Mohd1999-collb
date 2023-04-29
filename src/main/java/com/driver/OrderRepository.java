package com.driver;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
@Repository
public class OrderRepository {
    HashMap<String, Order> orderMap = new HashMap<>();
    HashMap<String, DeliveryPartner> deliveryPartnerMap = new HashMap<>();
    HashMap<String, List<String>> pairMap = new HashMap<>();
    HashMap<String, String> assignedOrder = new HashMap<>();

    // public OrderRepository(){
    //     this.orderMap = new HashMap<>();
    //     this.deliveryPartnerMap  = new HashMap<>();
    //     this.pairMap = new HashMap<>();
    //     this.assignedOrder = new HashMap<>();
    // }
    public void order_Added(Order order) {
        orderMap.put(order.getId(), order);
    }

    public void partner_Added(String id) {
        DeliveryPartner deliveryPartner = new DeliveryPartner(id);
        deliveryPartnerMap.put(id, deliveryPartner);
    }

    public void orderPartnerPair_Added(String orderId, String partnerId) {
        List<String> order = new ArrayList<>();

        if (pairMap.containsKey(partnerId)) {
            order = pairMap.get(partnerId);
        }
        order.add(orderId);
        pairMap.put(partnerId, order);

        // increment alongside in partner order by 1
        DeliveryPartner partnerOrder = deliveryPartnerMap.get(partnerId);
        int noOfOrders = partnerOrder.getNumberOfOrders();
        partnerOrder.setNumberOfOrders(noOfOrders + 1);
        // Now order has been assigned
        assignedOrder.put(orderId, partnerId);
    }

    public Order get_Order(String orderId) {
        return orderMap.get(orderId);
    }

    public DeliveryPartner get_Partner(String partnerId) {
        return deliveryPartnerMap.get(partnerId);
    }

    public Integer get_OrderCount(String partnerId) {
        Integer count = pairMap.get(partnerId).size();
        return count;
    }

    public List<String> getOrders_ByPartner(String partnerId) {
        return pairMap.get(partnerId);
    }

    public List<String> get_OrdersAll() {
        List<String> order = new ArrayList<>();
        for (String ord : orderMap.keySet()) {
            order.add(ord);
        }
        return order;
    }

    public Integer getCount_OfUnassignedOrders() {
        Integer unassignedOrder = orderMap.size() - assignedOrder.size();
        return unassignedOrder;
    }

    public Integer getOrdersLeft_AfterGivenTimeByPartnerId(String time, String partnerId) {
        // 12:50
        int timeOfOrder = (Integer.parseInt(time.substring(0, 2)) * 60) + Integer.parseInt(time.substring(3));
        List<String> orders = pairMap.get(partnerId);

        Integer lateOrder = 0;
        for (String order : orders) {
            int orderTime = orderMap.get(order).getDeliveryTime();
            if (orderTime > timeOfOrder) {
                lateOrder++;
            }
        }
        return lateOrder;
    }

    public String get_LastDeliveryTimeByPartnerId(String partnerId) {
        int time = 0;
        List<String> orders = pairMap.get(partnerId);
        for (String order : orders) {
            int orderTime = orderMap.get(order).getDeliveryTime();
            if (orderTime > time) {
                time = orderTime;
            }
        }
        int hour = time / 60;
        int minute = time - hour * 60;
        String lastOrderTime = "";
        if (hour <= 9) {
            lastOrderTime = lastOrderTime + "0" + hour + ":";
        } else {
            lastOrderTime = lastOrderTime + hour + ":";
        }
        if (minute <= 9) {
            lastOrderTime = lastOrderTime + "0" + minute;
        } else {
            lastOrderTime = lastOrderTime + minute;
        }
        return lastOrderTime;
    }

    public void deletePartner_ById(String partnerId) {
        List<String> orders = pairMap.get(partnerId);
        for (String order : orders) {
            assignedOrder.remove(order);
        }
        pairMap.remove(partnerId);
    }

    public void deleteOrder_ById(String orderId) {
        // delete from orders
        orderMap.remove(orderId);

        // get partner id from assigned orders, if present
        if (assignedOrder.containsKey(orderId)) {
            String partnerId = assignedOrder.get(orderId);

            // decrement alongside in partner order by 1
            DeliveryPartner partnerOrder = deliveryPartnerMap.get(partnerId);
            int noOfOrders = partnerOrder.getNumberOfOrders();
            partnerOrder.setNumberOfOrders(noOfOrders - 1);

            // delete from assigned orders
            assignedOrder.remove(orderId);

            // get the order by partnerId
            List<String> orders = pairMap.get(partnerId);
            for (String order : orders) {
                if (order.equals(orderId)) {
                    orders.remove(order);
                    return;
                }
            }
        }
    }

}