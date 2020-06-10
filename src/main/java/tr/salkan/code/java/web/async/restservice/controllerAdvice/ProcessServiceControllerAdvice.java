package tr.salkan.code.java.web.async.restservice.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import tr.salkan.code.java.web.async.restservice.errorhandling.ErrorResponse;
import tr.salkan.code.java.web.async.restservice.errorhandling.ServiceCustomException;

@ControllerAdvice
public class ProcessServiceControllerAdvice extends ExceptionHandlerExceptionResolver {

    @ExceptionHandler(ServiceCustomException.class)
    public ResponseEntity<ErrorResponse> exceptionToDoHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage("ServiceCustomException - Service is error. " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Exception - Service is error.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}