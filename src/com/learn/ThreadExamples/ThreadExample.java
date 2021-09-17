package com.learn.ThreadExamples;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run(){
                throw new RuntimeException("Intentional exception");
                /*System.out.println("We are in Thread: "+ Thread.currentThread().getName());
                System.out.println("Current Thread Priority is:" + Thread.currentThread().getPriority());*/
            }

        });
        /*thread.setName("Worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("We are in Thread: "+ Thread.currentThread().getName()+" before strating new Thread");*/
        thread.setName("Misbehaving Thread");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("critical error in "+ t.getName() + " error is" + e.getMessage());
            }
        });


        thread.start();
        /*System.out.println("We are in Thread: "+ Thread.currentThread().getName()+" after strating new Thread");
        Thread.sleep(10000);*/
    }
}
