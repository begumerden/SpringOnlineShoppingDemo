package demo.controller;

import demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author b.erden
 * @created 08/05/2018
 */

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping("/show")
    public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
        ModelAndView mv = new ModelAndView("page");

        if (result != null) {
            switch (result) {
                case "updated":
                    mv.addObject("message", "CartLine updated successfully.");
                    break;
                case "deleted":
                    mv.addObject("message", "CartLine deleted successfully.");
                    break;
                case "error":
                    mv.addObject("message", "Error");
                    break;
            }
        }

        mv.addObject("title", "User Cart");
        mv.addObject("showCartClicked", true);
        mv.addObject("cartLines", cartService.getCartLines());

        return mv;
    }

    @RequestMapping("/{cartLineId}/update")
    public String updateCart(@PathVariable int cartLineId, @RequestParam int count) {
        String response = cartService.updateCartLine(cartLineId, count);

        return "redirect:/cart/show?" + response;
    }

    @RequestMapping("/{cartLineId}/delete")
    public String deleteCart(@PathVariable int cartLineId) {
        String response = cartService.deleteCartLine(cartLineId);

        return "redirect:/cart/show?" + response;
    }
}
