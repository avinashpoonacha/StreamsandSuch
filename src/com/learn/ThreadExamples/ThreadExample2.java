package com.learn.ThreadExamples;

public class ThreadExample2 {
    public static void main(String[] args) throws InterruptedException {
        NewThread thread = new NewThread();
        thread.start();

    }

    private static class NewThread extends Thread {
        @Override
        public void run(){
            System.out.println("We are in Thread: "+ this.getName());
        }
    }
}
