package com.example.algorithms.multiple_thread.guardedSuspension;

/**
 * Created by fox.hu on 2018/11/6.
 */

public class AlarmAgent {
    private static final String TAG = AlarmAgent.class.getSimpleName();

    private volatile boolean connectedToServer = false;

    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };

    private final Blocker blocker = new ConditionVarBlocker();
}
