package com.component.common.completableFuture;

import java.util.List;

public interface FutureListener {
    <R> void onResult(List<R> resultList);
}
