package br.com.heloisabusquim.product.usecase;

import br.com.heloisabusquim.product.domain.Product;
import br.com.heloisabusquim.product.gateway.ProductGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProduct {

    @Autowired
    private ProductGateway productGateway;

    public void update(Long id, String name, Double price, Long brandId) {

        if (productGateway.isProductNotRegisteredById(id)) {
            throw new IllegalArgumentException("No product found under the id: " + id);

        } else if (productGateway.isRegisteredByName(name)) {
            throw new IllegalArgumentException("There is already a product registered with this name");

        } else if (productGateway.isBrandNotRegisteredById(brandId)) {
            throw new IllegalArgumentException("There is not a brand registered under the id: " + brandId);

        } else {
            productGateway.updateEntireProduct(id, name, price, brandId);
        }

    }

}
