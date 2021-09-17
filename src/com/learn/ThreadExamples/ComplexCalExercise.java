package com.learn.ThreadExamples;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ComplexCalExercise {

    public static void main(String[] args) throws InterruptedException {
        PowerCalulateThread t1=new PowerCalulateThread(new BigInteger("2"),new BigInteger("10000000"));

        PowerCalulateThread t2=new PowerCalulateThread(new BigInteger("1000000000"),new BigInteger("2"));

        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();
        t1.join(3000);
        t2.join(3000);

        System.out.println("t1.getResult() " + t1.getResult());
        System.out.println("t2.getResult() " + t2.getResult());
        System.out.println("sum -->  " + t1.getResult().add(t2.getResult()));


    }

    public static class PowerCalulateThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;
        private boolean isFinished = false;

        public PowerCalulateThread(BigInteger base,BigInteger power){
            this.base=base;
            this.power=power;
        }
        @Override
        public void run(){
            result = pow(base,power);
            this.isFinished=true;
        }

        private BigInteger pow(BigInteger base,BigInteger power){
            BigInteger temp = BigInteger.ONE;
            for(BigInteger i=BigInteger.ZERO;i.compareTo(power)!=0;i=i.add(BigInteger.ONE)){
                temp = temp.multiply(base);
            }
            return temp;
        }

        public BigInteger getResult() {
            return result;
        }

    }
}
