package demo.controller;

import demo.model.UserModel;
import demo.onlineshoppingbe.dao.UserDAO;
import demo.onlineshoppingbe.dto.User;
import demo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * @author: b.erden
 * @date: 6.5.2018
 */

@ControllerAdvice
public class GlobalController {

    private final HttpSession httpSession;
    private final UserDAO userDAO;

    @Autowired
    public GlobalController(HttpSession httpSession, UserDAO userDAO) {
        this.httpSession = httpSession;
        this.userDAO = userDAO;
    }

    @ModelAttribute(name = "userModel")
    public UserModel getUserModel() {
        if (httpSession.getAttribute("userModel") == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userDAO.findUserByEmail(authentication.getName());
            if (user != null) {
                UserModel userModel = new UserModel();
                userModel.setId(user.getId());
                userModel.setEmail(user.getEmail());
                userModel.setRole(user.getRole());
                userModel.setFullName(String.format("%s-%s", user.getFirstName(), user.getLastName()));
                if(user.getRole().equals(Constants.Roles.USER)){
                    userModel.setCart(user.getCart());
                }

                httpSession.setAttribute("userModel",userModel);

                return userModel;
            }
        }
        return (UserModel) httpSession.getAttribute("userModel");
    }
}
