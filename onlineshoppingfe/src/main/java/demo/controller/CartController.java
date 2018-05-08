package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author b.erden
 * @created 08/05/2018
 */

@Controller
@RequestMapping("/cart")
public class CartController {

    @RequestMapping("/show")
    public ModelAndView showCart() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "User Cart");
        mv.addObject("showCartClicked", true);
        mv.addObject("cartLines", null); //TODO:

        return mv;
    }
}
