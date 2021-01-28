package com.milchstrabe.autot;

import org.junit.Test;

import java.io.IOException;

public class AppTest 
{

    @Test
    public void test() throws IOException {
        TaskContainer taskContainer = TaskContainer.INSTANCE();
        taskContainer.put(new TTL(30, TimeUnit.SECONDS), new AbstractTask() {
            @Override
            public void job() {
                System.out.println("30s, start running");
            }
        });
        System.in.read();
    }
}
