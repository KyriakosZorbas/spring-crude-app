/**
 * Created by Kyriakos Zorbas on 04/07/21.
 */

package com.kyriakos.pojo.order;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Date {

    @SerializedName("from")
    @Expose
    private Long from;
    @SerializedName("to")
    @Expose
    private Long to;

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

}