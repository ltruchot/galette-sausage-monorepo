package com.galettesausage.config;

import com.galettesausage.entity.GaletteType;
import com.galettesausage.entity.Sauce;
import com.galettesausage.entity.Topping;
import com.galettesausage.repository.GaletteTypeRepository;
import com.galettesausage.repository.SauceRepository;
import com.galettesausage.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MenuDataInitializer implements CommandLineRunner {

    @Autowired
    private GaletteTypeRepository galetteTypeRepository;

    @Autowired
    private SauceRepository sauceRepository;

    @Autowired
    private ToppingRepository toppingRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeGaletteTypes();
        initializeSauces();
        initializeToppings();
    }

    private void initializeGaletteTypes() {
        if (galetteTypeRepository.count() == 0) {
            createGaletteType("Classic Pork", "Traditional Breton galette with pork sausage");
            createGaletteType("Merguez", "Spicy lamb and beef sausage");
            createGaletteType("Vegetarian", "Plant-based sausage option");
            System.out.println("Galette types initialized");
        }
    }

    private void initializeSauces() {
        if (sauceRepository.count() == 0) {
            createSauce("Mustard");
            createSauce("Ketchup");
            createSauce("Mayonnaise");
            createSauce("Algerian");
            System.out.println("Sauces initialized");
        }
    }

    private void initializeToppings() {
        if (toppingRepository.count() == 0) {
            createTopping("Cheese");
            createTopping("Salad");
            createTopping("Pickles");
            createTopping("Fried onions");
            createTopping("Jalapeño peppers");
            System.out.println("Toppings initialized");
        }
    }

    private void createGaletteType(String name, String description) {
        GaletteType galetteType = new GaletteType();
        galetteType.setName(name);
        galetteType.setDescription(description);
        galetteType.setPrice(new BigDecimal("4.00"));
        galetteType.setAvailable(true);
        galetteTypeRepository.save(galetteType);
    }

    private void createSauce(String name) {
        Sauce sauce = new Sauce();
        sauce.setName(name);
        sauce.setPrice(new BigDecimal("0.50"));
        sauce.setAvailable(true);
        sauceRepository.save(sauce);
    }

    private void createTopping(String name) {
        Topping topping = new Topping();
        topping.setName(name);
        topping.setPrice(new BigDecimal("1.00"));
        topping.setAvailable(true);
        toppingRepository.save(topping);
    }
}