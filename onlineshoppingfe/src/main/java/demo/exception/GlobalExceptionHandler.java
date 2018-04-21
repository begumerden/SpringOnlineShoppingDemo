package demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author: b.erden
 * @date: 20.4.2018
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handlerNoHandlerFoundException() {

        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle", "Page is not Available!");
        mv.addObject("title", "404 Error Page");
        return mv;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ModelAndView categoryNotFoundException() {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle", "Category Not Found");
        mv.addObject("title", "Category Unavailable");
        return mv;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView productNotFoundException() {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle", "Product Not Found");
        mv.addObject("title", "Product Unavailable");
        return mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(Exception ex) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle", "Error occured");
        mv.addObject("errorDesc", ex.toString());
        mv.addObject("title", "Error");
        return mv;
    }
}
