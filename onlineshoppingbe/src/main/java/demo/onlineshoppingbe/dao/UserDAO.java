package demo.onlineshoppingbe.dao;

import demo.onlineshoppingbe.dto.Address;
import demo.onlineshoppingbe.dto.User;

import java.util.List;

/**
 * @author: b.erden
 * @date: 27.4.2018
 */
public interface UserDAO {

    boolean addUser(User user);

    User findUserByEmail(String email);

    boolean addAddress(Address address);
    Address getBillingAddress(int userId);
    List<Address> listShippingAddressesByUserId(int userId);

}
