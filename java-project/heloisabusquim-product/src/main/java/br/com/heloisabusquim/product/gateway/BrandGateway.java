package br.com.heloisabusquim.product.gateway;

import br.com.heloisabusquim.product.domain.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class BrandGateway {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Brand> rowMapper = (rs, rowNum) -> {
        Brand brand = new Brand();
        brand.setName(rs.getString("NAME"));
        brand.setCreationDate(LocalDate.parse(rs.getString("CREATION_DATE")));
        return brand;
    };

    public void saveBrand(Brand brand) {
        jdbcTemplate.execute("INSERT INTO brand (name, creation_date) VALUES ('" + brand.getName() + "', '" + brand.getCreationDate() + "');");
    }

    public boolean existsByName(String name) {
        String sql = "SELECT COUNT(*) FROM brand b WHERE b.name='" + name + "'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count > 0;
    }

    public List<Brand> listAll(){
        String sql = "SELECT * FROM brand";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
