package tr.salkan.code.java.web.async.restservice.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.salkan.code.java.web.async.restservice.dto.enums.ProcessModulEnum;
import tr.salkan.code.java.web.async.restservice.dto.request.Person;
import tr.salkan.code.java.web.async.restservice.dto.request.RequestObject;
import tr.salkan.code.java.web.async.restservice.dto.response.ResponseObject;
import tr.salkan.code.java.web.async.restservice.processComponent.ProcessModul1Component;
import tr.salkan.code.java.web.async.restservice.processComponent.ProcessModul2Component;
import tr.salkan.code.java.web.async.restservice.processComponent.ProcessModul3Component;
import tr.salkan.code.java.web.async.restservice.service.ProcessAsyncMainService;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProcessAsyncMainServiceImpl implements ProcessAsyncMainService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ProcessAsyncMainServiceImpl.class);

    private final ProcessModul1Component processModul1Component;
    private final ProcessModul2Component processModul2Component;
    private final ProcessModul3Component processModul3Component;
    private final ProcessDBServiceImpl processDBService;

    @Autowired
    public ProcessAsyncMainServiceImpl(ProcessModul1Component processModul1Component, ProcessModul2Component processModul2Component, ProcessModul3Component processModul3Component, ProcessDBServiceImpl processDBService) {
        this.processModul1Component = processModul1Component;
        this.processModul2Component = processModul2Component;
        this.processModul3Component = processModul3Component;
        this.processDBService = processDBService;
    }

    @Override
    public void processAllWorks(RequestObject requestObject) {

        CompletableFuture<ResponseObject> p1 = processModul1Component.makeProcess1(requestObject)
                    .exceptionally(e -> {

                            System.out.println("p1 except : " + e.getMessage());
                            ResponseObject responseObject = new ResponseObject();
                            responseObject.setModul(ProcessModulEnum.MODUL1);
                            responseObject.setPersonList(new ArrayList<>());

                            return responseObject;
                    });

        CompletableFuture<ResponseObject> p2 = processModul2Component.makeProcess2(requestObject)
                .exceptionally(e -> {

                    System.out.println("p2 except : " + e.getMessage());
                    ResponseObject responseObject = new ResponseObject();
                    responseObject.setModul(ProcessModulEnum.MODUL2);
                    responseObject.setPersonList(new ArrayList<>());

                    return responseObject;
                });

        CompletableFuture<ResponseObject> p3 = processModul3Component.makeProcess3(requestObject)
                .exceptionally(e -> {

                    System.out.println("p3 except : " + e.getMessage());
                    ResponseObject responseObject = new ResponseObject();
                    responseObject.setModul(ProcessModulEnum.MODUL3);
                    responseObject.setPersonList(new ArrayList<>());

                    return responseObject;
                });

        CompletableFuture.allOf(p1, p2, p3).join();



        try {

            ResponseObject r1 = p1.get();
            ResponseObject r2 = p2.get();
            ResponseObject r3 = p3.get();

            List<Person> responseObjectList = new ArrayList<>();
            Stream.of(r1.getPersonList(), r2.getPersonList(), r3.getPersonList()).forEach(responseObjectList::addAll);

            processDBService.saveDatabase(responseObjectList);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    public static <T, R> CompletableFuture<R> allOf(List<CompletableFuture<T>> args, Function<List<T>, R> combiner) {
        final Queue<CompletableFuture<T>> queue = new LinkedList<>();
        for (CompletableFuture<T> arg : args) {
            queue.add(arg);
        }
        return aggregator(queue, new ArrayList<>(), combiner);
    }

    private static <T, R> CompletableFuture<R> aggregator(Queue<CompletableFuture<T>> queue, List<T> arg,
                                                          Function<List<T>, R> combiner) {
        if (queue.size() == 2)
            return queue.poll().thenCombine(queue.poll(), (c, d) -> {
                arg.add(c);
                arg.add(d);
                return combiner.apply(arg);
            });
        return queue.poll().thenCompose(data -> {
            arg.add(data);
            return aggregator(queue, arg, combiner);
        });
    }

//    private void getData(List<ResponseObject> allResponse, RequestObject requestObject) {
//
//        List<CompletableFuture<ResponseObject>> completableFutures = new ArrayList<>();
//
//        completableFutures.add(CompletableFuture.supplyAsync(() -> processModul1Component.makeProcess1(requestObject)));
//        completableFutures.add(CompletableFuture.supplyAsync(() -> processModul2Component.makeProcess2(requestObject)));
//        completableFutures.add(CompletableFuture.supplyAsync(() -> processModul3Component.makeProcess3(requestObject)));
//
//        CompletableFuture<List<ResponseObject>>[] cfArray = new CompletableFuture[completableFutures.size()];
//
//        cfArray = completableFutures.toArray(cfArray);
//
//        CompletableFuture.allOf(cfArray)
//                .exceptionally(ex ->
//                {
//                    ex.printStackTrace();
//                    return null;
//                }).join();
//
//        List<CompletableFuture<ResponseObject>> completedFutures = completableFutures.stream().filter(cf -> !cf.isCompletedExceptionally()).collect(Collectors.toList());
//
//        for(CompletableFuture<ResponseObject> cf : completedFutures){
//            allResponse.add(cf.join());
//        }
//    }

//    public Map<ResponseObject, List<TaskExample>> responseMap(RequestObject requestObject)
//    {
//        Map<ResponseObject, List<TaskExample>> responseMap = new HashMap<>();
//
//        try
//        {
//
//            CompletableFuture.allOf(
//                    CompletableFuture.supplyAsync(() -> processModul1Component.makeProcess1(requestObject))
//                            .thenAccept(x -> responseMap.put(x, x.getTaskExampleList())),
//
//                    CompletableFuture.supplyAsync(() -> processModul2Component.makeProcess2(requestObject))
//                            .thenAccept(x -> responseMap.put(x, x.getTaskExampleList())),
//
//                    CompletableFuture.supplyAsync(() -> processModul3Component.makeProcess3(requestObject))
//                            .thenAccept(x -> responseMap.put(x, x.getTaskExampleList())))
//                    .get(); // wait for completion of all three subtasks
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        return responseMap;
//    }

    public <T> CompletableFuture<List<T>> allOf(List<CompletableFuture<T>> futuresList) {
        CompletableFuture<Void> allFuturesResult =
                CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[futuresList.size()]));
        return allFuturesResult.thenApply(v ->
                futuresList.stream().
                        map(future -> future.join()).
                        collect(Collectors.<T>toList())
        );
    }

    //       CompletableFuture<ResponseObject> a =  CompletableFuture.
//                supplyAsync(()-> processModul2Component.makeProcess2(requestObject));


//        List<ResponseObject> responseObjectList = new ArrayList<>();
//
//        List<CompletableFuture<List<ResponseObject>>> futures = Arrays.asList(processModul1Component.makeProcess1(requestObject),
//                processModul2Component.makeProcess2(requestObject),
//                processModul3Component.makeProcess3(requestObject));
//
//
//        return CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]))
//                .thenApply(r)
//                .thenApply(v -> {
//
//                    return "v";
//                })
//                .exceptionally(throwable -> {
//                    msgResponse.setMessageStatus("ERROR");
//                    msgResponse.setErrorMessage("error");
//                    return msgResponse;
//                }).join();

//                CompletableFuture<ResponseObject> cf1 = CompletableFuture.
//                supplyAsync(()-> processModul1Component.makeProcess1(requestObject))
//                .exceptionally(e ->  null);
//
//
//        CompletableFuture<ResponseObject> cf2 = CompletableFuture.
//                supplyAsync(()-> processModul2Component.makeProcess2(requestObject)).
//                handle((result, ex) -> {
//                    if (null != ex) {
//                        LOGGER.error("processModul2Component error : " + ex.getMessage());
//                        return new ResponseObject(ProcessModulEnum.MODUL2.getName());
//                    } else {
//                        return result;
//                    }
//                });
//
//        CompletableFuture<ResponseObject> cf3 = CompletableFuture.
//                supplyAsync(()-> processModul3Component.makeProcess3(requestObject)).
//                handle((result, ex) -> {
//                    if (null != ex) {
//                        LOGGER.error("processModul3Component error : " + ex.getMessage());
//                        return new ResponseObject(ProcessModulEnum.MODUL3.getName());
//                    } else {
//                        return result;
//                    }
//                });
//
//
//
//        List<CompletableFuture<ResponseObject>> compList = new ArrayList<>();
//        compList.add(cf1);
//        compList.add(cf2);
//        compList.add(cf3);
//
//        return  allOf(compList);

//        return null;
//}
}
