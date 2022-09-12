package org.aliacademy.model;

import javax.persistence.*;

@Entity
@Table(name = "cartItem")
public class CartItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private int id;

    @Column(name="cartId")
    private int cartId;

    @Column(name="productCode")
    private String productCode;

    @Column(name="quantityOrdered")
    private int quantityOrdered;

    @Column(name="extraPrice")
    private double extraPrice;

    public CartItem() {
    }

    public CartItem(int cartId, String productCode, int quantityOrdered, double extraPrice) {
        this.cartId = cartId;
        this.productCode = productCode;
        this.quantityOrdered = quantityOrdered;
        this.extraPrice = extraPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}
