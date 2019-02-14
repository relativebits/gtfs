package com.relativebits.gtfs.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GtfsTestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(GtfsTests.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}
