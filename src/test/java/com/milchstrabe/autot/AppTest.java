package com.milchstrabe.autot;

import org.junit.Test;

import java.io.IOException;

public class AppTest 
{

    @Test
    public void testTimestamp() throws IOException {
        TaskContainer taskContainer = TaskContainer.INSTANCE();
        taskContainer.put(new TimestampTimeType(System.currentTimeMillis() + 1000 * 20), new AbstractTask() {
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
        taskContainer.put(new TTLTimeType(30, TimeUnit.SECONDS), new AbstractTask() {
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

        taskContainer.put(new CustomizedFunctionTimeType<CustomizedFunctionParam>(new CustomizedFunctionParam(5,System.currentTimeMillis() + 5 * 1000),
                rt -> {
                    if(rt.timestamp < System.currentTimeMillis()){
                        System.out.println("current index:" + rt.index);
                        return new CustomizedFunctionResult(true,System.currentTimeMillis() + 1000 * 15);
                    }else {
                        return new CustomizedFunctionResult(false);
                    }

                }),new AbstractTask() {
            @Override
            public void job() {
                System.out.println("start running");
            }
        });
        System.in.read();
    }
}
