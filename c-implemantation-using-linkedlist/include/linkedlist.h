// Created by burakisik on 22.01.2018.

#ifndef PAGEREPLACEMENTALGORITHM_MYSTACK_H
#define PAGEREPLACEMENTALGORITHM_MYSTACK_H
#include <printf.h>
#include <stdlib.h>
#include "node.h"
int size=0;
node *head = NULL;


void insert(int data){
    node *newNode = (node*)malloc(sizeof(node));
    newNode->data = data;
    newNode->link=NULL;
    size++;
    if(head == NULL){
        head = newNode;
        return;
    }

    node *iter = head;

    while(iter->link != NULL){
        iter = iter->link;
    }
    iter->link = newNode;
}

void displayList(){
    printf("\n");
    node *iter = head;
    for (int i = 0; i <size; ++i) {

        if(i == size-1){
            printf("%d",iter->data); // I just put it for a cool screen
            return;;
        }
        printf("%d->",iter->data);
        iter = iter->link;
    }
}

void delete(){ //deleting the beginning of the list(for fifo)
    node *iter = head;
    head = head->link;
    free(iter);
    size--;
}

void changePage(int oldData,int newData){ // this functions is used lru and optimal algoithms.We dont need delete value just ,we just need change current position's value

    node *iter = head;

    if(head == NULL) //list is empty
        return;

    while(iter->data != oldData){
        if(iter->link == NULL){ //if it doesnt exist
            return;
        }
        else{
            iter = iter->link;
        }
    }

    if(iter == head){
        head->data = newData;
    }else{
        iter->data = newData;
    }
}

char* isEmpty(){
    if(head == NULL)
        return "True";
    else
        return "False";
}

int search(int value){
    node *iter = head;
    while(iter!=NULL){
        if(iter->data == value)
            return 1;
        iter = iter->link;
    }
    return 0;
}

int length(){
    return  size;
}

void putListIntoArray (int arr []){
    node *iter = head;
    for (int i = 0; i < size; ++i) {
        arr[i] = iter->data;
        iter = iter->link;
    }
}
#endif
