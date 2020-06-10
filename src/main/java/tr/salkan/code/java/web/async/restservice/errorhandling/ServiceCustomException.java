package tr.salkan.code.java.web.async.restservice.errorhandling;

import java.io.IOException;

public class ServiceCustomException extends IOException {

    private static final long serialVersionUID = 3192932711425785506L;

    private String errorMessage;

    public ServiceCustomException() {
        super();
    }

    public ServiceCustomException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
