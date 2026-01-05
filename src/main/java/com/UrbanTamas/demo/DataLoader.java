package com.UrbanTamas.demo;

import com.UrbanTamas.demo.data.entity.ProductEntity;
import com.UrbanTamas.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        // Csak ha üres a tábla
        if (productRepository.count() == 0) {
            productRepository.saveAll(List.of(
                    new ProductEntity(null, "Gingerbread", 12.99, true,
                            "A spiced baked treat traditionally enjoyed during the Christmas season.",
                            "assets/images/products/gingerbread.jpg",
                            List.of("christmas", "sweet")),
                    new ProductEntity(null, "Panettone", 8.99, false,
                            "Fluffy Italian sweet bread filled with dried fruits and often served at Christmas.",
                            "assets/images/products/panettone.jpg",
                            List.of("christmas", "bakery-goods")),
                    new ProductEntity(null, "Chocolate", 1.99, false,
                            "Rich and versatile sweet enjoyed in countless forms, from desserts to confectionery.",
                            "assets/images/products/chocolate.jpg",
                            List.of("sweet", "dessert")),
                    new ProductEntity(null, "Pumpkin roll", 4.99, false,
                            "Soft pumpkin-flavored sponge cake rolled with a creamy filling, popular in autumn and winter.",
                            "assets/images/products/pumkim_roll.jpg",
                            List.of("dessert")),
                    new ProductEntity(null, "Bananas", 1.49, true,
                            "Fresh bananas from the farm.",
                            "assets/images/products/banana.jpg",
                            List.of("summer", "dessert")),
                    new ProductEntity(null, "Orange", 2.49, true,
                            "Juicy and sweet oranges.",
                            "assets/images/products/oranges.jpg",
                            List.of("summer", "dessert")),

                    new ProductEntity(null, "Item1", 4.29, true,
                            "A spiced baked treat traditionally enjoyed during the Christmas season.",
                            "assets/images/products/empty-image.png",
                            List.of("christmas")),
                    new ProductEntity(null, "Item2", 7.49, false,
                            "Fluffy Italian sweet bread filled with dried fruits and often served at Christmas.",
                            "assets/images/products/empty-image.png",
                            List.of("bakery-goods")),
                    new ProductEntity(null, "Item3", 10.29, false,
                            "Rich and versatile sweet enjoyed in countless forms, from desserts to confectionery.",
                            "assets/images/products/empty-image.png",
                            List.of("dessert")),
                    new ProductEntity(null, "Item4", 20.39, false,
                            "Soft pumpkin-flavored sponge cake rolled with a creamy filling, popular in autumn and winter.",
                            "assets/images/products/empty-image.png",
                            List.of("dessert")),
                    new ProductEntity(null, "Item5", 1.99, true,
                            "Fresh bananas from the farm.",
                            "assets/images/products/empty-image.png",
                            List.of("summer")),
                    new ProductEntity(null, "Item6", 19.19, true,
                            "Juicy and sweet oranges.",
                            "assets/images/products/empty-image.png",
                            List.of("summer"))
            ));
            System.out.println("Mock data loaded!");
        }
    }
}
