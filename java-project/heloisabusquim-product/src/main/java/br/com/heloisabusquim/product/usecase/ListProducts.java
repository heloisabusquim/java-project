package br.com.heloisabusquim.product.usecase;

import br.com.heloisabusquim.product.controller.ProductDTO;
import br.com.heloisabusquim.product.gateway.ProductGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListProducts {
    @Autowired
    private ProductGateway productGateway;
    public List<ProductDTO> listAllProducts() {
        return productGateway.listAll();
    }
    public ProductDTO findById(Long id) {
        return productGateway.findById(id);
    }
}
