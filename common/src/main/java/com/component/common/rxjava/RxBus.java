package com.component.common.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


public class RxBus {
    private static final String TAG = RxBus.class.getSimpleName();

    private ConcurrentHashMap<Object, List<Subject>> subjectMap = new ConcurrentHashMap<>();

    private RxBus() {

    }

    public static RxBus get() {
        return Holder.INSTANCE;
    }

    public <T> Observable<T> register(Class<T> clz) {
        return register(clz.getName());
    }

    public <T> Observable<T> register(String tag) {
        List<Subject> subjectList = subjectMap.get(tag);
        if (subjectList == null) {
            subjectList = new ArrayList<>();
            subjectMap.put(tag, subjectList);
        }

        Subject subject = PublishSubject.create();
        subjectList.add(subject);
        return subject;
    }

    public <T> void unRegister(Class<T> clz, Observable observable) {
        unRegister(clz.getName(), observable);
    }

    public void unRegister(String tag, Observable observable) {
        final List<Subject> subjectList = subjectMap.get(tag);
        if (subjectList != null) {
            subjectList.remove(observable);
            if (subjectList.isEmpty()) {
                subjectMap.remove(tag);
            }
        }
    }

    public void post(Object tag, Object obj) {
        post(tag.getClass().getSimpleName(), obj);
    }

    public void post(String tag, Object obj) {
        final List<Subject> subjectList = subjectMap.get(tag);
        if (subjectList != null && !subjectList.isEmpty()) {
            for (Subject subject : subjectList) {
                subject.onNext(obj);
            }
        }
    }

    private static class Holder {
        private static final RxBus INSTANCE = new RxBus();
    }
}
