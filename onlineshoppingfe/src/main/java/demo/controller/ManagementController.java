package demo.controller;

import demo.onlineshoppingbe.dao.CategoryDAO;
import demo.onlineshoppingbe.dao.ProductDAO;
import demo.onlineshoppingbe.dto.Category;
import demo.onlineshoppingbe.dto.Product;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author: b.erden
 * @date: 21.4.2018
 */

@Controller
@RequestMapping("/manage")
public class ManagementController {

    private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

    private final CategoryDAO categoryDAO;
    private final ProductDAO productDAO;

    @Autowired
    public ManagementController(CategoryDAO categoryDAO, ProductDAO productDAO) {
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
    }

    @GetMapping("/products")
    public ModelAndView showManageProducts(@RequestParam(required = false) String operation) {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("manageProductsClicked", true);
        mv.addObject("title", "Product Management");

        Product product = new Product();
        product.setSupplierId(1);
        product.setActive(true);

        mv.addObject("product", product);

        if (StringUtils.isNotBlank(operation) && operation.equals("product")) {
            mv.addObject("message", "Product saved successfully.");
        }

        return mv;
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryDAO.list();
    }


    @PostMapping(value = "/products")
    public String saveProduct(@ModelAttribute @Valid Product product, BindingResult bindingResult, Model model, HttpServletRequest request) {
        logger.info(product.toString());

        if (bindingResult.hasErrors()) {
            model.addAttribute("manageProductsClicked", true);
            model.addAttribute("title", "Product Management");
            model.addAttribute("message", "Validation Errors!");

            return "page";
        }

        if(product.getId() == 0 ){
            productDAO.add(product);
        }else{
            productDAO.update(product);
        }

        //TODO: will be done later
       /* if (!ObjectUtils.isEmpty(product.getFile())
                && StringUtils.isNotBlank(product.getFile().getOriginalFilename())) {
            FileUploadUtil.uploadFile(request, product.getFile(), product.getCode());
        }*/

        return "redirect:/manage/products?operation=product";
    }

    @PostMapping(value = "/product/{id}/activation")
    @ResponseBody
    public String productActivation(@PathVariable int id) {

        Product product = productDAO.get(id);
        boolean active = product.isActive();
        product.setActive(!active);
        productDAO.update(product);

        return active ? "Deactivation Successful with product: " + product.getId() : "Activation Successful! with product: " + product.getId();

    }

    @GetMapping(value = "/{id}/product")
    public ModelAndView showEditProducts(@PathVariable int id){
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("manageProductsClicked", true);
        mv.addObject("title", "Product Management");

        Product product = productDAO.get(id);

        mv.addObject("product", product);

        return mv;
    }
}
