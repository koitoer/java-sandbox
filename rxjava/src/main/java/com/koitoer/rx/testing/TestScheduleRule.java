package com.koitoer.rx.testing;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import rx.plugins.RxJavaHooks;
import rx.schedulers.TestScheduler;

/**
 * Created by mmena on 11/28/17.
 */
public class TestScheduleRule implements TestRule {

    private final TestScheduler testScheduler = new TestScheduler();

    public TestScheduler getTestScheduler() {
        return testScheduler;
     }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {

            @Override public void evaluate() throws Throwable {
                RxJavaHooks.setOnIOScheduler(scheduler -> testScheduler);
                RxJavaHooks.setOnComputationScheduler(scheduler -> testScheduler);
                RxJavaHooks.setOnNewThreadScheduler(scheduler -> testScheduler);

                try {
                    statement.evaluate();
                }finally {
                    RxJavaHooks.reset();
                }
            }
        };
    }
}
