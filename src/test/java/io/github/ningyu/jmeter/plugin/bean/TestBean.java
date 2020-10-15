package io.github.ningyu.jmeter.plugin.bean;

import java.io.Serializable;

public class TestBean<T> implements Serializable {
    private static final long serialVersionUID = 8364861022089141326L;
    private String name;

    private T item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
