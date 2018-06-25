package com.example.algorithms.efficient.work_master;

import android.util.Log;

import java.util.Map;
import java.util.Set;

/**
 *
 * 在worker master模式中 最重要的是worker master持有同一个workqueue 和 resultmap
 * @author fox.hu
 * @date 2018/6/20
 */

public class MasterWorkTest {
    private static final String TAG = MasterWorkTest.class.getSimpleName();

    public static void start() {
        Master master = new Master(new PlusWorker(), 5);
        for (int i = 0; i < 100; i++) {
            master.submit(i);
        }
        master.execute();

        int re = 0;

        Map<String, Object> resultMap = master.getResultMap();
        while (resultMap.size() > 0 || !master.isComplete()) {
            Set<String> keys = resultMap.keySet();
            String key = null;
            for (String k : keys) {
                key = k;
                break;
            }

            Integer i = null;
            if (key != null) {
                i = (Integer) resultMap.get(key);
            }

            if (i != null) {
                re += i;
                Log.i(TAG, "result = " + re);
            }

            if (key != null) {
                resultMap.remove(key);
            }
        }
        Log.i(TAG, "complete, total result = " + re);
    }
}
