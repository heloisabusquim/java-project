package br.com.heloisabusquim.product.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class BrandDTO {

    private Long id;
    @NotBlank(message = "Name field is required and cannot be empty")
    @NotNull(message = "Name field cannot be null")
    private String name;

    @NotNull(message = "Creation Date field cannot be empty")
    @Past(message = "Creation Date field must be in YYYY-MM-DD format")
    private LocalDate creationDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "BrandDTO{" +
                "name='" + name + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
