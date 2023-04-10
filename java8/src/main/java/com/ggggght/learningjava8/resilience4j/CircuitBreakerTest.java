package com.ggggght.learningjava8.resilience4j;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import java.time.Duration;
import java.util.function.Supplier;

public class CircuitBreakerTest {
    public static void main(String[] args) throws Throwable {
        // 1. 创建熔断器
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("circuitBreaker");
        // 2. 创建重试器
        RetryConfig retryConfig = RetryConfig.custom().maxAttempts(2).waitDuration(Duration.ofSeconds(3))
            .retryOnResult(p -> (p instanceof ResultVO<?> && ((ResultVO<?>) p).code != 0))
            .build();

        Retry retry = Retry.of("retry", retryConfig);
        // 3. bulkhead
        Bulkhead bulkhead = Bulkhead.ofDefaults("backendService");
        Supplier<ResultVO<?>> supplier = () -> new ResultVO<>("he");
        Supplier<ResultVO<?>> decorate = Decorators.ofSupplier(supplier)
            .withCircuitBreaker(circuitBreaker)
            .withBulkhead(bulkhead)
            .withRetry(retry)
            .decorate();
        ResultVO<?> vo1 = decorate.get();
        System.out.println(vo1);

//        Try.ofSupplier(decoratedSupplier)
//            .recover(throwable -> "hello from recovery")
//            .onFailure(throwable -> System.out.println("error: " + throwable.getMessage()))
//            .onSuccess(System.out::println);

        System.out.println("===");
        ResultVO<?> vo = retry.executeSupplier(supplier);
        System.out.println(vo);
    }

    static class ResultVO<T> {
        T data;
        String msg;
        int code;

        public ResultVO(T str) {
            System.out.println("ResultVO#ResultVO");
            if (str instanceof String && ((String) str).length() > 3) {
                this.data = str;
                this.code = 0;
                this.msg = "ok";
            } else {
                this.code = -1;
            }
        }

        @Override public String toString() {
            return "ResultVO{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                '}';
        }
    }
}
