package com.liangzhicheng.config.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 在使用spring中，已经给我们做了很好的支持。只要要@EnableAsync就可以使用多线程
 * 通过spring给我们提供的ThreadPoolTaskExecutor就可以使用线程池
 *
 * 在未使用ScheduleTaskConfig配置类、ScheduleTaskExecutor中方法未贴@Async注解，定时器执行系统打印的信息都是scheduling-1，Springboot定时器默认的是单线程
 * 通过使用ScheduleTaskConfig配置类，贴上@EnableAsync注解就可以使用多线程，通过创建ThreadPoolTaskExecutor实例初始化
 */
@EnableAsync
@Configuration
public class ScheduleTaskConfig {

    private static final int CORE_POOL_SIZE = 10; //默认线程数
    private static final int MAX_POOL_SIZE = 100; //最大线程数
    private static final int KEEP_ALIVE_TIME = 10; //允许线程空闲时间（单位：默认为秒），十秒后就把线程关闭
    private static final int QUEUE_CAPACITY = 200; //缓冲队列数
    private static final String THREAD_NAME_PREFIX = "thread-demo-"; //线程池名前缀

    @Bean("threadPoolTaskExecutor") //bean的名称，默认为首字母小写的方法名
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        //线程池拒绝任务的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化
        executor.initialize();
        return executor;
    }

}
