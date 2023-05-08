package shop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import shop.dto.GoodsDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShopRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<GoodsDto> getAllGoods() {
        return namedParameterJdbcTemplate.query("select * from goods", new BeanPropertyRowMapper<>(GoodsDto.class));
    }
    public void addProduct(GoodsDto request) {
        namedParameterJdbcTemplate.update("insert into goods(product_name, price) values (:productName, :price)",
                new MapSqlParameterSource()
        .addValue("productName", request.getProductName())
        .addValue("price", request.getPrice())
        );
    }
}
