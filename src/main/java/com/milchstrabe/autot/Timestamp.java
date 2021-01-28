package com.milchstrabe.autot;

public class Timestamp implements ITimeType<Long>{

    private long timestamp;

    public Timestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean j() {
        return timestamp <= System.currentTimeMillis();
    }
}
