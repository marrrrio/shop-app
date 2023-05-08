package shop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.dto.GoodsDto;
import shop.repository.ShopRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShopServiceTest {

    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    private ShopService service;

    private final GoodsDto dto = new GoodsDto(1L, "pr1", new BigDecimal(1));

    @Test
    void getAllGoods() {

        var list = List.of(dto);

        when(shopRepository.getAllGoods()).thenReturn(list);

        assertThat(service.getAllGoods())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(list);

        verify(shopRepository).getAllGoods();
    }

    @Test
    void addProduct() {

        service.addProduct(dto);
        verify(shopRepository).addProduct(eq(dto));
    }
}