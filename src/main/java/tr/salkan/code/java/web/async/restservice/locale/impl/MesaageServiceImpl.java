package tr.salkan.code.java.web.async.restservice.locale.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import tr.salkan.code.java.web.async.restservice.locale.MessageService;

import java.util.Locale;

@Component
public class MesaageServiceImpl implements MessageService {

    private final MessageSource messageSource;

    @Autowired
    public MesaageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String id) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id, null, locale);
    }

    @Override
    public String getMessage(String id, Object ...params) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id, params, locale);
    }
}
