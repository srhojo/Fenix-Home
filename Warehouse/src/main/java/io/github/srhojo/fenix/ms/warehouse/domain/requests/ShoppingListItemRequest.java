package io.github.srhojo.fenix.ms.warehouse.domain.requests;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.github.srhojo.utils.cdm.Quantity;
import io.swagger.annotations.ApiModelProperty;

public class ShoppingListItemRequest implements Serializable {

    private static final long serialVersionUID = -3833663360348929223L;

    @NotEmpty
    @ApiModelProperty(name = "productId", notes = "Product id. the product has to be created at data base previously.", required = true)
    private String productId;

    @NotNull
    @Valid
    @ApiModelProperty(name = "quantity", required = true)
    private Quantity quantity;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(final String productId) {
        this.productId = productId;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(final Quantity quantity) {
        this.quantity = quantity;
    }
}
