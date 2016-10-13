package com.guilin.java6.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

/**
 * Created by guilin on 2016/9/2.
 */
public class TestEventBus {

    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        eventBus.post(11);
        eventBus.post(true);

        System.out.println("LastMessage:" + listener.getLastMessage());
        ;
    }

    public static class EventListener {

        public int lastMessage = 0;

        @Subscribe
        public void listen(TestEvent event) {
            lastMessage = event.getMessage();
            System.out.println("Message:" + lastMessage);
        }

        @Subscribe
        public void listen(Integer a) {
            System.out.println(a);
        }

        @Subscribe
        public void listen(Boolean b) {
            System.out.println(b);
        }

        public int getLastMessage() {
            return lastMessage;
        }

    }

    public static class TestEvent {

        private final int message;

        public TestEvent(int message) {
            this.message = message;
            System.out.println("event message:" + message);
        }

        public int getMessage() {
            return message;
        }
    }
}
