package com.yoelglus.mlbb.mvvm;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class Observable<T> {

    private T data;

    private Set<Observer<T>> observers =
            Collections.newSetFromMap(new WeakHashMap<Observer<T>, Boolean>());

    public Observable(T data) {
        this.data = data;
    }

    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }

    public void deleteObserver(Observer<T> observer) {
        observers.remove(observer);
    }

    public void deleteObservers() {
        observers.clear();
    }

    public void setData(T data) {
        this.data = data;
        notifyObservers();
    }

    public T getData() {
        return data;
    }

    private void notifyObservers() {
        for (Observer<T> observer : observers) {
            observer.update(this, data);
        }
    }

    public interface Observer<T> {
        void update(Observable observable, T data);
    }

}
