package br.com.heloisabusquim.product.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class UpdateProductDTO {

    private Long id;

    @NotBlank(message = "Name field is required and cannot be empty")
    private String name;

    @Positive(message = "Price field cannot be Zero or Negative")
    @NotNull(message = "Price field is required and cannot be empty")
    @Max(1000)
    private Double price;

    @Positive(message = "Brand ID field cannot be Zero or Negative")
    @NotNull(message = "Brand ID field is required and cannot be empty")
    private Long brandId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Long getBrandId() {
        return brandId;
    }

    @Override
    public String toString() {
        return "UpdateProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brandId=" + brandId +
                '}';
    }
}
