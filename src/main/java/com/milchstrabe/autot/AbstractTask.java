package com.milchstrabe.autot;

public abstract class AbstractTask implements Runnable{

    @Override
    public void run() {
        job();
    }

    public abstract void job();
}
