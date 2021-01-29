package com.milchstrabe.autot;

import java.util.function.Function;

public class CustomizedFunctionTimeType implements ITimeType<Function<Long,CustomizedFunctionResult>>{

    private Function<Long,CustomizedFunctionResult> function;
    private int times = 0;
    private CustomizedFunctionParam param;


    public CustomizedFunctionTimeType(CustomizedFunctionParam param,Function<Long,CustomizedFunctionResult> function) {
        this.function = function;
        this.param = param;
    }

    private CustomizedFunctionTimeType(Function<Long, CustomizedFunctionResult> function, int times, CustomizedFunctionParam param) {
        this.function = function;
        this.times = times;
        this.param = param;
    }

    @Override
    public Result j() {
        return function.andThen(result -> {
            if(result.isRun()){
                times++;
                if(times < param.getTargetTimes()){
                    this.param.setTimestamp(result.getNextTimestamp());
                    TaskContainer.INSTANCE().put(new CustomizedFunctionTimeType(this.function,this.times,this.param),
                            TaskContainer.INSTANCE().get(this));
                }
            }
            return result;
        }).apply(param.getTimestamp());
    }


}
