package com.milchstrabe.autot;

public class CustomizedFunctionResult extends Result{

    protected long timestamp;

    public CustomizedFunctionResult(boolean isRun) {
        super(isRun);
    }

    public CustomizedFunctionResult(boolean isRun, long timestamp) {
        super(isRun);
        this.timestamp = timestamp;
    }
}
