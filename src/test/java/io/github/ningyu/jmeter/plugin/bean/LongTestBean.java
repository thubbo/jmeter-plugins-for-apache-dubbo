package io.github.ningyu.jmeter.plugin.bean;

import java.io.Serializable;

public class LongTestBean<T> extends TestBean<T> implements Serializable {
    private static final long serialVersionUID = 4855342359618933272L;

    public Long getBigLong() {
        return bigLong;
    }

    public void setBigLong(Long bigLong) {
        this.bigLong = bigLong;
    }

    public long getSmallLong() {
        return smallLong;
    }

    public void setSmallLong(long smallLong) {
        this.smallLong = smallLong;
    }

    private Long bigLong;
    private long smallLong;
}
