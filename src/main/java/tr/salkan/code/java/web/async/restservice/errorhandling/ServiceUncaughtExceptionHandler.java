package tr.salkan.code.java.web.async.restservice.errorhandling;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.util.Objects;

public class ServiceUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUncaughtExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {

        System.out.println("ServiceUncaughtExceptionHandler error : " + createError(method,params,ex.getMessage()));
        LOGGER.error("ServiceUncaughtExceptionHandler error : " + createError(method,params,ex.getMessage()));

    }

    private String createError(Method method, Object[] params, String message) {

        StringBuffer err = new StringBuffer();

        err.append(" method name :" + method.getName());

        if(Objects.nonNull(params))
        {
            for(Object o : params)
            {
                err.append(" param :" + o.toString());
            }

        }

        if(!StringUtils.isBlank(message))
        {
            err.append(" exception :" + message);
        }

        return err.toString();


    }
}