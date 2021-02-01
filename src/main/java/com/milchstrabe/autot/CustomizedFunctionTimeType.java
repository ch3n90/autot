package com.milchstrabe.autot;

import java.util.function.Function;

public class CustomizedFunctionTimeType<T extends AbstractFunctionParam> implements ITimeType<Function<T,CustomizedFunctionResult>>{

    private Function<T,CustomizedFunctionResult> func;
    private T param;


    public CustomizedFunctionTimeType(T param,Function<T,CustomizedFunctionResult> function) {
        this.func = function;
        this.param = param;
    }

    private CustomizedFunctionTimeType(Function<T, CustomizedFunctionResult> function, T param) {
        this.func = function;
        this.param = param;
    }

    @Override
    public Result j() {
        return func.andThen(result -> {
            if(result.isRun){
                this.param.index++;
                if(this.param.index < param.count){
                    this.param.timestamp = result.timestamp;
                    TaskContainer.INSTANCE().put(new CustomizedFunctionTimeType(this.func, this.param),
                            TaskContainer.INSTANCE().get(this));
                }
            }
            return result;
        }).apply(param);
    }


}
