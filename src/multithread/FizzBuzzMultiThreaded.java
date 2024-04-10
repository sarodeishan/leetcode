package src.multithread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

import static java.util.concurrent.CompletableFuture.runAsync;

public class FizzBuzzMultiThreaded {

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Thread thread1 = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("Fizz"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("Buzz"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("FizzBuzz"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread4 = new Thread(() -> {
            try {
                fizzBuzz.number(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture.allOf(runAsync(thread1), runAsync(thread2), runAsync(thread3), runAsync(thread4)).join();
    }

    static class FizzBuzz {
        private int n;
        private int x = 1;

        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            while (x <= n) {
                if (x % 3 == 0 && x % 5 != 0) {
                    synchronized (this) {
                        printFizz.run();
                        x++;
                    }
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            while (x <= n) {
                if (x % 3 != 0 && x % 5 == 0) {
                    synchronized (this) {
                        printBuzz.run();
                        x++;
                    }
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (x <= n) {
                if (x % 3 == 0 && x % 5 == 0) {
                    synchronized (this) {
                        printFizzBuzz.run();
                        x++;
                    }
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            while (x <= n) {
                if (x % 3 != 0 && x % 5 != 0) {
                    synchronized (this) {
                        printNumber.accept(x);
                        x++;
                    }
                }
            }
        }

    }
}