package demo.onlineshoppingfe.controller;

import demo.onlineshoppingbe.dao.CategoryDAO;
import demo.onlineshoppingbe.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: b.erden
 * @date: 14.4.2018.
 */

@Controller
public class PageController {

    private final CategoryDAO categoryDAO;

    @Autowired
    public PageController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Home");
        // adding categories
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
        mv.addObject("title", "All Products");
        mv.addObject("categories", categoryDAO.list());
        mv.addObject("allProductPageClicked", true);

        return mv;
    }

    @RequestMapping(value = "/list/category/{id}/products")
    public ModelAndView listCategoryProducts(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("page");
        Category category = categoryDAO.getById(id);
        mv.addObject("title", category.getName());
        mv.addObject("categories", categoryDAO.list());
        mv.addObject("category", category);
        mv.addObject("categoryProductsClicked", true);

        return mv;
    }
}
