package demo.onlineshoppingbe.dao;

import demo.onlineshoppingbe.dto.Product;

import java.util.List;

/**
 * @author: b.erden
 * @date: 18.4.2018
 */
public interface ProductDAO {

    Product get(int productId);
    List<Product> list();
    boolean add(Product product);
    boolean update(Product product);
    boolean delete(Product product);

    List<Product> listActiveProducts();
    List<Product> listActiveProductsByCategory(int categoryId);
}
