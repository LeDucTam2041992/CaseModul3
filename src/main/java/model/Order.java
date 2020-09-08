package model;

import java.util.List;

public class Order {
    private String id;
    private int quantity;
    private Customer customer;
    private List<OrderDetail> listOrder;

    public Order(String id, int quantity, Customer customer, List<OrderDetail> orderDetails) {
        this.id = id;
        this.quantity = quantity;
        this.customer = customer;
        this.listOrder = orderDetails;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<OrderDetail> listOrder) {
        this.listOrder = listOrder;
    }
}
