package com.galettesausage.service;

import com.galettesausage.dto.BasketItemDto;
import com.galettesausage.entity.*;
import com.galettesausage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class BasketService {

    @Autowired
    private BasketItemRepository basketItemRepository;

    @Autowired
    private GaletteTypeRepository galetteTypeRepository;

    @Autowired
    private SauceRepository sauceRepository;

    @Autowired
    private ToppingRepository toppingRepository;

    public List<BasketItem> getBasket(String sessionId) {
        return basketItemRepository.findBySessionId(sessionId);
    }

    public BasketItem addToBasket(String sessionId, BasketItemDto dto) {
        // Validate galette type
        GaletteType galetteType = galetteTypeRepository.findById(dto.getGaletteTypeId())
                .orElseThrow(() -> new RuntimeException("Galette type not found"));

        // Create basket item
        BasketItem item = new BasketItem();
        item.setSessionId(sessionId);
        item.setGaletteType(galetteType);
        item.setQuantity(dto.getQuantity());

        // Add sauces (max 2, unique)
        if (dto.getSauceIds() != null && !dto.getSauceIds().isEmpty()) {
            Set<Long> uniqueSauceIds = new HashSet<>(dto.getSauceIds());
            if (uniqueSauceIds.size() > 2) {
                throw new RuntimeException("Maximum 2 sauces allowed");
            }
            List<Sauce> sauces = sauceRepository.findAllById(uniqueSauceIds);
            item.setSauces(sauces);
        }

        // Add toppings (max 3 different, 1-2 quantity each)
        if (dto.getToppingQuantities() != null && !dto.getToppingQuantities().isEmpty()) {
            if (dto.getToppingQuantities().size() > 3) {
                throw new RuntimeException("Maximum 3 different toppings allowed");
            }
            Map<Topping, Integer> toppingQuantities = new HashMap<>();
            for (Map.Entry<Long, Integer> entry : dto.getToppingQuantities().entrySet()) {
                if (entry.getValue() < 1 || entry.getValue() > 2) {
                    throw new RuntimeException("Topping quantity must be 1 or 2");
                }
                Topping topping = toppingRepository.findById(entry.getKey())
                        .orElseThrow(() -> new RuntimeException("Topping not found"));
                toppingQuantities.put(topping, entry.getValue());
            }
            item.setToppingQuantities(toppingQuantities);
        }

        return basketItemRepository.save(item);
    }

    public void removeFromBasket(String sessionId, Long itemId) {
        BasketItem item = basketItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Basket item not found"));
        
        if (!item.getSessionId().equals(sessionId)) {
            throw new RuntimeException("Unauthorized");
        }
        
        basketItemRepository.delete(item);
    }

    public void clearBasket(String sessionId) {
        basketItemRepository.deleteBySessionId(sessionId);
    }

    public BasketItem updateQuantity(String sessionId, Long itemId, Integer quantity) {
        if (quantity < 1) {
            throw new RuntimeException("Quantity must be positive");
        }

        BasketItem item = basketItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Basket item not found"));
        
        if (!item.getSessionId().equals(sessionId)) {
            throw new RuntimeException("Unauthorized");
        }
        
        item.setQuantity(quantity);
        return basketItemRepository.save(item);
    }
}