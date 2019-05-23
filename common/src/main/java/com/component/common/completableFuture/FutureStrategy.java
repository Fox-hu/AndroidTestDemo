package com.component.common.completableFuture;

public interface FutureStrategy<K> {

    default boolean enable(K key) {
        return true;
    }

    default boolean anyof(){
        return false;
    }

    default int getTimeoutSecond(){
        return 3;
    }
}
