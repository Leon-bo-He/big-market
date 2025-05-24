package cn.bobo.infrastructure.persistent.redis;

import org.redisson.api.*;

import java.util.concurrent.TimeUnit;

/**
 * Redis Service Interface
 *
 */
public interface IRedisService {

    /**
     * Set the value for a specified key
     *
     * @param key   Key
     * @param value Value
     */
    <T> void setValue(String key, T value);

    /**
     * Set the value for a specified key with expiration
     *
     * @param key     Key
     * @param value   Value
     * @param expired Expiration time
     */
    <T> void setValue(String key, T value, long expired);

    /**
     * Get the value of a specified key
     *
     * @param key Key
     * @return Value
     */
    <T> T getValue(String key);

    /**
     * Get a queue by key
     *
     * @param key Key
     * @param <T> Generic type
     * @return Queue
     */
    <T> RQueue<T> getQueue(String key);

    /**
     * Get a blocking queue by key
     *
     * @param key Key
     * @param <T> Generic type
     * @return Blocking queue
     */
    <T> RBlockingQueue<T> getBlockingQueue(String key);

    /**
     * Get a delayed queue
     *
     * @param rBlockingQueue Blocking queue
     * @param <T>            Generic type
     * @return Delayed queue
     */
    <T> RDelayedQueue<T> getDelayedQueue(RBlockingQueue<T> rBlockingQueue);

    /**
     * Increment the value of a key by 1: 1, 2, 3, 4...
     *
     * @param key Key
     * @return Incremented value
     */
    long incr(String key);

    /**
     * Increment the value of a key by a specific amount
     *
     * @param key   Key
     * @param delta Amount to increment
     * @return Incremented value
     */
    long incrBy(String key, long delta);

    /**
     * Decrement the value of a key by 1
     *
     * @param key Key
     * @return Decremented value
     */
    long decr(String key);

    /**
     * Decrement the value of a key by a specific amount
     *
     * @param key   Key
     * @param delta Amount to decrement
     * @return Decremented value
     */
    long decrBy(String key, long delta);

    /**
     * Remove the specified key
     *
     * @param key Key
     */
    void remove(String key);

    /**
     * Check if a key exists
     *
     * @param key Key
     * @return true if exists, false otherwise
     */
    boolean isExists(String key);

    /**
     * Add a value to a set
     *
     * @param key   Key
     * @param value Value
     */
    void addToSet(String key, String value);

    /**
     * Check if a value is a member of a set
     *
     * @param key   Key
     * @param value Value
     * @return true if member, false otherwise
     */
    boolean isSetMember(String key, String value);

    /**
     * Add a value to a list
     *
     * @param key   Key
     * @param value Value
     */
    void addToList(String key, String value);

    /**
     * Get a value by index from a list
     *
     * @param key   Key
     * @param index Index
     * @return Value
     */
    String getFromList(String key, int index);

    /**
     * Get a map by key
     *
     * @param key Key
     * @return Map
     */
    <K, V> RMap<K, V> getMap(String key);

    /**
     * Add a key-value pair to a hash
     *
     * @param key   Key
     * @param field Field
     * @param value Value
     */
    void addToMap(String key, String field, String value);

    /**
     * Get a value from a hash by field
     *
     * @param key   Key
     * @param field Field
     * @return Value
     */
    String getFromMap(String key, String field);

    /**
     * Get a value from a hash by field
     *
     * @param key   Key
     * @param field Field
     * @return Value
     */
    <K, V> V getFromMap(String key, K field);

    /**
     * Add a value to a sorted set
     *
     * @param key   Key
     * @param value Value
     */
    void addToSortedSet(String key, String value);

    /**
     * Get a Redis lock (reentrant)
     *
     * @param key Key
     * @return Lock
     */
    RLock getLock(String key);

    /**
     * Get a Redis lock (fair)
     *
     * @param key Key
     * @return Lock
     */
    RLock getFairLock(String key);

    /**
     * Get a Redis read-write lock
     *
     * @param key Key
     * @return ReadWriteLock
     */
    RReadWriteLock getReadWriteLock(String key);

    /**
     * Get a Redis semaphore
     *
     * @param key Key
     * @return Semaphore
     */
    RSemaphore getSemaphore(String key);

    /**
     * Get a Redis expirable permit semaphore
     * <p>
     * Based on Redisson's distributed semaphore. The `RSemaphore` interface is similar to
     * `java.util.concurrent.Semaphore`, and also provides async, reactive, and RxJava2 interfaces.
     *
     * @param key Key
     * @return Expirable semaphore
     */
    RPermitExpirableSemaphore getPermitExpirableSemaphore(String key);

    /**
     * Get a Redis countdown latch
     *
     * @param key Key
     * @return CountDownLatch
     */
    RCountDownLatch getCountDownLatch(String key);

    /**
     * Get a Redis Bloom filter
     *
     * @param key Key
     * @param <T> Type of stored object
     * @return Bloom filter
     */
    <T> RBloomFilter<T> getBloomFilter(String key);

    Long getAtomicLong(String key);

    void setAtomicLong(String key, Integer value);

    Boolean setNx(String key);

    Boolean setNx(String key, long expired, TimeUnit timeUnit);
}
