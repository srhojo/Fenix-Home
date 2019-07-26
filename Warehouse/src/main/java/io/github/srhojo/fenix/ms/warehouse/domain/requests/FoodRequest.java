package io.github.srhojo.fenix.ms.warehouse.domain.requests;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.github.srhojo.utils.cdm.Quantity;
import io.swagger.annotations.ApiModelProperty;

public class FoodRequest implements Serializable {
    private static final long serialVersionUID = 7887295188620901402L;

    @NotEmpty
    @ApiModelProperty(name = "name", notes = "name of the food", required = true, example = "Potatoes")
    private String name;

    @ApiModelProperty(name = "description", notes = " A sort description", example = "Perfect to cooking french chips ")
    private String description;

    @ApiModelProperty(name = "categoryName", notes = "Category base. Category must be created at database")
    private String categoryName;

    @ApiModelProperty(name = "subCategoryName", notes = "SubCategory base. SubCategory must be created at database")
    private String subCategoryName;

    @NotNull
    @Valid
    @ApiModelProperty(name = "quantity", notes = "quantity of food")
    private Quantity quantity;

    @ApiModelProperty
    private LocalDate expirationDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(final String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(final Quantity quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
