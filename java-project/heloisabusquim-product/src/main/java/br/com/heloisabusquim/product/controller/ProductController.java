package br.com.heloisabusquim.product.controller;

import br.com.heloisabusquim.product.usecase.CreateProduct;
import br.com.heloisabusquim.product.usecase.DeleteProduct;
import br.com.heloisabusquim.product.usecase.ListProducts;
import br.com.heloisabusquim.product.usecase.UpdateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    // HTTP -> path, http verb, body

    // URI -> /v1/products
    // Method -> POST, PUT, PATCH, DELETE, GET
    // POST -> Criar um produto
    // PUT -> Atualização total de um produto
    // PATCH -> Atualização parcial de um produto
    // DELETE -> Remover um produto
    // GET -> Listar um produto

    @Autowired
    private CreateProduct createProduct;
    @Autowired
    private ListProducts listProducts;

    @Autowired
    private DeleteProduct deleteProduct;

    @Autowired
    private UpdateProduct updateProduct;

    @PostMapping
    public String createProduct(@RequestBody @Valid CreateProductDTO body) {

        try {

            createProduct.create(body.getName(), body.getPrice(), body.getBrandId());
            return body.toString();

        } catch (IllegalArgumentException e) {
            return e.getMessage();

        }
    }

    @GetMapping
    public List<ProductDTO> listProducts() {
        return listProducts.listAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable ("id") Long id) {

            ProductDTO product = listProducts.findById(id);
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setBrandId(product.getBrandId());
            return productDTO;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") Long id) {

        try {
            deleteProduct.delete(id);
            String message = "Product was successfully deleted";
            return message;

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @PutMapping("/{id}")
    public String update(@PathVariable ("id") Long id, @RequestBody @Valid UpdateProductDTO body) {

        try {
            updateProduct.update(id, body.getName(), body.getPrice(), body.getBrandId());
            String message = "Product was successfully updated";
            return message;

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
