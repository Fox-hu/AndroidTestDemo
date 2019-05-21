package com.component.common.completableFuture;

import java.util.function.Function;

public interface FutureStrategy {

    default boolean enable(Function future) {
        return true;
    }

    default boolean anyof(){
        return true;
    }
}
