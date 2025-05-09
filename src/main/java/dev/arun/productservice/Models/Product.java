package dev.arun.productservice.Models;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Product extends BaseModels{
    private String title;
    private String description;
    private String image;
    private Category category;
    private double price;
}
