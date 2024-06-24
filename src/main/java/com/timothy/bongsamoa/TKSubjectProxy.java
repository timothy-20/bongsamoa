package com.timothy.bongsamoa;

public class TKSubjectProxy implements TKSubject {
    private TKRealSubject realSubject;

    public TKSubjectProxy() {
        this.realSubject = null;
    }

    @Override
    public void action() {
        if (this.realSubject == null) {
            this.realSubject = new TKRealSubject();
        }

        this.realSubject.action();
        System.out.println("Proxy action for subject.");
    }
}
