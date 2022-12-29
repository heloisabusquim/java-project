package br.com.heloisabusquim.product.domain;

import java.time.LocalDate;


public class Brand {

    private Long id;
    private String name;
    private LocalDate creationDate;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

}
