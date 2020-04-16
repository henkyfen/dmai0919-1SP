package controller;

import container.ProductContainer;
import model.Product;
import model.ProductBundle;
import model.ProductType;

import java.util.LinkedList;

/**
 * ProductController is a class which connects the ProductContainer and the TUI
 * package' classes with the necessary methods
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 * @see model.ProductType
 * @see container.ProductContainer
 */
public class ProductController {

    public ProductController() {
    }

    public void createProduct(String name, String description, double price, String barCode, int amount) {
        ProductType p = new Product(name, description, price, barCode, amount);
        ProductContainer.getInstance().addProduct(p);
    }

    public void createProductBundle(String name, String description, double price, String barCode) {
        ProductType pb = new ProductBundle(name, description, price, barCode);
        ProductContainer.getInstance().addProduct(pb);
    }

    public void updateProduct(String name, String description, double price, String barCode, int amount) {
        Product p = (Product) ProductContainer.getInstance().findProduct(barCode);
        p.setName(name);
        p.setPrice(price);
        p.setDescription(description);
        p.setAmount(amount);
    }

    public void updateProductBundle(String name, double price, String barCode) {
        ProductType pb = ProductContainer.getInstance().findProduct(barCode);
        pb.setName(name);
        pb.setPrice(price);
        // \TODO Erik said sth about getting the list of products in it and updating THEM
    }

    public LinkedList<ProductType> getAll() {
        return ProductContainer.getInstance().getProducts();
    }

    public LinkedList<Product> getProducts() {
        LinkedList<Product> products = new LinkedList<>();

        for (ProductType item : ProductContainer.getInstance().getProducts()) {
            if (item instanceof Product) {
                products.add((Product) item);
            }
        }

        return products;
    }

    public LinkedList<ProductBundle> getProductBundles() {
        LinkedList<ProductBundle> bundles = new LinkedList<>();

        for (ProductType item : ProductContainer.getInstance().getProducts()) {
            if (item instanceof ProductBundle) {
                bundles.add((ProductBundle) item);
            }
        }

        return bundles;
    }

    public ProductType searchProduct(String barcode) {
        return ProductContainer.getInstance().findProduct(barcode);
    }

    public void deleteProduct(ProductType p) {
        ProductContainer.getInstance().deleteProduct(p);
    }
}	
