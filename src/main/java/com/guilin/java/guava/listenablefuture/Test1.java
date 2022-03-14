package com.guilin.java.guava.listenablefuture;

import com.google.common.util.concurrent.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class Test1 {

    private Logger logger = LoggerFactory.getLogger(Test1.class);

    @Test
    public void test1() throws InterruptedException {
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        logger.info("start");

        logger.info("submit query1");
        ListenableFuture query1 = pool.submit(() -> {
            logger.info("query1 invoke...");
            Thread.sleep(5000);
            logger.info("query1 over");
            return "hello";
        });

        logger.info("submit query2");
        ListenableFuture query2 = pool.submit(() -> {
            logger.info("query2 invoke...");
            Thread.sleep(3000);
            logger.info("query2 over");
            return 121;
        });

        logger.info("addCallback");
        Futures.addCallback(Futures.successfulAsList(query1, query2), new FutureCallback<List>() {
            @Override
            public void onSuccess(List result) {
                logger.info("all success!");
            }

            @Override
            public void onFailure(Throwable t) {
                logger.error(t.getMessage());
            }
        });

        logger.info("addCallback over");

        Thread.sleep(30000);
    }
}
