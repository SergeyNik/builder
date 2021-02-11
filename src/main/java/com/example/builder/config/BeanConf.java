package com.example.builder.config;

import com.google.common.collect.ImmutableMap;
import io.github.resilience4j.core.IntervalFunction;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

@Configuration
public class BeanConf {

    private static final int CONNECT_TIMEOUT = 30000;
    private static final int REQUEST_TIMEOUT = 30000;
    private static final int SOCKET_TIMEOUT = 60000;
    private static final int MAX_TOTAL_CONNECTIONS = 20;
    private static final int ONE_SECOND_IN_MILLIS = 1000;
    private static final int DEFAULT_KEEP_ALIVE_TIME_MILLIS = 5 * ONE_SECOND_IN_MILLIS;

    @Primary
    @Bean
    public RateLimiterRegistry rateLimiter() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(3))
                .limitForPeriod(1)
//                .timeoutDuration(Duration.ofSeconds(6))
                .build();

        return RateLimiterRegistry.of(config);
    }

    @Bean
    public RateLimiterRegistry rateLimiter2() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(3))
                .limitForPeriod(1)
                .timeoutDuration(Duration.ofSeconds(6))
                .build();

        return RateLimiterRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        return RetryRegistry.of(ImmutableMap.of(
                "retry", RetryConfig.custom()
                        .maxAttempts(5)
                        .waitDuration(Duration.ofSeconds(5))
                        .retryExceptions(RuntimeException.class)
                        .intervalFunction(IntervalFunction.ofExponentialBackoff(10_000, 3, 30_000))
                        .build()
        ));
    }

    @Bean
    public PoolingHttpClientConnectionManager getPoolingConnectionManager() {
        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager();
        poolingConnectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        return poolingConnectionManager;
    }

    @Bean
    public ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy() {
        return (response, context) -> {
            HeaderElementIterator it = new BasicHeaderElementIterator
                    (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();

                if (value != null && param.equalsIgnoreCase("timeout")) {
                    return Long.parseLong(value) * ONE_SECOND_IN_MILLIS;
                }
            }
            return DEFAULT_KEEP_ALIVE_TIME_MILLIS;
        };
    }

    @Bean
    public HttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(getPoolingConnectionManager())
                .setKeepAliveStrategy(getConnectionKeepAliveStrategy())
                .setRetryHandler(new DefaultHttpRequestRetryHandler(3, true))
                .build();
    }
}
