package com.learn.ThreadExamples;

import java.math.BigInteger;

public class InterruptExample {
    public static void main(String[] args) {
        /*Thread bt = new Thread(new BlockingTask());
        bt.start();
        bt.interrupt();*/

        Thread longCompute = new Thread(new LongCompute(new BigInteger("2000000"),new BigInteger("1000000")));
        longCompute.start();
        longCompute.interrupt();
    }

    private static class LongCompute implements Runnable {

        BigInteger base = BigInteger.ONE;
        BigInteger power = BigInteger.ONE;
        public LongCompute(BigInteger base,BigInteger power){
            this.base=base;
            this.power=power;
        }

        @Override
        public void run() {
            System.out.println("the pow("+base+","+power +") is ->"+ pow(base,power));
        }

        private BigInteger pow(BigInteger base,BigInteger power){
            BigInteger result=BigInteger.ONE;
            for(BigInteger i=BigInteger.ZERO;i.compareTo(power)!=0;i=i.add(BigInteger.ONE)){
                /*if(Thread.currentThread().isInterrupted()){
                    System.out.println("Exiting ineruptted thread");
                    return BigInteger.ONE;
                }*/
                result = result.multiply(base);
            }
            return result;
        }
    }


    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Exiting the run method ");
            }
        }
    }
}
