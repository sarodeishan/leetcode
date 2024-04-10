package src.multithread;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static java.util.concurrent.CompletableFuture.runAsync;

public class PrintFooBarAlternately {

    public static void main(String[] args) {
        PrintFooBarAlternately printFooBarAlternately = new PrintFooBarAlternately();
        FooBar fooBar = new FooBar(2);
        Thread thread1 = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("Foo"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("Bar"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture.allOf(runAsync(thread1), runAsync(thread2)).join();
    }

    static class FooBar {
        private int n;
        Semaphore foo;
        Semaphore bar;

        public FooBar(int n) {
            this.n = n;
            this.foo = new Semaphore(1);
            this.bar = new Semaphore(0);
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                foo.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                bar.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                bar.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                foo.release();
            }
        }
    }
}