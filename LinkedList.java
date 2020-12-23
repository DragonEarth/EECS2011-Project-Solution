package eecs2011.project;

import java.util.Iterator;

public class LinkedList implements Iterable<Integer>{

    private Node head;
    private Node tail;
    private int size = 0;

    static class Node {

        int data;
        Node next;

        Node(int d)
        {
            data = d;
            next = null;
        }
    }

    public void addFirst(int data){
        Node newNode = new Node(data);

        newNode.next = head;
        head = newNode;

        if(size == 0){
            tail = head;
        }
        size++;
    }

    public void addLast(int data){
        Node newNode = new Node(data);

        if(isEmpty()){
            head = newNode;
        }
        else{
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }


    public int removeFirst(){
        if(isEmpty()) return -1;

        int data = head.data;
        head = head.next;
        size--;
        if(size == 0){
            tail = null;
        }
        return data;
    }



    public int size(){ return size;}
    public boolean isEmpty(){ return size == 0; }


    public int first(){
        if(isEmpty()) return -1;
        return head.data;
    }

    public int last(){
        if(isEmpty()) return -1;
        return tail.data;
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            Node curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    int data = curr.data;
                    curr = curr.next;
                    return data;
                }
                return null;
            }


        };
    }

}

