package com.milchstrabe.autot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TaskContainer<T extends AbstractTask>{

    private final Map<ITimeType,AbstractTask> container = new ConcurrentHashMap();

    private static final int LENGTH = 10;

    private static final int RANGE = 1000;

    public void put(ITimeType key,T task){
        container.put(key,task);
    }

    private TaskContainer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    l00p();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static class Instance{
        private static  TaskContainer instance = new TaskContainer();
    }

    public static TaskContainer INSTANCE(){
        return Instance.instance;
    }

    private void l00p() throws InterruptedException {
        while (true){
            if(container.size() == 0){
                System.out.printf("container size %d,sleep:%s\r\n",container.size(),"1000ms");
                TimeUnit.SECONDS.sleep(1);
                continue;
            }
            int times = 10;
            long mark1 = System.currentTimeMillis();
            do{
                container.entrySet().stream().forEach( entry -> {
                    ITimeType key = entry.getKey();
                    if(key.j()){
                        new Thread(entry.getValue()).start();
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
