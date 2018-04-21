package demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: b.erden
 * @date: 20.4.2018
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Category")
public class CategoryNotFoundException extends RuntimeException {

}
