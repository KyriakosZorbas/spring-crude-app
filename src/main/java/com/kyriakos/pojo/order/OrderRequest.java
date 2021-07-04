/**
 * Created by Kyriakos Zorbas on 04/07/21.
 */
package com.kyriakos.pojo.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class OrderRequest {

    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("customer")
    @Expose
    private Customer customer;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
