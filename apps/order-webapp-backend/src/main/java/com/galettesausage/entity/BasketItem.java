package com.galettesausage.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "basket_items")
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sessionId;

    @ManyToOne
    @JoinColumn(name = "galette_type_id", nullable = false)
    private GaletteType galetteType;

    @ManyToMany
    @JoinTable(
        name = "basket_item_sauces",
        joinColumns = @JoinColumn(name = "basket_item_id"),
        inverseJoinColumns = @JoinColumn(name = "sauce_id")
    )
    private List<Sauce> sauces = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "basket_item_topping_quantities", joinColumns = @JoinColumn(name = "basket_item_id"))
    @MapKeyJoinColumn(name = "topping_id")
    @Column(name = "quantity")
    private Map<Topping, Integer> toppingQuantities = new HashMap<>();

    @Column(nullable = false)
    private Integer quantity = 1;

    public BigDecimal calculatePrice() {
        BigDecimal price = galetteType.getPrice();
        
        for (Sauce sauce : sauces) {
            price = price.add(sauce.getPrice());
        }
        
        for (Map.Entry<Topping, Integer> entry : toppingQuantities.entrySet()) {
            price = price.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public GaletteType getGaletteType() {
        return galetteType;
    }

    public void setGaletteType(GaletteType galetteType) {
        this.galetteType = galetteType;
    }

    public List<Sauce> getSauces() {
        return sauces;
    }

    public void setSauces(List<Sauce> sauces) {
        this.sauces = sauces;
    }

    public Map<Topping, Integer> getToppingQuantities() {
        return toppingQuantities;
    }

    public void setToppingQuantities(Map<Topping, Integer> toppingQuantities) {
        this.toppingQuantities = toppingQuantities;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}