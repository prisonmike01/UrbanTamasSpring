package com.UrbanTamas.demo.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Double price;
    private Boolean favourite;
    private String description;
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "product_types", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "type")
    private List<String> types;
}
