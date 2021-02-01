# autot
autot 是一个自动任务容器

## 任务类型
- 指定执行时间
- 延迟执行
- 函数式执行

## 如何使用
- 指定执行时间
```java

    @Test
    public void testTimestamp() throws IOException {
        TaskContainer taskContainer = TaskContainer.INSTANCE();
        taskContainer.put(new TimestampTimeType(System.currentTimeMillis() + 1000 * 20), new AbstractTask(){
            @Override
            public void job() {
                System.out.println("20s, start running");
            }
        });

        System.in.read();
    }
```

- 延迟执行
```java
    @Test
    public void testTTL() throws IOException {
        TaskContainer taskContainer = TaskContainer.INSTANCE();
        taskContainer.put(new TTLTimeType(30, TimeUnit.SECONDS), new AbstractTask() {
            @Override
            public void job() {
                System.out.println("30s, start running");
            }
        });

        System.in.read();
    }
```

- 函数式执行
```java
    /**
     * 自定义函数实现运行
     */
@Test
    public void testFunc() throws IOException {
        TaskContainer taskContainer = TaskContainer.INSTANCE();

        taskContainer.put(new CustomizedFunctionTimeType<CustomizedFunctionParam>(new CustomizedFunctionParam(5,System.currentTimeMillis() + 5 * 1000),
                rt -> {
                    if(rt.timestamp < System.currentTimeMillis()){
                        System.out.println("current index:" + rt.index);
                        return new CustomizedFunctionResult(true,System.currentTimeMillis() + 1000 * 15);
                    }else {
                        return new CustomizedFunctionResult(false);
                    }

                }),new AbstractTask() {
            @Override
            public void job() {
                System.out.println("start running");
            }
        });
        System.in.read();
    }
```