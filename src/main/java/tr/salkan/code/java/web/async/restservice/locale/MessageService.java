package tr.salkan.code.java.web.async.restservice.locale;

public interface MessageService {

    String getMessage(String id);
    String getMessage(String id, Object ...params);
}