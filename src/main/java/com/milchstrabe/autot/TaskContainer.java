package com.milchstrabe.autot;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;

public class TaskContainer<T extends AbstractTask>{

    private final Map<ITimeType,AbstractTask> container = new ConcurrentHashMap();

    private ExecutorService workExecutor;

    private ExecutorService bossExecutor = Executors.newSingleThreadExecutor();

    private static final int LENGTH = 10;

    private static final int RANGE = 1000;

    public synchronized void put(ITimeType key,T task){
        container.put(key,task);
        this.notifyAll();
    }

    public TaskContainer(int nThreads){
        workExecutor = Executors.newFixedThreadPool(nThreads);
        bossExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    l00p();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    private static class Instance{
//        private static  TaskContainer instance = new TaskContainer();
//    }

//    public static TaskContainer INSTANCE(){
//        return Instance.instance;
//    }

    private synchronized void l00p() throws InterruptedException {
        while (true){
            if(container.size() == 0){
                System.out.printf("阻塞boss线程");
               this.wait();
            }
            int times = 10;
            long mark1 = System.currentTimeMillis();
            do{
                container.entrySet().stream().forEach( entry -> {
                    ITimeType key = entry.getKey();
                    if(key.j()){
                        workExecutor.execute(entry.getValue());
                        container.remove(key);
                    }
                 });
                times++;
            }while (times <= LENGTH);
            long val = System.currentTimeMillis() - mark1;
            if(val <= RANGE){
                TimeUnit.MILLISECONDS.sleep(val);
            }
        }
    }
}
