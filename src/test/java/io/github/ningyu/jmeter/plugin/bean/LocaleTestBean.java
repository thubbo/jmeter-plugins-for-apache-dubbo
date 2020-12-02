package io.github.ningyu.jmeter.plugin.bean;

import java.io.Serializable;
import java.util.Locale;

public class LocaleTestBean<T> extends TestBean<T> implements Serializable {
    private static final long serialVersionUID = -5300763291046460049L;
    private Locale locale;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
