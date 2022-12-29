package br.com.heloisabusquim.product.domain;

public class Product {

    private Long id;
    private String name;

    private Double price;

    private Long brandId;

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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", brandId=" + brandId +
                '}';
    }
}
