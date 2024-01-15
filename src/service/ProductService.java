package service;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductService implements Serializable {
    private static List<Product> products = new ArrayList<>();
    private static int currentId = 0;
    public ProductService(){
        products.add(new Product(1,"iphone11","ihhihi",332,2));
        products.add(new Product(2,"iphone12","ihhihi",333,2));
        products.add(new Product(3,"iphone13","ihhihi",334,2));
        products.add(new Product(4,"iphone14","ihhihi",335,2));
        products.add(new Product(5,"iphone15","ihhihi",3336,2));

    }

    public List<Product> getProducts() {
        return products;
    }

    public void updateProducts(List<Product> list){
        ProductService.products = list;

    }
    public void createProduct(Product product) {
        products.add(product);
    }

    public void displayProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }
    public void updateProduct(int productId, String newName,String description, float newPrice, int newQuantity) {
        for (Product product : products) {
            if (product.getId() == productId) {
                product.setName(newName);
                product.setDescription(description);
                product.setPrice(newPrice);
                product.setQuantity(newQuantity);
                System.out.println("Sản phẩm đã được cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm.");
    }
    public void deleteProduct(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                products.remove(product);
                System.out.println("Sản phẩm đã được xoá.");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm.");
    }
    public Product searchProduct() {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
            return null;
        }

        Product mostExpensiveProduct = Collections.max(products, Comparator.comparingDouble(Product::getPrice));
        System.out.println("Sản phẩm có giá đắt nhất là:");
        System.out.println(mostExpensiveProduct);
        return mostExpensiveProduct;
    }
    public void sortProductsByPriceAscending() {
        Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
        System.out.println("Sản phẩm đã được sắp xếp theo giá tăng dần.");
    }
    public void sortProductsByPriceDescending() {
        Collections.sort(products, Comparator.comparingDouble(Product::getPrice).reversed());
        System.out.println("Sản phẩm đã được sắp xếp theo giá giảm dần.");
    }

}
