package demo.onlineshoppingbe.dao;

import demo.onlineshoppingbe.dto.Cart;
import demo.onlineshoppingbe.dto.CartLine;

import java.util.List;

/**
 * @author b.erden
 * @created 08/05/2018
 */
public interface CartLineDAO {

    CartLine getById(int id);

    boolean add(CartLine cartLine);

    boolean update(CartLine cartLine);

    boolean delete(CartLine cartLine);

    List<CartLine> list(int cartId);

    List<CartLine> listAvailable(int cartId);

    CartLine getByCardAndProduct(int cartId, int productId);

    boolean updateCart(Cart cart);
}
