package app.mrobot.cn.toutiaoexample.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by fox on 2018/2/25.
 */

public class RxBus {
    private static final String TAG = RxBus.class.getSimpleName();

    private ConcurrentHashMap<Object, List<Subject>> subjectMap = new ConcurrentHashMap<>();

    private RxBus() {
    }

    @NonNull
    public static RxBus get() {
        return Holder.sRxBus;
    }

    private static final class Holder {
        private static RxBus sRxBus = new RxBus();
    }

    public <T> Observable<T> register(@NonNull Class<T> clz) {
        return register(clz.getName());
    }

    public <T> Observable<T> register(@NonNull Object tag) {
        List<Subject> subjects = subjectMap.get(tag);
        if (subjects == null) {
            subjects = new ArrayList<>();
            subjectMap.put(tag, subjects);
        }

        Subject<T> subject = PublishSubject.create();
        subjects.add(subject);
        return subject;
    }

    public <T> void unregister(@NonNull Class<T> clz, @NonNull Observable observable) {
        unregister(clz.getName(), observable);
    }

    public void unregister(@NonNull Object tag, @NonNull Observable observable) {
        List<Subject> subjects = subjectMap.get(tag);
        if (subjects != null) {
            subjects.remove(observable);
            if (subjects.isEmpty()) {
                subjectMap.remove(tag);
            }
        }
    }

    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjects = subjectMap.get(tag);
        if (!subjects.isEmpty()) {
            for (Subject subject : subjects) {
                subject.onNext(content);
            }
        }
    }
}
