package demo.service;

import demo.model.UserModel;
import demo.onlineshoppingbe.dao.CartLineDAO;
import demo.onlineshoppingbe.dao.ProductDAO;
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
    private final ProductDAO productDAO;

    // we can use session because we added our user related things to userModel in global controller
    private final HttpSession httpSession;

    @Autowired
    public CartService(CartLineDAO cartLineDAO, ProductDAO productDAO, HttpSession httpSession) {
        this.cartLineDAO = cartLineDAO;
        this.productDAO = productDAO;
        this.httpSession = httpSession;
    }

    public List<CartLine> getCartLines() {
        return cartLineDAO.list(this.getCart().getId());
    }


    public String manageCartLine(int cartLineId, int count) {
        CartLine cartLine = cartLineDAO.getById(cartLineId);
        if (cartLine == null) {
            return "result=error";
        }

        Product product = cartLine.getProduct();
        double oldTotal = cartLine.getTotal();

        // for available quantity
        if (product.getQuantity() < count) {
            return "result=unavailable";
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

    // cart of the logged in user
    private Cart getCart() {
        return ((UserModel) httpSession.getAttribute("userModel")).getCart();
    }

    public String deleteCartLine(int cartLineId) {

        CartLine cartLine = cartLineDAO.getById(cartLineId);

        if (cartLine == null) {
            return "result=error";
        }
        Cart cart = this.getCart();
        cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
        cart.setCartLines(cart.getCartLines() - 1);
        cartLineDAO.updateCart(cart);
        cartLineDAO.delete(cartLine);

        return "result=deleted";
    }

    public String addCartLine(int productId) {
        Cart cart = this.getCart();

        // for checking if already added
        CartLine cartLine = cartLineDAO.getByCardAndProduct(cart.getId(), productId);
        if (cartLine == null) {
            cartLine = new CartLine();
            Product product = productDAO.get(productId);
            cartLine.setProduct(product);
            cartLine.setCartId(cart.getId());
            cartLine.setBuyingPrice(product.getUnitPrice());
            cartLine.setProductCount(1);
            cartLine.setTotal(product.getUnitPrice());
            cartLine.setAvailable(true);

            cartLineDAO.add(cartLine);

            cart.setCartLines(cart.getCartLines() + 1);
            cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
            cartLineDAO.updateCart(cart);
            return "result_added";
        }
        // checking maximum count
        if (cartLine.getProductCount() < 5) {
            this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);
        } else {
            return "result=maximum";
        }

        return null;
    }
}
