package demo.model;

import demo.onlineshoppingbe.dto.Address;
import demo.onlineshoppingbe.dto.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @author b.erden
 * @created 03/05/2018
 */

@Data
public class RegisterModel implements Serializable {

    private User user;
    private Address billing;
}
