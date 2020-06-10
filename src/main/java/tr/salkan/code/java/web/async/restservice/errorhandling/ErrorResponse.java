package tr.salkan.code.java.web.async.restservice.errorhandling;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private int errorCode;
    private String message;

    public ErrorResponse(int errorCode, String message) {

        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorResponse() {
    }

    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}