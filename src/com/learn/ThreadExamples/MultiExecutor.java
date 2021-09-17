package com.learn.ThreadExamples;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    private final List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks=tasks;
    }

    public void executeAll(){
        List<Thread> threads= new ArrayList<>(tasks.size());
        for(Runnable task:tasks){
            Thread t = new Thread(task);
           threads.add(t);
        }
        for(Thread th:threads){
            th.start();
        }
    }

    public static void main(String[] args) {

        List<Runnable> tasks = new ArrayList<>();
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("this is run 1 !!");
            }
        };
    tasks.add(run1);
        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("this is run 2!!");
            }
        };
        tasks.add(run2);
        Runnable run3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("this is run 3!!");
            }
        };
        tasks.add(run3);
        MultiExecutor multiExecutor = new MultiExecutor(tasks);
        multiExecutor.executeAll();

    }
}
