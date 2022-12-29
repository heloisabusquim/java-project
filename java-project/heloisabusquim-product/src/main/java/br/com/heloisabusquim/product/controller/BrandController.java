package br.com.heloisabusquim.product.controller;

import br.com.heloisabusquim.product.domain.Brand;
import br.com.heloisabusquim.product.usecase.CreateBrand;
import br.com.heloisabusquim.product.usecase.ListBrands;
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
@RequestMapping("/v1/brands")
public class BrandController {

    @Autowired
    private CreateBrand brand;

    @Autowired
    private ListBrands listBrands;

    @PostMapping
    public String createBrand(@RequestBody @Valid CreateBrandDTO body) {

        try {

            brand.createBrand(body.getName(), body.getCreationDate());
            return body.toString();

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
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

    @GetMapping
    public List<Brand> listBrands() {
        return listBrands.listAllBrands();
    }
}
