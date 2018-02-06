#include <stdio.h>
#include "../include/linkedlist.h"

#define frameSize 3 // you can ask the user

//Created by burakisik on 22.01.2018.

int input[] = {1,2,3,4,2,1,5,6,2,1,2,3,7,6,3,2,1,2,3,6};
int pageFault=0;
int inputSize = sizeof(input)/sizeof(int);
int data=0; //least recently used
int distance=0;
int maxDistance=0;
int tempArr[frameSize];
int main() {
    printf("input:{1,2,3,4,2,1,5,6,2,1,2,3,7,6,3,2,1,2,3,6}");
    fifo();
    lru();
    optimal();
    return 0;
}

void fifo(){
    head = NULL;
    size = 0;
    pageFault = 0;

    for (int i = 0; i<inputSize;i++){
        if(length()<frameSize && !search(input[i])){ //fill the frame
            insert(input[i]);
            pageFault++;
        }
        else{ //changing page
            if(!search(input[i])){ //if key is not in frame
            delete(); //it will delete at the beginning of list(FIFO)
            insert(input[i]); //insert end of list
            pageFault++;
                //displayList();
            }
        }
    }
    printf("\nframeSize:%d->fifo pageFault:%d",frameSize,pageFault);

}

void lru(){
    head = NULL;
    size = 0;
    pageFault = 0;
    for (int i = 0; i<inputSize;i++){
        if(length()<frameSize && !search(input[i])){ //fill the frame
            insert(input[i]);
            pageFault++;
        }
        else{ //changing page
            maxDistance = 0;
            if(!search(input[i])){ //if key is not in frame
                putListIntoArray(tempArr);
                for (int k = 0; k <frameSize ; k++) { //checking whether the page is in the frame
                    distance=0;
                    for (int j =i-1; j>=0;j--) {
                        distance++;
                        if(tempArr[k]==input[j])
                            break;
                    }
                    if(distance>maxDistance){
                        data = tempArr[k];
                        maxDistance = distance;
                    }
                }
                changePage(data,input[i]);
                pageFault++;
                //displayList();
            }
        }
    }
    printf("\nframeSize:%d->Lru pageFault:%d",frameSize,pageFault);}


void optimal(){
    head = NULL;
    size = 0;
    pageFault=0;

    for (int i = 0; i<inputSize;i++){
        if(length()<frameSize && !search(input[i])){ //fill the frame
            insert(input[i]);
            pageFault++;
        }
        else{ //changing page
            maxDistance = 0;
            if(!search(input[i])){ //if page is not in frame
                putListIntoArray(tempArr);
                for (int k = 0; k <frameSize ; k++) {
                    distance=0;
                    for (int j =i+1; j<inputSize;j++) {
                        distance++;
                        if(tempArr[k]==input[j])
                            break;
                    }
                    if(distance>maxDistance){
                        data = tempArr[k];
                        maxDistance = distance;
                    }
                }

                if(i == sizeof(input)/sizeof(int)-1) // we can not see the future
                    changePage(tempArr[0],input[i]);
                else
                    changePage(data,input[i]);


                pageFault++;
                //displayList();
            }
        }
    }
    printf("\nframeSize:%d->Optimal pageFault:%d",frameSize,pageFault);
}