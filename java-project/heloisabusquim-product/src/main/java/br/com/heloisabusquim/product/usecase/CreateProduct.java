package br.com.heloisabusquim.product.usecase;

import br.com.heloisabusquim.product.domain.Product;
import br.com.heloisabusquim.product.gateway.ProductGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProduct {

    @Autowired
    private ProductGateway productGateway;

    public void create(String name, Double price, Long brandId) {

        if (productGateway.isRegisteredByName(name)) {
            throw new IllegalArgumentException("There is already a product registered under this name");

        } else if (productGateway.isBrandNotRegisteredById(brandId)) {
            throw new IllegalArgumentException("There is not a brand registered under this id");

        } else {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setBrandId(brandId);
            System.out.println(product);

            productGateway.save(product);
        }
    }
}
