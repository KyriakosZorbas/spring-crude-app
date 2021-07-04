/**
 * Created by Kyriakos Zorbas on 04/07/21.
 */
package com.kyriakos.pojo.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Product {

    @SerializedName("productId")
    @Expose
    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
