package com.example.Products.models;

import lombok.Data;

@Data
public class ProductsResponse {
    public int id;
    public String title;
    public double price;
    public String description;
    public String category;
    public String image;
    public Rating rating;
}
