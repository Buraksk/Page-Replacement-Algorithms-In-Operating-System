package question1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MaxPrimeNumber extends Thread {

    public int start;
    public int end;
    public static int maxPrime = -1;
    public static int [] Arr;
    public static int counter=0;
    private static Lock lock = new ReentrantLock();

    public MaxPrimeNumber(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        super.run();

        for(int i=start ; i<end;i++){

            for(int j =2; j<Arr[i]/2; j++){

                if(Arr[i]%j==0){
                    counter++;
                    break;
                }
            }
            if(counter == 0){
                if(maxPrime < Arr[i]){
                    lock.lock();
                    maxPrime = Arr[i];
                    lock.unlock();
                }
            }
            counter = 0;
        }

        //System.out.println("max eleman:"+max);
    }
}
