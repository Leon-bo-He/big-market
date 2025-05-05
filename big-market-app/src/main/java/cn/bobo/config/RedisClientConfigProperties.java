package cn.bobo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description Redis connection configuration
 *              <a href="https://github.com/redisson/redisson/tree/master/redisson-spring-boot-starter">
 *              redisson-spring-boot-starter</a>
 * @created 2023-12-23 09:51
 */
@Data
@ConfigurationProperties(prefix = "redis.sdk.config", ignoreInvalidFields = true)
public class RedisClientConfigProperties {

    /** host:ip */
    private String host;

    /** Port */
    private int port;

    /** Password */
    private String password;

    /** Connection pool size, default is 64 */
    private int poolSize = 64;

    /** Minimum number of idle connections in the pool, default is 10 */
    private int minIdleSize = 10;

    /** Maximum idle time for a connection (ms); idle connections beyond this will be closed, default is 10000 */
    private int idleTimeout = 10000;

    /** Connection timeout (ms), default is 10000 */
    private int connectTimeout = 10000;

    /** Number of retry attempts, default is 3 */
    private int retryAttempts = 3;

    /** Interval between retry attempts (ms), default is 1000 */
    private int retryInterval = 1000;

    /** Interval for periodically checking connection availability (ms); 0 means no periodic check, default is 0 */
    private int pingInterval = 0;

    /** Whether to keep the connection alive, default is true */
    private boolean keepAlive = true;
}
