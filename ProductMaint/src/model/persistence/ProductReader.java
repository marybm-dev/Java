// 2015-Mar-02 added notDuplicate(Product p) method

package model.persistence;

import model.Product;

public interface ProductReader
{
    Product getProduct(String code);
    String getProductsString();
    boolean notDuplicate(Product p);
}