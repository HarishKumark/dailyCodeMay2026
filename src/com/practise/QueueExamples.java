package com.practise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExamples {

    public static void main(String[] args) {
        QueueExamples queueExamples = new QueueExamples();
//        queueExamples.arrayDequeExample();
//        queueExamples.priorityQueueEx();
        queueExamples.DequeExample();

    }

    public Deque<Integer> DequeExample() {
        Deque<Integer> d = new ArrayDeque<>();
        d.add(1);
        d.add(2);
        d.add(3);

        d.add(3);
        d.add(2);
        d.add(1);
        System.out.println(d);
        return d;
    }


    //no sorting add has it is
    public Queue<Integer> arrayDequeExample() {
        Queue<Integer> aq = new ArrayDeque<>();
        aq.add(50);
        aq.add(30);
        aq.add(70);
        aq.add(40);
        aq.add(-50);
        aq.add(10);
        System.out.println(aq);
        System.out.println("============");

        System.out.println(aq.poll());
        System.out.println(aq);
        System.out.println("============");

        System.out.println(aq.remove());
        System.out.println(aq);
        System.out.println("============");

        System.out.println(aq.peek());
        System.out.println(aq.isEmpty());
        return aq;

    }

    //sort the numbers and add it
    public Queue<Integer> priorityQueueEx() {

        Queue<Integer> pq = new PriorityQueue<>();

        System.out.println(pq.isEmpty());
        pq.add(50);
        pq.add(20);
        pq.add(40);
        pq.add(10);
        pq.add(30); // may throw an exception if it cannot add
        pq.offer(45);// returns false if it cannot add

        System.out.println(pq);

        System.out.println(pq.poll()); // removes the smallest element
        System.out.println(pq.remove());// removes the next smallest element

        System.out.println(pq);

        System.out.println(pq.peek()); // views the smallest remaining element

        System.out.println(pq);

        System.out.println(pq.isEmpty());
        return pq;
    }
}
