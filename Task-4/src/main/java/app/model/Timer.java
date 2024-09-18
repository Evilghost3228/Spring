package app.model;

import org.springframework.stereotype.Component;

@Component
public class Timer {

    private final Long nanoTime;

    public Timer() {
        this.nanoTime = System.nanoTime();
    }

    public Long getTime() {
        return nanoTime;
    }
}
