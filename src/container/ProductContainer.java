package container;

import model.ProductType;

import java.util.LinkedList;

/**
 * ProductContainer is a singleton container which contains a LinkedList
 * of the products of the company
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-04
 * @see model.ProductType;
 */
public class ProductContainer {
    private static ProductContainer instance;
    private final LinkedList<ProductType> products;

    private ProductContainer() {
        products = new LinkedList<>();
    }

    public static ProductContainer getInstance() {
        if (instance == null) {
            instance = new ProductContainer();

        }
        return instance;
    }

    /*
     * This method is used to find a product based on it's barcode
     * @param barcode	This is the barcode located on the product
     * @return ProductType This returns the ProductType if found. Returns null if not found
     */
    public ProductType findProduct(String barCode) {
        barCode = barCode.trim();
        for (ProductType productType : products) {
            String code = productType.getBarCode();
            if (code.equals(barCode)) {
                return productType;
            }
        }
        return null;
    }

    /*
     * This method is used to add a new productType to the container.
     * @param productType This is the productType to be added to the container
     * @return boolean	This returns true if the productType is added successfully
     */
    public void addProduct(ProductType productType) {
        if (productType != null) {
            products.add(productType);
        }
    }

    /*
     * This method is used to search for products based on their names
     * @param search This is the string the employee searches for
     * @return LinkedList<ProductType> This returns a LinkedList of all the ProductTypes which names contain the given string
     */
    public LinkedList<ProductType> searchProduct(String search) {
        LinkedList<ProductType> list = new LinkedList<>();
        for (ProductType productType : products) {
            String name = productType.getName();
            if (name.contains(search)) {
                list.add(productType);
            }
        }
        if (list.size() == 0) return null;
        else return list;
    }

    public LinkedList<ProductType> getProducts() {
        return this.products;
    }

    public void deleteProduct(ProductType p) {
        products.remove(p);

    }
}
