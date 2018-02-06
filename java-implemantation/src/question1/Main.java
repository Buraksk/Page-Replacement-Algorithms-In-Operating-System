package question1;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int[] arrNumbers;
    Random rnd;
    static MaxNumber[] maxNumber;
    static Main main;
    static MaxPrimeNumber[] maxPrimeNumbers;

    public static void main(String[] args) {
        //Thread[] threads = new Thread[10];
        /*
        //kullanıcıdan dizi boyutu ve thread sayısı isteniyor
        Scanner Size = new Scanner(System.in);
        System.out.println("Lütfen Dizi Boyutunu Giriniz:");
        int arrSize = Size.nextInt();

        System.out.println("Lütfen Thread Oluşturulacak Sayısını Giriniz:");
        Scanner thread = new Scanner(System.in);
        int threadNumber = thread.nextInt();
        */
        main = new Main();
        //main.findMaxNumber(arrSize,threadNumber);  //1.soru a şıkkı
        //main.findMaxPrimeNumber(arrSize,threadNumber); //1.soru b şıkkı

        //1.soru c şıkkı 2,4 ve 8 thread ile max sayıyı bulma süreleri
        long startTime;
        long endTime;
        for (int i = 1; i < 4; i++) {
            if (i == 3) {
                startTime = System.currentTimeMillis();
                main.findMaxNumber(1000000,8);
                endTime = System.currentTimeMillis();

                System.out.println("8 Thread ile bir milyon dizinin max elemanı bulma süresi:" + (endTime - startTime)+"ms");
            } else {
                startTime = System.currentTimeMillis();
                main.findMaxNumber(1000000,i*2);
                endTime = System.currentTimeMillis();
                System.out.println(i * 2 + " Thread ile bir milyon dizinin max elemanı bulma süresi:" + (endTime - startTime)+"ms");
            }
        }

        //1.soru c şıkkı 2,4 ve 8 için max asal sayıyı bulma sürelerinin hesabı
        for (int i = 1; i < 4; i++) {
            if (i == 3) {
                startTime = System.currentTimeMillis();
                main.findMaxPrimeNumber(1000000,8);
                endTime = System.currentTimeMillis();

                System.out.println("8 Thread ile bir milyon dizinin max Asal elemanı bulma süresi:" + (endTime - startTime)+"ms");
            } else {
                startTime = System.currentTimeMillis();
                main.findMaxPrimeNumber(1000000,i*2);
                endTime = System.currentTimeMillis();
                System.out.println(i * 2 + " Thread ile bir milyon dizinin max Asal elemanı bulma süresi:" + (endTime - startTime)+"ms");
            }
        }

       /*
        startTime = System.currentTimeMillis();
        main.findMaxPrimeNumber(1000000,32);
        endTime = System.currentTimeMillis();
        System.out.println(32+ " Thread ile bir milyon dizinin max Asal elemanı bulma süresi:" + (endTime - startTime));
       */
    }

    public void createRandomArray(int arrSize) {
        rnd = new Random();
        arrNumbers = new int[arrSize];

        for (int i = 0; i < arrSize; i++) {
            arrNumbers[i] = rnd.nextInt(1000000);
             //System.out.println(arrNumbers[i]);
        }
    }

    public void findMaxNumber(int arrSize, int threadNumber ){

        main.createRandomArray(arrSize);
        maxNumber = new MaxNumber[threadNumber];

        int lowerLimit = 0;
        int upperLimit;
        //dizi her thread için eşit aralıklarda parçalara ayırılacak aralık değeri hesaplanıyor
        int interval = arrSize / threadNumber;
        upperLimit = arrSize / threadNumber;

        //her thread için çalışma yapacağı dizi aralığını belirledik
        for (int i = 0; i < threadNumber; i++) {
            //System.out.println("start:" + lowerLimit + " " + "end:" + upperLimit);
            maxNumber[i] = new MaxNumber(lowerLimit, upperLimit);

            lowerLimit += interval;
            upperLimit += interval;
        }
        //thread'lerin ortak erişeceği diziye atama yaptık random olarak oluşturduğumuz diziyi atadık
        MaxNumber.Arr = arrNumbers;

        for (int i = 0; i < threadNumber; i++) {
            maxNumber[i].start();
            //mythread[i].join();
        }
        //Threadler arka planda çalıştığı için threadlerin işleri bitmeden alt satıra geçmesini engellik
        //eğer bunu yapmassak max sayıyı yanlış bulur!çünkü daha işlem bitmeden main fonksiyonun çıktıları yazdırır
        boolean isFinish = false;
        do {
            isFinish = false;
            for (Thread t : maxNumber) {
                if (t.isAlive()) {
                    isFinish = true;
                    break;
                }
            }
        }while (isFinish);
        //System.out.println("max:" + MaxNumber.max);
    }

    public void findMaxPrimeNumber(int arrSize,int threadNumber){

        main.createRandomArray(arrSize);
        maxPrimeNumbers = new MaxPrimeNumber[threadNumber];

        int lowerLimit = 0;
        int upperLimit;
        //dizi her thread için eşit aralıklarda parçalara ayırılacak aralık değeri hesaplanıyor
        int interval = arrSize / threadNumber;
        upperLimit = arrSize / threadNumber;

        //her thread için çalışma yapacağı dizi aralığını belirledik
        for (int i = 0; i < threadNumber; i++) {
            //System.out.println("start:" + lowerLimit + " " + "end:" + upperLimit);
            maxPrimeNumbers[i] = new MaxPrimeNumber(lowerLimit, upperLimit);
            lowerLimit += interval;
            upperLimit += interval;
        }
        //thread'lerin ortak erişeceği diziye atama yaptık random olarak oluşturduğumuz diziyi atadık
        MaxPrimeNumber.Arr = arrNumbers;

        for (int i = 0; i < threadNumber; i++){
            maxPrimeNumbers[i].start();
        }
        //Threadler arka planda çalıştığı için threadlerin işleri bitmeden alt satıra geçmesini engellik
        //eğer bunu yapmassak max sayıyı yanlış bulur!çünkü daha işlem bitmeden main fonksiyonun çıktıları yazdırır
        boolean isFinish = false;
        do {
            isFinish = false;
            for (Thread t : maxPrimeNumbers) {
                if (t.isAlive()) {
                    isFinish = true;
                    break;
                }
            }
        }while (isFinish);

        /*
        if (MaxPrimeNumber.maxPrime == -1)
            System.out.println("Dizide asal sayı yoktur!!!");
        else
            System.out.println("Max Asal Sayı:" + MaxPrimeNumber.maxPrime);
        */
    }
}
