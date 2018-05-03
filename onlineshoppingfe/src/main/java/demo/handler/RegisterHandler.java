package demo.handler;

import demo.model.RegisterModel;
import demo.onlineshoppingbe.dao.UserDAO;
import demo.onlineshoppingbe.dto.Address;
import demo.onlineshoppingbe.dto.Cart;
import demo.onlineshoppingbe.dto.User;
import demo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;


/**
 * @author b.erden
 * @created 03/05/2018
 */
@Component
public class RegisterHandler {

    private final UserDAO userDAO;

    @Autowired
    public RegisterHandler(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public RegisterModel init() {
        return new RegisterModel();
    }

    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
    }

    public void addBilling(RegisterModel registerModel, Address billing) {
        registerModel.setBilling(billing);
    }

    public String saveAll(RegisterModel model) {
        String transitionValue = "success";

        User user = model.getUser();
        if (user.getRole().equals(Constants.Roles.USER)) {
            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        userDAO.addUser(user);

        Address billing = model.getBilling();
        billing.setUser(user);
        billing.setBilling(true);
        userDAO.addAddress(billing);

        return transitionValue;
    }

    public String validateUser(User user, MessageContext messageContext) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            messageContext.addMessage(new MessageBuilder().error()
                                                          .source("confirmPassword")
                                                          .defaultText("Passwords don't match!").build());
            return "failure";
        }


        User userByEmail = userDAO.findUserByEmail(user.getEmail());
        if (userByEmail != null) {
            messageContext.addMessage(new MessageBuilder().error()
                                                          .source("email")
                                                          .defaultText("Email is already used! Please choose another email")
                                                          .build());
            return "failure";
        }

        return "success";
    }

}
