package com.milchstrabe.autot;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekType implements ITimeType<DayOfWeek>{

    private DayOfWeek week;
    private boolean repeat;

    public WeekType(DayOfWeek week,boolean repeat){
        this.week = week;
        this.repeat = repeat;
    }

    @Override
    public Result j() {
        boolean result = LocalDate.now().getDayOfWeek().compareTo(week) == 0;
        if(result && this.repeat){

        }
        return new Result(LocalDate.now().getDayOfWeek().getValue() == week.getValue() );
    }
}
