package com.milchstrabe.autot;

public class TimestampType implements ITimeType<Long>{

    private long timestamp;

    public TimestampType(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public Result j() {
        return new Result(timestamp <= System.currentTimeMillis());
    }
}
