package com.galettesausage.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.Map;

public class BasketItemDto {
    @NotNull(message = "Galette type is required")
    private Long galetteTypeId;

    private List<Long> sauceIds;

    private Map<Long, Integer> toppingQuantities;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity = 1;

    public Long getGaletteTypeId() {
        return galetteTypeId;
    }

    public void setGaletteTypeId(Long galetteTypeId) {
        this.galetteTypeId = galetteTypeId;
    }

    public List<Long> getSauceIds() {
        return sauceIds;
    }

    public void setSauceIds(List<Long> sauceIds) {
        this.sauceIds = sauceIds;
    }

    public Map<Long, Integer> getToppingQuantities() {
        return toppingQuantities;
    }

    public void setToppingQuantities(Map<Long, Integer> toppingQuantities) {
        this.toppingQuantities = toppingQuantities;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}