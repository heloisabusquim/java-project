package br.com.heloisabusquim.product.usecase;

import br.com.heloisabusquim.product.domain.Brand;
import br.com.heloisabusquim.product.domain.Product;
import br.com.heloisabusquim.product.gateway.BrandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListBrands {

    @Autowired
    private BrandGateway brandGateway;

    public List<Brand> listAllBrands() {
        return brandGateway.listAll();
    }
}
