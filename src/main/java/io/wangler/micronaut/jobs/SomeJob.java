package io.wangler.micronaut.jobs;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

@Singleton
public class SomeJob {

    private boolean paused = false;

    @Scheduled(fixedRate = "#{ env['hello.world.rate'] }", condition = "#{!this.paused}")
    public void doSomething() {
        System.out.println("The job runs...");
    }

    public boolean isPaused() {
        return paused;
    }
}
