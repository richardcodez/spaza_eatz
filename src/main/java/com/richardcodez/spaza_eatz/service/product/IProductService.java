package com.richardcodez.spaza_eatz.service.product;

import java.util.List;

import com.richardcodez.spaza_eatz.model.Product;
import com.richardcodez.spaza_eatz.request.AddProductRequest;
import com.richardcodez.spaza_eatz.request.UpdateProductRequest;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateProductRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
