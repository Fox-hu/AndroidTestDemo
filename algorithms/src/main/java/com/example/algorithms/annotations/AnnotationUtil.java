package com.example.algorithms.annotations;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class AnnotationUtil {

    public static void inject(Activity activity) {
        injectLayout(activity);
        injectView(activity);
        injectEvent(activity);
    }

    private static void injectLayout(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView == null) {
            return;
        }
        activity.setContentView(contentView.value());
    }

    public static void injectView(Activity activity) {
        Field[] fields = activity.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {
                View view = activity.findViewById(injectView.value());
                try {
                    //在activity中 将使用注解的view 赋值到注解id的view上
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void injectEvent(Activity activity) {
        Method[] methods = activity.getClass().getDeclaredMethods();
        for (Method method : methods) {
            OnClick click = method.getAnnotation(OnClick.class);
            if (click == null) {
                continue;
            }
            //获取注解的注解要使用这种方式
            EventControl eventControl = click.annotationType().getAnnotation(EventControl.class);
            if (eventControl == null) {
                return;
            }
            String listener = eventControl.listenerMethod();
            String callBack = eventControl.callBack();
            Class<?> listenerType = eventControl.getClazzType();
            HashMap<String, Method> methodMap = new HashMap<>();
            methodMap.put(callBack, method);

            int[] viewIds = click.value();
            for (int viewId : viewIds) {
                View view = activity.findViewById(viewId);
                if (view != null) {
                    try {
                        Method setListenerMethod = view.getClass().getMethod(listener,
                                listenerType);
                        Object proxyInstance = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class[]{listenerType}, (proxy, method1, args) -> {
                                    Method m = methodMap.get(method1.getName());
                                    if (m != null) {
                                        return m.invoke(activity, args);
                                    }
                                    return method1.invoke(proxy, args);
                                });
                        setListenerMethod.invoke(view, proxyInstance);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void injectEvent(Fragment fragment) {
        Method[] methods = fragment.getClass().getDeclaredMethods();
        for (Method method : methods) {
            OnClick click = method.getAnnotation(OnClick.class);
            if (click == null) {
                continue;
            }
            //获取注解的注解要使用这种方式
            EventControl eventControl = click.annotationType().getAnnotation(EventControl.class);
            if (eventControl == null) {
                return;
            }
            String listener = eventControl.listenerMethod();
            String callBack = eventControl.callBack();
            Class<?> listenerType = eventControl.getClazzType();
            HashMap<String, Method> methodMap = new HashMap<>();
            methodMap.put(callBack, method);

            int[] viewIds = click.value();
            for (int viewId : viewIds) {
                View content = fragment.getView();
                if (content == null) {
                    continue;
                }
                View view = content.findViewById(viewId);
                if (view != null) {
                    try {
                        Method setListenerMethod = view.getClass().getMethod(listener,
                                listenerType);
                        Object proxyInstance = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class[]{listenerType}, (proxy, method1, args) -> {
                                    Method m = methodMap.get(method1.getName());
                                    if (m != null) {
                                        return m.invoke(fragment, args);
                                    }
                                    return method1.invoke(proxy, args);
                                });
                        setListenerMethod.invoke(view, proxyInstance);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
