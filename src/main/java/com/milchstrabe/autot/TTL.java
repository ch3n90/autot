package com.milchstrabe.autot;

public class TTL implements ITimeType<Integer>{

    private long now = System.currentTimeMillis();

    private int delay;

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public TTL(int delay, TimeUnit timeUnit) {
        this.delay = delay;
        this.timeUnit = timeUnit;
    }

    public TTL(int delay) {
        this.delay = delay;
    }

    @Override
    public boolean j() {
        return delay * timeUnit.val() + now <= System.currentTimeMillis();
    }
}
