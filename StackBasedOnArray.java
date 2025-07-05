package org.example;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Function;

public class StackBasedOnArray<T> implements Iterable<T>, Cloneable {
    private int top = -1;
    private T[] arr;
    private int maxSize = 100;

    public StackBasedOnArray() {
        arr = (T[]) new Object[maxSize];
    }

    public StackBasedOnArray(int size) {
        maxSize = size;
        if (maxSize > 0)
            arr = (T[]) new Object[maxSize];
        else
            throw new IllegalArgumentException("Invalid Number Of Elements.");
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(T element) {
        if (isFull()) resize();
        arr[++top] = element;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("There's No element In Stack To Pop...!");
            return null;
        } else {
            return arr[top--];
        }
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("There's No Element...!");
            return null;
        } else return arr[top];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("The Stack is Empty On Display...!");
        } else {
            System.out.print("The Stack is: [ ");
            for (int i = top; i >= 0; i--) {
                System.out.print(arr[i] + " ");
            }
            System.out.println("]");
        }
    }

    public void clear() {
        top = -1;
        System.out.println("Stack has been cleared.");
    }

    public int getSize() {
        return top + 1;
    }

    public boolean contains(T element) {
        for (int i = 0; i <= top; i++) {
            if (arr[i].equals(element)) return true;
        }
        return false;
    }

    public T[] toArray() {
        return Arrays.copyOfRange(arr, 0, top + 1);
    }

    public void printWithIndex() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            System.out.println("Stack contents with index:");
            for (int i = top; i >= 0; i--) {
                System.out.println("Index " + i + ": " + arr[i]);
            }
        }
    }

    private void resize() {
        maxSize *= 2;
        arr = Arrays.copyOf(arr, maxSize);
    }

    public void reverseInPlace() {
        for (int i = 0, j = top; i < j; i++, j--) {
            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public StackBasedOnArray<T> reversed() {
        StackBasedOnArray<T> newStack = new StackBasedOnArray<>(maxSize);
        for (int i = 0; i <= top; i++) {
            newStack.arr[i] = arr[top - i];
        }
        newStack.top = this.top;
        return newStack;
    }

    @Override
    public StackBasedOnArray<T> clone() {
        StackBasedOnArray<T> copy = new StackBasedOnArray<>(this.maxSize);
        copy.top = this.top;
        copy.arr = Arrays.copyOf(this.arr, this.arr.length);
        return copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = top;

            @Override
            public boolean hasNext() {
                return currentIndex >= 0;
            }

            @Override
            public T next() {
                return arr[currentIndex--];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Stack (top → bottom): [ ");
        for (int i = top; i >= 0; i--) {
            sb.append(arr[i]);
            if (i != 0) sb.append(" → ");
        }
        sb.append(" ]");
        return sb.toString();
    }
    // The new updating is here add these two method for save and load file ::: 
    
    public void saveToCSVWithDialog(Function<T, String> formatter) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Stack as CSV");
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!path.endsWith(".csv")) path += ".csv";
            try (PrintWriter writer = new PrintWriter(path)) {
                for (int i = 0; i <= top; i++) {
                    writer.println(formatter.apply(arr[i]));
                }
                JOptionPane.showMessageDialog(null, "Stack saved to: " + path);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error saving file:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void loadFromCSVWithDialog(Function<String, T> parser) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open CSV File");
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (Scanner scanner = new Scanner(file)) {
                clear();
                List<T> tempList = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    tempList.add(parser.apply(line));
                }
                for (T item : tempList) push(item);
                JOptionPane.showMessageDialog(null, "Stack loaded from: " + file.getAbsolutePath());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading file:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*
        This Class Is Created By AmeerMack
        Email : ayyad.ameer777@gmail.com
     */
}
