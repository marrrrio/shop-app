package shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.dto.GoodsDto;
import shop.repository.ShopRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public List<GoodsDto> getAllGoods() {
        return shopRepository.getAllGoods();
    }

    public void addProduct(GoodsDto request) {
        shopRepository.addProduct(request);
    }
}
