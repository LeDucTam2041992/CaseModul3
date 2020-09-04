package model;

public class Product {
    private String id, name, imgUrl, special;
    private int price;

    public Product(String id, String name, String imgUrl, String special, int price) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.special = special;
        this.price = price;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}