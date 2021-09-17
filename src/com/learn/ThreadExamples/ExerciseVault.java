package com.learn.ThreadExamples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExerciseVault {
    public static final int MAX_PASSWORD=999;
    public static void main(String[] args) {
        Random random = new Random();
        Vault v = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<Thread>();

        threads.add(new AscHackingThread(v));
        threads.add(new DscHackingThread(v));
        threads.add(new PoliceThread());

        for(Thread t : threads){
            t.start();
        }
    }

    private static class Vault {
        private int password;
        public Vault(int password) {
            this.password = password;
        }
        public boolean isCorrectPassword(int guess){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.password==guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;
        public HackerThread(Vault vault){
            this.vault=vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("starting thread " + this.getName());
            super.start();
        }
    }

    private static class AscHackingThread extends HackerThread {

        public AscHackingThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0;guess<MAX_PASSWORD;guess++){
                if(vault.isCorrectPassword(guess)){
                    System.out.println(this.getName()+ " guess the password"+ guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DscHackingThread extends HackerThread {

        public DscHackingThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD;guess>0;guess--){
                if(vault.isCorrectPassword(guess)){
                    System.out.println(this.getName()+ " guess the password"+ guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {

        @Override
        public void run(){
            for(int i=10;i>0;i--){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
            System.out.println("game over !!");
            System.exit(0);
        }

    }
}
