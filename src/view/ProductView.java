package view;

import model.Product;
import service.ProductService;
import utils.FileUtils;
import utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    static ProductService productService = new ProductService();
    private Scanner scanner = new Scanner(System.in);
    public void laugher(){
        int choice;
        boolean flag = false;
        do {
            System.out.println("------ Quản lý sản phẩm ------");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xoá sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm");
            System.out.println("6. Sắp xếp sản phẩm");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Đọc từ file");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 : {
                    addProduct();
                    break;
                }

                case 2: {
                    displayProduct();
                    break;
                }

                case 3 : {
                    updateProduct();
                    break;
                }

                case 4: {
                    deleteProduct();
                    break;
                }
                case 5 : {
                    searchProduct();
                    break;
                }
                case 6 :{
                    sortProduct();
                    break;
                }
                case 7: {
                    writeFile();
                    break;
                }
                case 8 :{
                    readData();
                    break;
                }
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng thử lại.");
                    break;
            }

        }while (!flag);


    }
    public void addProduct(){
        boolean flag = true;

        System.out.print("Nhập ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập Tên: ");
        String name = scanner.nextLine();
        if (!ValidateUtils.isProductStringValid(name,ValidateUtils.USERNAME_REGEX)){
            System.out.println("Ban nhap ten sai");
            return;
        }
        System.out.println("Nhập mô tả");
        String description = scanner.nextLine();
        if (!ValidateUtils.isProductStringValid(description,ValidateUtils.DESCRIPTION_REGEX)){
            System.out.println("Nhap mo ta sai");
            return;
        }

        System.out.print("Nhập Giá: ");
        float price = scanner.nextFloat();
        System.out.print("Nhập Số lượng: ");
        int quantity = scanner.nextInt();


        Product newProduct = new Product(id, name,description, price, quantity);
        productService.createProduct(newProduct);
    }
    public void  deleteProduct(){
        System.out.print("Nhập ID sản phẩm cần xoá: ");
        int deleteId = scanner.nextInt();
        System.out.println("1 la xoa");
        System.out.println("2 quay ve");
        int choice = scanner.nextInt();
        switch (choice){
            case 1 : {
                productService.deleteProduct(deleteId);
                break;
            }
            case 2 :{
                return;
            }

        }

    }
    public void updateProduct(){
        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập Tên mới: ");
        String newName = scanner.nextLine();
        System.out.println("Nhập mô tả mới");
        String description = scanner.nextLine();
        System.out.print("Nhập Giá mới: ");
        float newPrice = scanner.nextFloat();
        System.out.print("Nhập Số lượng mới: ");
        int newQuantity = scanner.nextInt();
        productService.updateProduct(updateId, newName,description, newPrice, newQuantity);
    }
    public void displayProduct(){

            List<Product> products = productService.getProducts();
            System.out.printf("%10s | %20s | %30s | %15s | %10s\n", "ID", "Name", "Description", "Price","Quantity");
            for (Product p : products) {
                System.out.printf("%10s | %20s | %30s | %15s | %10s\n",
                        p.getId(), p.getName(), p.getDescription(), p.getPrice(),p.getQuantity() );
            }

    }
    public void sortProduct(){
        System.out.println("Chọn cách sắp xếp giá:");
        System.out.println("1. Tăng dần");
        System.out.println("2. Giảm dần");
        System.out.print("Chọn: ");
        int sortChoice = scanner.nextInt();
        scanner.nextLine();
        switch (sortChoice) {
            case 1:
                productService.sortProductsByPriceAscending();
                break;
            case 2:
                productService.sortProductsByPriceDescending();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }
    public void searchProduct(){
        productService.searchProduct();
    }
    public void readData(){
        List<Product> list = FileUtils.readFile("./data/product.csv",Product.class);
        productService.updateProducts(list);
    }
    public void writeFile(){
        FileUtils.writeFile(productService.getProducts(),"./data/product.csv");
    }


}
