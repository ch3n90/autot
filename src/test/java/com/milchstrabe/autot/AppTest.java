package com.milchstrabe.autot;

import org.junit.Test;

import java.io.IOException;

public class AppTest 
{

    @Test
    public void testTimestamp() throws IOException {
        TaskContainer taskContainer = TaskContainer.INSTANCE();
        taskContainer.put(new TimestampType(System.currentTimeMillis() + 1000 * 1000), new AbstractTask() {
            @Override
            public void job() {
                System.out.println("20s, start running");
            }
        });

        System.in.read();
    }

    @Test
    public void testTTL() throws IOException {
        TaskContainer taskContainer = TaskContainer.INSTANCE();
        taskContainer.put(new DelayType(30, TimeUnit.SECONDS), new AbstractTask() {
            @Override
            public void job() {
                System.out.println("30s, start running");
            }
        });

        System.in.read();
    }

    @Test
    public void testFunc() throws IOException {
        TaskContainer taskContainer = TaskContainer.INSTANCE();

        taskContainer.put(new FunctionType<FunctionParam>(new FunctionParam(5,System.currentTimeMillis() + 5 * 1000),
                rt -> {
                    if(rt.timestamp < System.currentTimeMillis()){
                        System.out.println("current index:" + rt.index);
                        return new FunctionResult(true,System.currentTimeMillis() + 1000 * 15);
                    }else {
                        return new FunctionResult(false);
                    }

                }),new AbstractTask() {
            @Override
            public void job() {
                System.out.println("start running");
            }
        });
        System.in.read();
    }

    @Test
    public void testDay() throws IOException {
        TaskContainer.INSTANCE().put(new DayType(2,true), new AbstractTask() {
            @Override
            public void job() {
                System.out.println("20, start running");
            }
        });

        System.in.read();
    }
}
