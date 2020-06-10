package tr.salkan.code.java.web.async.restservice.config;

//@Configuration
//@EnableAsync
//@ComponentScan(basePackages = "tr.salkan.code.java.web.async")
//public class ServiceAsyncConfig {
//
//
//
//}


import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import tr.salkan.code.java.web.async.restservice.errorhandling.ServiceExecutionHandlerImpl;
import tr.salkan.code.java.web.async.restservice.errorhandling.ServiceUncaughtExceptionHandler;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@ComponentScan(basePackages = "tr.salkan.code.java.web.async")
@ConfigurationProperties("task.profile")
public class ServiceAsyncConfig implements AsyncConfigurer {

    @Value("${task.profile.corePoolSize}")
    private int srvCorePoolSize;
    @Value("${task.profile.maxPoolSize}")
    private int srvMaxPoolSize;
    @Value("${task.profile.keepAliveSecond}")
    private int srvKeepAliveSecond;
    @Value("${task.profile.queueCapacity}")
    private int srvQueueCapacity;

    @Bean(name="serviceAsyncExecutor")
    public TaskExecutor smsWorkExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        threadPoolTaskExecutor.setCorePoolSize(srvCorePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(srvMaxPoolSize);
        threadPoolTaskExecutor.setKeepAliveSeconds(srvKeepAliveSecond);
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setQueueCapacity(srvQueueCapacity);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ServiceExecutionHandlerImpl());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return smsWorkExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new ServiceUncaughtExceptionHandler();
    }


}
