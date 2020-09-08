package model;

public class OrderDetail {
    private String id;
    private int quantity, price;
    private Product product;

    public OrderDetail(int quantity, int price, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public OrderDetail() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
