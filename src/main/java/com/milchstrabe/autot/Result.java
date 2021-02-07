package com.milchstrabe.autot;

public class Result {

    protected boolean isRun;
    protected boolean isRemove = true;

    public Result() {
    }

    protected Result(boolean isRun) {
        this.isRun = isRun;
    }

    public Result(boolean isRun, boolean isRemove) {
        this.isRun = isRun;
        this.isRemove = isRemove;
    }
}
