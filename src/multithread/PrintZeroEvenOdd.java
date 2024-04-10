package src.multithread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

import static java.util.concurrent.CompletableFuture.runAsync;

public class PrintZeroEvenOdd {

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(2);
        Thread thread0 = new Thread(() -> {
            int i=0;
            while (i<2) {
                i++;
                try {
                    zeroEvenOdd.zero(System.out::println);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            int i=0;
            while (i<1) {
                i++;
                try {
                    zeroEvenOdd.even(System.out::println);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            int i=0;
            while (i<1) {
                i++;
                try {
                    zeroEvenOdd.odd(System.out::println);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        CompletableFuture.allOf(runAsync(thread1), runAsync(thread2), runAsync(thread0)).join();
    }

    static class ZeroEvenOdd {
        private int n;
        private int x;
        private Semaphore zero, odd, even;

        public ZeroEvenOdd(int n) {
            this.n = n;
            this.x = 1;
            zero = new Semaphore(1);
            odd = new Semaphore(0);
            even = new Semaphore(0);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            while (x < n) {
                System.out.println("acquiring Zero lock");
                zero.acquire();
                System.out.println("acquired Zero lock");
                printNumber.accept(0);
                if ((x) % 2 == 0) {
                    even.release();
                } else {
                    odd.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            while (x <= n) {
                System.out.println("acquiring Even lock");
                even.acquire();
                System.out.println("acquired Even lock");
                printNumber.accept(x);
                x++;
                zero.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            while (x <= n) {
                System.out.println("acquiring Odd lock");
                odd.acquire();
                System.out.println("acquired Odd lock");
                printNumber.accept(x);
                x++;
                zero.release();
            }
        }

    }
}