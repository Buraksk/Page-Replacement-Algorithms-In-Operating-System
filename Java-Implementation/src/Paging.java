package question2;
public class Paging {

    int[] input = {1, 2, 3, 4, 2, 1, 5, 6, 2, 1, 2, 3, 7, 6, 3, 2, 1, 2, 3, 6};
    static String[] frame;
    int counter;
    int index = 0; //atılacak olan elemanın index
    static int pageFault = 0;
    int distance;
    int tempArr[][]; 
    Boolean isExist = false;
    int rightest;

    public static void main(String[] Args) {
        int frameSize = 3;
        frame = new String[frameSize];

        Paging p = new Paging();
        //p.fifo(frame.length);
        //p.optimal(frame.length);
        p.lru(frame.length);

        for (int i = 0; i < frameSize; i++)
            System.out.println(i + ". index -->" + frame[i]);
        System.out.print("pageFAULT:"+pageFault);
    }

    public void fifo(int frameSize) {    
        //first value initialize-frame boş önce doldur
        fillFrame(frameSize);

        for (int j = frameSize; j < input.length; j++) {

            for (int k = 0; k < frame.length; k++) {
                if (Integer.parseInt(frame[k]) == input[j]) {
                    counter++;
                }
            }
            if (counter == 0) {
                frame[index] = input[j] + "";
                index++;
                index = index % (frameSize);
                pageFault++;
            }
            counter = 0;
        }
        counter = 0;
        index = 0;
    }

    public void optimal(int frameSize) {
        fillFrame(frameSize);
        tempArr = new int[frameSize][1];

        for (int j = frameSize; j < input.length; j++) {
            for (int k = 0; k < frame.length; k++) {
                if (Integer.parseInt(frame[k]) == input[j])
                    counter++;
            }
            if (counter == 0) {
                pageFault++;

                for (int l = 0; l < frameSize; l++){
                    for (int k = j + 1; k < input.length; k++){
                        if (input[k] != Integer.parseInt(frame[l])){
                            distance++;
                        } else {
                            distance++;
                            break;
                        }
                    }
                    tempArr[l][0] = distance;
                    distance = 0;
                }
                //en uzaktakini bulacaz
                rightest = tempArr[0][0];
                for (int i = 1; i < frame.length; i++){
                    if (tempArr[i][0] > rightest) {
                        rightest = tempArr[i][0];
                        index = i;
                    }
                }
                frame[index] = input[j] +"";
            }     
            counter = 0;
            index = 0;
            distance = 0;
        }
    }

    public void lru(int frameSize){    
        fillFrame(frameSize);
        tempArr = new int[frameSize][1];

        for (int j = frameSize; j < input.length; j++){
            //input değeri frame'in içinde varmı
            for (int k = 0; k < frame.length; k++) {
                if (Integer.parseInt(frame[k]) == input[j])
                    counter++;
            }

            if (counter == 0) {
                pageFault++;

                for (int l = 0; l < frameSize; l++){
                    for (int k = j-1; k>-1; k--) {
                        if (input[k] != Integer.parseInt(frame[l])) {
                            distance++;

                        } else {
                            distance++;
                            break;
                        }
                    }
                    tempArr[l][0] = distance;
                    distance = 0;
                }
                //en uzaktakini bulacaz
                rightest = tempArr[0][0];
                for (int i = 1; i < frame.length; i++){
                    if (tempArr[i][0] > rightest) {
                        rightest = tempArr[i][0];
                        index = i;
                    }
                }          
                frame[index] = input[j] +"";
            }        
            counter = 0;
            index = 0;
            distance = 0;
        }
    }

    public void fillFrame(int frameSize) {
        pageFault = 0;
        int counter = 0;
        int top = frameSize;
        int iter = 0;
        for (int i = 0; i < top; i++){         
            if (frame[iter] == null){
                for(int j=0;j<frameSize;j++){
                    if(frame[j] != null && frame[j].equals(input[i]+"")){
                        counter++;
                        break;
                    }
                }
                if(counter == 0){
                    frame[iter] = input[i] + ""; 
                    pageFault++;
                    iter++;
                }
                else{
                    top++;
                }
            }
            counter = 0;
        }
    }
}





