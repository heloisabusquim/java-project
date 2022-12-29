package br.com.heloisabusquim.product.usecase;

import br.com.heloisabusquim.product.domain.Brand;
import br.com.heloisabusquim.product.gateway.BrandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateBrand {

    @Autowired
    private BrandGateway brandGateway;

    public void createBrand(String name, LocalDate creationDate) {

        if (brandGateway.existsByName(name)) {
            throw new IllegalArgumentException("There is already a brand registered under this name");

        } else {
            Brand brand = new Brand();
            brand.setName(name);
            brand.setCreationDate(creationDate);

            brandGateway.saveBrand(brand);
        }
    }
}
