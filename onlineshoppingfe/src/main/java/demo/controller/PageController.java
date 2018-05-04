package demo.controller;

import demo.exception.CategoryNotFoundException;
import demo.exception.ProductNotFoundException;
import demo.onlineshoppingbe.dao.CategoryDAO;
import demo.onlineshoppingbe.dao.ProductDAO;
import demo.onlineshoppingbe.dto.Category;
import demo.onlineshoppingbe.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: b.erden
 * @date: 14.4.2018.
 */

@Controller
public class PageController {

    private final CategoryDAO categoryDAO;
    private final ProductDAO productDAO;

    @Autowired
    public PageController(CategoryDAO categoryDAO, ProductDAO productDAO) {
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
    }

    @RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("page"); //-> masterPage
        mv.addObject("title", "Home");
        mv.addObject("categories", categoryDAO.list());
        mv.addObject("homePageClicked", true);

        return mv;
    }

    @RequestMapping(value = "/about")
    public ModelAndView about() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "About Us");
        mv.addObject("aboutPageClicked", true);

        return mv;
    }

    @RequestMapping(value = "/contact")
    public ModelAndView contact() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Contact Us");
        mv.addObject("contactPageClicked", true);

        return mv;
    }

    @RequestMapping(value = "/list/all/products")
    public ModelAndView listAllProducts() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Products");
        mv.addObject("categories", categoryDAO.list());
        mv.addObject("allProductPageClicked", true);

        return mv;
    }

    @RequestMapping(value = "/list/category/{id}/products")
    public ModelAndView listCategoryProducts(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("page");
        Category category = categoryDAO.getById(id);
        // If "handlerException" wasn't defined
        // this exception one can be handled without throwing explicity
        if (category == null) {
            throw new CategoryNotFoundException();
        }
        mv.addObject("title", category.getName());
        mv.addObject("categories", categoryDAO.list());
        mv.addObject("category", category);
        mv.addObject("categoryProductsClicked", true);

        return mv;
    }

    @RequestMapping(value = "/show/{id}/product")
    public ModelAndView showProductById(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("page");
        Product product = productDAO.get(id);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        // updating view count
        product.setViews(product.getViews() + 1);
        productDAO.update(product);

        mv.addObject("title", product.getName());
        mv.addObject("product", product);
        mv.addObject("showProductClicked", true);

        return mv;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(name = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("login");

        if(error != null){
            mv.addObject("message", "Invalid Username or Password");
        }

        mv.addObject("title", "Login");

        return mv;
    }
}
