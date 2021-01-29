package com.milchstrabe.autot;

public class TTLTimeType implements ITimeType<Integer>{

    private long now = System.currentTimeMillis();

    private int delay;

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public TTLTimeType(int delay, TimeUnit timeUnit) {
        this.delay = delay;
        this.timeUnit = timeUnit;
    }

    public TTLTimeType(int delay) {
        this.delay = delay;
    }

    @Override
    public Result j() {
        return new Result(delay * timeUnit.val() + now <= System.currentTimeMillis());
    }
}
