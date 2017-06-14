package com.guilin.java.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Created by guilin on 2017/5/8.
 */
public class GetStarted {
    //mvn package exec:java -Dexec.mainClass=com.guilin.java.metrics.GetStarted

    static final MetricRegistry metrics = new MetricRegistry();

    private final Timer responses = metrics.timer(name(GetStarted.class, "responses"));

    @Test
    public void test1() {
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(10, TimeUnit.SECONDS);


        for (int i = 0; i < 5; i++) {
            tt();
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(5) * 1000);
            } catch (InterruptedException e) {

            }
        }

        //reporter.report();

        try {
            Thread.sleep(1000 * 60 * 5);
        } catch (InterruptedException e) {

        }

    }

    private void tt() {
        final Timer.Context context = metrics.timer(name(GetStarted.class, "tt")).time();
        try {
            // etc;
            System.out.println("ok");
        } finally {
            context.stop();
        }
    }


    public static void main(String args[]) {
        startReport(1);
        Meter requests = metrics.meter("requests");
        requests.mark();
        waitSeconds(5);
    }

    @Test
    public void testMeters() {
        startReport(1);
        Meter requests = metrics.meter("requests");
        requests.mark();
        waitSeconds(5);
    }

    private static void startReport(int seconds) {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(seconds, TimeUnit.SECONDS);
    }

    private static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
        }
    }


}
