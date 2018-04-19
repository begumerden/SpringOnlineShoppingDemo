package demo.onlineshoppingfe.controller;

import demo.onlineshoppingbe.dao.ProductDAO;
import demo.onlineshoppingbe.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author b.erden
 * @created 19.04.2018
 */

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

    private final ProductDAO productDAO;

    @Autowired
    public JsonDataController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @RequestMapping("/all/products")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productDAO.listActiveProducts();
    }

    @RequestMapping("/category/{id}/products")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable int id) {
        return productDAO.listActiveProductsByCategory(id);
    }

}
