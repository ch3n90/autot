package com.milchstrabe.autot;

public class TimestampTimeType implements ITimeType<Long>{

    private long timestamp;

    public TimestampTimeType(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public Result j() {
        return new Result(timestamp <= System.currentTimeMillis());
    }
}
