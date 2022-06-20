package com.chi.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 功能说明: spring上下文<br>
 * 开发人员: @author huadi<br>
 * 开发时间: 2020年02月20日<br>
 */
@Service
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext appContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    public static boolean containsBean(String beanName) {
        if (appContext != null) {
            return appContext.containsBean(beanName);
        }
        return false;
    }

    public static Object getBean(String beanName) {
        if (appContext != null) {
            return appContext.getBean(beanName);
        }
        return null;
    }

    public static <T> T getBean(Class<T> clazz) {
        if (appContext != null) {
            try {
                return appContext.getBean(clazz);
            } catch (BeansException e) {
                Map<String, T> map = getBeansOfType(clazz);
                for (Map.Entry<String, T> entry : map.entrySet()) {
                    if (entry.getValue().getClass().equals(clazz)) {
                        return entry.getValue();
                    }
                }
                throw e;
            }
        }
        return null;
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) throws BeansException {
        Map<String, T> map = null;
        if (appContext != null) {
            map = appContext.getBeansOfType(clazz);
            if (map.isEmpty() && appContext.getParent() != null) {
                map = appContext.getParent().getBeansOfType(clazz);
            }
        }
        if (map == null) {
            map = new HashMap<String, T>();
        }
        return map;
    }

    public static <T> T getBean(String beanId, Class<T> clazz) {
        if (appContext != null) {
            return appContext.getBean(beanId, clazz);
        }
        return null;
    }

    public <T> List<Map.Entry<String, T>> getOrderedBeans(Class<T> clazz) {
        Set<Map.Entry<String, T>> caches = getBeansOfType(clazz).entrySet();
        List<Map.Entry<String, T>> list = new ArrayList<Map.Entry<String, T>>(caches);
        /*
         * 根据order顺序排列
         */
        Collections.sort(list, new Comparator<Map.Entry<String, T>>() {

            public int compare(Map.Entry<String, T> e1, Map.Entry<String, T> e2) {
                return new OrderComparator().compare(e1.getValue(),
                        e2.getValue());
            }
        });
        return list;
    }
}
