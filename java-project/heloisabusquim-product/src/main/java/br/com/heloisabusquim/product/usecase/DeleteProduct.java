package br.com.heloisabusquim.product.usecase;

import br.com.heloisabusquim.product.gateway.ProductGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteProduct {

    @Autowired
    private ProductGateway productGateway;

    public void delete(Long id) {

        if (productGateway.isProductNotRegisteredById(id)) {
            throw new IllegalArgumentException("There is not a product registered under the id: " + id);

        }
            productGateway.deleteProduct(id);
        }
}
