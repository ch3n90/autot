package com.milchstrabe.autot;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskContainer<T extends AbstractTask>{

    private final Map<ITimeType,AbstractTask> container = new ConcurrentHashMap();

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    private ExecutorService workExecutor;

    private ExecutorService bossExecutor = Executors.newSingleThreadExecutor();

    private static final int LENGTH = 10;

    private static final int RANGE = 1000;

    public void put(ITimeType key,T task){
        boolean is0 = container.size() == 0 ? true : false;
        container.put(key,task);
        if(is0){
            lock.lock();
            condition.signalAll();
            lock.unlock();
        }
    }

    public TaskContainer(int nThreads){
        workExecutor = Executors.newFixedThreadPool(nThreads);
        bossExecutor.execute(new Runnable() {
            @Override
            public void run() {
                l00p();
            }
        });
    }

//    private static class Instance{
//        private static  TaskContainer instance = new TaskContainer();
//    }

//    public static TaskContainer INSTANCE(){
//        return Instance.instance;
//    }

    private void l00p()  {
        try {
            lock.lock();
            while (true) {
                if (container.size() == 0) {
                    condition.await();
                }else {
                    long mark1 = System.currentTimeMillis();
                    int times = 1;
                    do {
                        container.entrySet().stream().forEach(entry -> {
                            ITimeType key = entry.getKey();
                            if (key.j()) {
                                workExecutor.execute(entry.getValue());
                                container.remove(key);
                            }
                        });
                        times++;
                    } while (times <= LENGTH);
                    long val = System.currentTimeMillis() - mark1;
                    if (val <= RANGE) {
                        TimeUnit.MILLISECONDS.sleep(val);
                    }
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}