package shop.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import shop.dto.GoodsDto;

import java.math.BigDecimal;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShopRepositoryTest {

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @InjectMocks
    private ShopRepository repository;

    private final GoodsDto dto = new GoodsDto(1L, "pr1", new BigDecimal(1));

    @Test
    void getAllGoods() {

        var list = List.of(dto);

        when(namedParameterJdbcTemplate.query(any(), any(BeanPropertyRowMapper.class))).thenReturn(list);

        assertThat(repository.getAllGoods())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(list);

        verify(namedParameterJdbcTemplate).query(eq("select * from goods"), any(BeanPropertyRowMapper.class));
    }

    @Test
    void addProduct() {

        repository.addProduct(dto);
        verify(namedParameterJdbcTemplate).update(eq("insert into goods(product_name, price) values (:productName, :price)"),
                any(MapSqlParameterSource.class));
    }
}