package question1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MaxNumber extends Thread{

    public int start;
    public int end;
    public static int [] Arr;
    public static int max = Integer.MIN_VALUE;
    public static int counter=0;
    private static Lock lock = new ReentrantLock();


    public MaxNumber(int start, int end){
        this.start = start;
        this.end = end;
    }

    public void run(){
        for(int i=start ; i<end;i++){
            if(Arr[i] > max) {
                lock.lock();
                max = Arr[i];
                lock.unlock();
            }
         }
        //System.out.println("max eleman:"+max);
    }

}
