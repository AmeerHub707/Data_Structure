package org.example;

import java.util.Arrays;

public class StackBasedOnArray <T> {
    private int top = -1 ;
    private T [] arr ;
    private int maxSize = 100 ;
    public StackBasedOnArray () {
        arr = (T[]) new Object [maxSize];                                // Default Constructor with size 100 element :
    }
    public StackBasedOnArray (int size) {                                 // Constructor With User Input Size :
      maxSize = size ;
        if (maxSize > 0)
            arr = (T[]) new Object [maxSize];                              // Default Constructor with size 100 element :
        else
            System.out.println("Invalid Number Of Element...! ");

    }

    public boolean isFull() {
         return top == maxSize ;
    }

    public boolean isEmpty() {
        return top == -1 ;
    }

    public void push  (T element) {                            // This method to add new element to Stack :
       try {
           if (isFull())
               System.out.println("The Stack is Full on push...!");
           else {
               top++;
               arr[top] = element;
           }
       }catch (IndexOutOfBoundsException e){
           System.out.println("Error: Tried to push beyond stack limit. Details: " + e.getMessage());
       }
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("There's No element In Stack To Pop...!");
            return null;
        } else {
            return arr[top--];
        }
    }
    public void getTop() {
        if (isEmpty()) {
            System.out.println("There's No Element...!");
        }else
            System.out.println("Top Of Stack Is : "+arr[top]);
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("The Stack is Empty On Display...!");
        } else {
            System.out.print("The Stack is: [ ");
            for (int i = top; i >= 0 && i < arr.length; i--) {
                System.out.print(arr[i] + " ");
            }
            System.out.println("]");
        }
    }
    public void clear() {
        top = -1;
        System.out.println("Stack has been cleared.");
    }
    public int getSize () {
        return top + 1;
    }
    public boolean isThere (T element) {
        for (int i = 0; i <= top; i++) {
            if (arr[i].equals(element)) return true;
        }
        return false;
    }
    public T[] toArray() { // convert for Array
        return Arrays.copyOfRange(arr, 0, top + 1);
    }

    public void printWithIndex() { // just for learning
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            System.out.println("Stack contents with index:");
            for (int i = top; i >= 0; i--) {
                System.out.println("Index " + i + ": " + arr[i]);
            }
        }
    }
    
    private void resize() {  // For dynamic resize
        maxSize *= 2;
        arr = Arrays.copyOf(arr, maxSize);
    }


    /*
        This Class Is Created By AmeerMack
        Email : ayyad.ameer777@gmail.com
     */
}
