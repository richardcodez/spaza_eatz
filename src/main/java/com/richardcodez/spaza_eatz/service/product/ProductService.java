package com.richardcodez.spaza_eatz.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.richardcodez.spaza_eatz.exceptions.ProductNotFoundException;
import com.richardcodez.spaza_eatz.model.Category;
import com.richardcodez.spaza_eatz.model.Product;
import com.richardcodez.spaza_eatz.repository.CategoryRepository;
import com.richardcodez.spaza_eatz.repository.ProductRepository;
import com.richardcodez.spaza_eatz.request.AddProductRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        // Check if category is found in the DB
        // if Yes, set it as a new product category
        // if No, then save it as a new category
        // Then set it as a new product category
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
        return null;
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
            request.getName(),
            request.getBrand(),
            request.getPrice(),
            request.getInventory(),
            request.getDescription(),
            category
        );
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
        .ifPresentOrElse(productRepository::delete,
         () -> {throw new ProductNotFoundException("Product not found");}); 
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).
        orElseThrow(()-> new ProductNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public void updateProduct(Product product, Long productId) {
        // TODO Auto-generated method stub
        
    }

}
