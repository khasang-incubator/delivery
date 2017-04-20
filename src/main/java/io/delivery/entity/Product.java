package io.delivery.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private int price;

    private String description;

    @OneToOne
    @JoinColumn(name = "product_catalog_section_id")
    private ProductCatalogSection productCatalogSection;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCatalogSection getProductCatalogSection() {
        return productCatalogSection;
    }

    public void setProductCatalogSection(ProductCatalogSection productCatalogSection) {
        this.productCatalogSection = productCatalogSection;
    }
}
