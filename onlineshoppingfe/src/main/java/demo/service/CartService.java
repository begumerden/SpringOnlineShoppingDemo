package demo.service;

import demo.model.UserModel;
import demo.onlineshoppingbe.dao.CartLineDAO;
import demo.onlineshoppingbe.dto.Cart;
import demo.onlineshoppingbe.dto.CartLine;
import demo.onlineshoppingbe.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author b.erden
 * @created 09/05/2018
 */

@Service
public class CartService {

    private final CartLineDAO cartLineDAO;

    // we can use session because we added our user related things to userModel in global controller
    private final HttpSession httpSession;

    @Autowired
    public CartService(CartLineDAO cartLineDAO, HttpSession httpSession) {
        this.cartLineDAO = cartLineDAO;
        this.httpSession = httpSession;
    }

    public List<CartLine> getCartLines() {
        return cartLineDAO.list(this.getCart().getId());
    }


    public String updateCartLine(int cartLineId, int count) {
        CartLine cartLine = cartLineDAO.getById(cartLineId);
        if (cartLine == null) {
            return "result=error";
        } else {
            Product product = cartLine.getProduct();
            double oldTotal = cartLine.getTotal();

            // for available quantity
            if (product.getQuantity() <= count) {
                count = product.getQuantity();
            }
            cartLine.setProductCount(count);
            cartLine.setBuyingPrice(product.getUnitPrice());
            cartLine.setTotal(product.getUnitPrice() * count);
            cartLineDAO.update(cartLine);

            Cart cart = this.getCart();
            cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
            cartLineDAO.updateCart(cart);

            return "result=updated";
        }

    }

    // cart of the logged in user
    private Cart getCart() {
        return ((UserModel) httpSession.getAttribute("userModel")).getCart();
    }

    public String deleteCartLine(int cartLineId) {

        CartLine cartLine = cartLineDAO.getById(cartLineId);

        if (cartLine == null) {
            return "result=error";
        } else {
            Cart cart = this.getCart();
            cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
            cart.setCartLines(cart.getCartLines() - 1);
            cartLineDAO.updateCart(cart);

            cartLineDAO.delete(cartLine);
            return "result=deleted";
        }
    }
}
