package com.learn.ThreadExamples;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(123L,12L);
        List<FactorialThread> threads = new ArrayList<>();
        for(Long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for(Thread th:threads){
            th.setDaemon(true);
            th.start();
        }
        for(Thread th:threads){
            th.join(3000);
        }

        for(int i=0;i<inputNumbers.size();i++){
            FactorialThread ft = threads.get(i);
            if(ft.isFinished()){
                System.out.println("FACtorial is"+ft.getResult());
            } else {
                System.out.println("FACtorial is in progress");
            }
        }
    }


    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(Long inputNumber){
            this.inputNumber=inputNumber;
        }

        @Override
        public void run(){
            this.result=findFactorial(inputNumber);
            this.isFinished=true;
        }

        private BigInteger findFactorial(long inputNumber) {
            BigInteger result = BigInteger.ONE;

            for(long i=inputNumber;i>0;i--){
                result = result.multiply(new BigInteger(String.valueOf(i)));
            }
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }

        public void setResult(BigInteger result) {
            this.result = result;
        }
    }
}
