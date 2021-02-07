# autot
autot 是一个自动任务容器

## 任务类型
- 指定执行时间
- 延迟执行
- 函数式执行

## 如何使用
- 周循环执行
```java
    @Test
    public void testWeekType() throws IOException {
        TaskContainer.INSTANCE().put(new WeekType(DayOfWeek.SUNDAY,15,24,2), new AbstractTask() {
            @Override
            public void job() {
                System.out.println("week type, start running");
            }
        });
        System.in.read();
    }
```

- 小时循环执行
```java
    @Test
    public void testTimeType() throws IOException {
        TaskContainer.INSTANCE().put(new TimeType(48,2), new AbstractTask() {
            @Override
            public void job() {
                System.out.println("time type, start running");
            }
        });
        System.in.read();
    }
```

- 延迟循环执行
```java
    @Test
    public void testDelayType() throws IOException {
        TaskContainer.INSTANCE().put(new DelayType(20, TimeUnit.SECONDS,-1), new AbstractTask() {
            @Override
            public void job() {
                System.out.println("delay type, start running");
            }
        });
        System.in.read();
    }
```

- 月循环执行

```java
    @Test
    public void testDateType() throws IOException {
        TaskContainer.INSTANCE().put(new DateType(7, 15,55,2), new AbstractTask() {
            @Override
            public void job() {
                System.out.println("date type, start running");
            }
        });
        System.in.read();
    }

```

- 函数循环执行

```java
   @Test
    public void testFunc() throws IOException {
        TaskContainer.INSTANCE().put(new FunctionType<FunctionParam>(new FunctionParam(5),
                rt -> {

                    if(rt.timestamp < System.currentTimeMillis()){
                        System.out.println("current index:" + rt.index);
                        return new FunctionResult(true,System.currentTimeMillis() + 1000 * 15);
                    }else {
                        return new FunctionResult(false);
                    }

                }),new AbstractTask() {
            @Override
            public void job() {
                System.out.println("func type, start running");
            }
        });
        System.in.read();
    }
```

