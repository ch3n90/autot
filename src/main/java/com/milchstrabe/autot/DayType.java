package com.milchstrabe.autot;

import java.time.LocalDate;

public class DayType implements ITimeType<Integer>{

    private LocalDate localDate;
    private boolean repeat;

    public DayType(int day,boolean repeat){
        LocalDate now = LocalDate.now();
        int dayOfMonth = now.getDayOfMonth();
        if(dayOfMonth >= day){
            this.localDate = LocalDate.now().withDayOfMonth(day);
        }else{
            this.localDate = LocalDate.now().withDayOfMonth(day).plusMonths(1);
        }
        this.repeat = repeat;
    }

    private DayType(LocalDate localDate,boolean repeat){
        this.localDate = localDate;
        this.repeat = repeat;
    }

    @Override
    public Result j() {
        boolean result = LocalDate.now().compareTo(this.localDate) == 0;
        if(result && this.repeat){
            TaskContainer.INSTANCE().put(new DayType(LocalDate.now().plusMonths(1),this.repeat),TaskContainer.INSTANCE().get(this));
        }
        return new Result(result);
    }
}
