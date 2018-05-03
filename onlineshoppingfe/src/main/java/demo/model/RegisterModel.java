package demo.model;

import demo.onlineshoppingbe.dto.Address;
import demo.onlineshoppingbe.dto.User;

import java.io.Serializable;

/**
 * @author b.erden
 * @created 03/05/2018
 */
public class RegisterModel implements Serializable {

    private User user;
    private Address billing;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }
}
