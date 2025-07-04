package com.galettesausage.controller;

import com.galettesausage.dto.BasketItemDto;
import com.galettesausage.entity.BasketItem;
import com.galettesausage.service.BasketService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/basket")
@CrossOrigin
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getBasket(HttpSession session) {
        String sessionId = session.getId();
        List<BasketItem> items = basketService.getBasket(sessionId);
        
        BigDecimal total = items.stream()
                .map(BasketItem::calculatePrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("totalPrice", total);
        response.put("itemCount", items.size());
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<BasketItem> addToBasket(
            @Valid @RequestBody BasketItemDto dto,
            HttpSession session) {
        String sessionId = session.getId();
        BasketItem item = basketService.addToBasket(sessionId, dto);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<Void> removeFromBasket(
            @PathVariable Long itemId,
            HttpSession session) {
        String sessionId = session.getId();
        basketService.removeFromBasket(sessionId, itemId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/item/{itemId}/quantity")
    public ResponseEntity<BasketItem> updateQuantity(
            @PathVariable Long itemId,
            @RequestParam Integer quantity,
            HttpSession session) {
        String sessionId = session.getId();
        BasketItem item = basketService.updateQuantity(sessionId, itemId, quantity);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearBasket(HttpSession session) {
        String sessionId = session.getId();
        basketService.clearBasket(sessionId);
        return ResponseEntity.noContent().build();
    }
}