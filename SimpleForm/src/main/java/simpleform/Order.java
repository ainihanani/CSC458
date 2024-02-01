package simpleform;

import java.util.Date;

public class Order {
    private int orderId;
    private Date date;
    private double amount;
    private String status;

    // Constructors

    public Order() {
    }

    public Order(int orderId, Date date, double amount, String status) {
        this.orderId = orderId;
        this.date = date;
        this.amount = amount;
        this.status = status;
    }

    // Getters and Setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
