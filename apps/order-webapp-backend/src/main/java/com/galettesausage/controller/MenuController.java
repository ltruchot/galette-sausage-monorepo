package com.galettesausage.controller;

import com.galettesausage.entity.GaletteType;
import com.galettesausage.entity.Sauce;
import com.galettesausage.entity.Topping;
import com.galettesausage.repository.GaletteTypeRepository;
import com.galettesausage.repository.SauceRepository;
import com.galettesausage.repository.ToppingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {

    private final GaletteTypeRepository galetteTypeRepository;
    private final SauceRepository sauceRepository;
    private final ToppingRepository toppingRepository;

    public MenuController(GaletteTypeRepository galetteTypeRepository, 
                         SauceRepository sauceRepository, 
                         ToppingRepository toppingRepository) {
        this.galetteTypeRepository = galetteTypeRepository;
        this.sauceRepository = sauceRepository;
        this.toppingRepository = toppingRepository;
    }

    @GetMapping
    public Map<String, Object> getFullMenu() {
        Map<String, Object> menu = new HashMap<>();
        menu.put("galetteTypes", galetteTypeRepository.findByAvailableTrue());
        menu.put("sauces", sauceRepository.findByAvailableTrue());
        menu.put("toppings", toppingRepository.findByAvailableTrue());
        return menu;
    }

    @GetMapping("/galette-types")
    public List<GaletteType> getGaletteTypes() {
        return galetteTypeRepository.findByAvailableTrue();
    }

    @GetMapping("/sauces")
    public List<Sauce> getSauces() {
        return sauceRepository.findByAvailableTrue();
    }

    @GetMapping("/toppings")
    public List<Topping> getToppings() {
        return toppingRepository.findByAvailableTrue();
    }
}