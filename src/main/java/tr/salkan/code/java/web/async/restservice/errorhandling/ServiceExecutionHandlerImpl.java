package tr.salkan.code.java.web.async.restservice.errorhandling;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ServiceExecutionHandlerImpl implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        try {
            executor.getQueue().put(r);
        }
        catch (InterruptedException e) {

            System.out.println("Service Thread RejectedExecutionHandler"  + e.getMessage());

            throw new RejectedExecutionException("Service Thread RejectedExecutionHandler", e);
        }

    }
}