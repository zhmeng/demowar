package com.zwxpay.demo.helper;

import java.io.Serializable;

/**
 * Created by zhangmeng on 16-7-4.
 */
public final class Tuple<A, B> implements Serializable {

    /**
     * gen by eclipse
     */
    private static final long serialVersionUID = 482545776907024160L;

    private A a;
    private B b;

    public Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A _1() {
        return this.a;
    }

    public B _2() {
        return this.b;
    }

}