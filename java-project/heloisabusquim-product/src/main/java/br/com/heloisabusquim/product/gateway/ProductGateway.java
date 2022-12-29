package br.com.heloisabusquim.product.gateway;

import br.com.heloisabusquim.product.controller.BrandDTO;
import br.com.heloisabusquim.product.controller.ProductDTO;
import br.com.heloisabusquim.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductGateway {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ProductGateway() {
        //bloqueando acesso ao construtor por ser classe Singleton
    }
    RowMapper<ProductDTO> rowMapper = (rs, rowNum) -> {
        ProductDTO product = new ProductDTO();
        BrandDTO brand = new BrandDTO();

        product.setId(rs.getLong("ID"));
        product.setName(rs.getString("NAME"));
        product.setPrice(rs.getDouble("PRICE"));
        product.setBrandId(rs.getLong("ID_BRAND"));

        brand.setId(product.getBrandId());
        brand.setName(rs.getString("NAME"));
        brand.setCreationDate(rs.getDate("CREATION_DATE").toLocalDate());
        return product;
    };

    public List<ProductDTO> listAll(){
        String sql = "SELECT * FROM product";
        String join = "SELECT p.id, p.name, p.price, p.id_brand, b.name, b.creation_date\n" +
                "FROM product AS p\n" +
                "INNER JOIN brand AS b ON p.id_brand = b.id;";
        return jdbcTemplate.query(join, rowMapper);
    }

    public void save(Product product) {
        String sql = "INSERT INTO product (name, price, id_brand) values ('" + product.getName() + "', " + product.getPrice() + ", " + product.getBrandId() + ");";
        jdbcTemplate.execute(sql);
    }

    public boolean isRegisteredByName(String name) {
        String sql = "SELECT COUNT(*) FROM product p WHERE p.name='" + name + "'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count > 0;
    }

    public boolean isBrandNotRegisteredById(Long brandId) {
        String sql = "SELECT COUNT(*) FROM brand b WHERE b.id=" + brandId + ";";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count < 1;
    }

    public void deleteProduct(Long id) {
        String sql = "DELETE FROM product p WHERE p.id=" + id + ";";
        jdbcTemplate.execute(sql);
    }

    public boolean isProductNotRegisteredById(Long id) {
        String sql = "SELECT COUNT(*) FROM product p WHERE p.id=" + id + ";";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count < 1;
    }

    public ProductDTO findById(Long id) {
        String sql = "SELECT * FROM product p WHERE p.id=" + id + ";";
        String join = "SELECT p.id, p.name, p.price, p.id_brand, b.name, b.creation_date FROM product AS p INNER JOIN brand AS b ON p.id_brand = b.id WHERE p.id=" + id + ";";
        return jdbcTemplate.queryForObject(
                join,
                rowMapper);
    }

    public void updateEntireProduct(Long id, String name, Double price, Long brandId) {
        String sql = "UPDATE product p SET p.name='" + name + "', p.price=" + price + ", p.id_brand=" + brandId + " WHERE p.id=" + id + ";";
        jdbcTemplate.execute(sql);
    }
}