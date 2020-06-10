# spring-boot-asynchron-rest-service

  **JDK VERSION :** JDK 11 - [AMAZON CORRETTO](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
  
---

Maven build plugins for JDK 11
------
```java
  <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <release>11</release>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
   </plugins>
```
---
Configuration
------

* @EnableAsync and i want to use AsyncConfigurer implementation

```java
    
    @Configuration
    @EnableAsync
    @ComponentScan(basePackages = "tr.salkan.code.java.web.async")
    @ConfigurationProperties("task.profile")
    public class ServiceAsyncConfig implements AsyncConfigurer {
    
```

* Define ThreadPoolTaskExecutor with corePoolSize,maxPoolSize and queueCapacity

```java
    
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
    
```
* if task is fill up -> using RejectedExecutionHandler

```java
    
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
    
```

* catch UncaughtException -> using AsyncUncaughtExceptionHandler

```java

      @Override
      public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
          return new ServiceUncaughtExceptionHandler();
      }
      
      public class ServiceUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
      
      
          ...
      
      }
      

```

* Define CompleTableFuture<Object> and exceptionally feature (if you want handle() function)

```java

            CompletableFuture<ResponseObject> p1 = processModul1Component.makeProcess1(requestObject)
                    .exceptionally(e -> {

                            System.out.println("p1 except : " + e.getMessage());
                            ResponseObject responseObject = new ResponseObject();
                            responseObject.setModul(ProcessModulEnum.MODUL1);
                            responseObject.setPersonList(new ArrayList<>());

                            return responseObject;
                    });
                    

```

* Defined asynchron running function (Return CompleTableFuture<Object> )

```java

    @Async("serviceAsyncExecutor")
    public CompletableFuture<ResponseObject> makeProcess1(RequestObject requestObject) {
    
        ...

        return CompletableFuture.completedFuture(responseObject);
    
    }

```

* Blocking thread until all CompletableFutures is done ( .join() )

```java

    // blocking code until p1,p2,p3 completablefuture is done
    CompletableFuture.allOf(p1, p2, p3).join();
    
    //if done, running below code
    other codes

```

* Get Object from Completablefuture<Object>  ( .get() )

```java
      
       try {

            ResponseObject r1 = p1.get();
            ResponseObject r2 = p2.get();
            ResponseObject r3 = p3.get();
             
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


```
---
Other Configuration
------

* Define LocaleResolver and ReloadableResourceBundleMessageSource configuration to read messages_tr_TR

* Define ExceptionHandlerExceptionResolver class extending with @ControllerAdvice

* Define custom @JsonSerializer and @JsonDeserializer

```java

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime processStartTime;

    @JsonSerialize(using = CustomGenericSerializer.class)
    @JsonDeserialize(using = CustomGenericDeSerializer.class)
    private EntityExampleObject entityExampleObject;

```

