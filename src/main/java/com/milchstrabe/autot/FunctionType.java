package com.milchstrabe.autot;

import java.util.function.Function;

public class FunctionType<T extends AbstractFunctionParam> implements ITimeType<Function<T,FunctionResult>>{

    private Function<T,FunctionResult> func;
    private T param;


    public FunctionType(T param,Function<T,FunctionResult> function) {
        this.func = function;
        this.param = param;
    }

    private FunctionType(Function<T, FunctionResult> function, T param) {
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
                    TaskContainer.INSTANCE().put(new FunctionType(this.func, this.param),
                            TaskContainer.INSTANCE().get(this));
                }
            }
            return result;
        }).apply(param);
    }


}
