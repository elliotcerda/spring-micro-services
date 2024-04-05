package com.elliot.microservices.limitservice;

public class LimitsConfiguration {
    private int minimun;
    private int maximun;

    protected LimitsConfiguration() {}

    protected LimitsConfiguration(int minimun, int maximun) {
        super();
        this.minimun = minimun;
        this.maximun = maximun;
    }

    public int getMinimun() {
        return minimun;
    }

    public void setMinimun(int minimun) {
        this.minimun = minimun;
    }

    public int getMaximun() {
        return maximun;
    }

    public void setMaximun(int maximun) {
        this.maximun = maximun;
    }
}
