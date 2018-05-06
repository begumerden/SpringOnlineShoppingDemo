package demo.model;

import demo.onlineshoppingbe.dto.Cart;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: b.erden
 * @date: 6.5.2018
 */

@Data
public class UserModel implements Serializable {

    private int id;
    private String fullName;
    private String email;
    private String role;
    private Cart cart;
}
