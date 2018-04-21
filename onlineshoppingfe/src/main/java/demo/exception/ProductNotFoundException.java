package demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: b.erden
 * @date: 21.4.2018
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Product")
public class ProductNotFoundException extends RuntimeException {

    private String message;

    public ProductNotFoundException(){
        this("Product is not available");
    }

    public ProductNotFoundException(String message){
        this.message = System.currentTimeMillis() +":"+ message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
