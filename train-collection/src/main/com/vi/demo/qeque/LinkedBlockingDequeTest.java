package com.vi.demo.qeque;

import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequeTest {

    public static void main(String[] args) {

        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();
        System.out.println("add 1");
        linkedBlockingDeque.add(1);
        linkedBlockingDeque.add(2);
        linkedBlockingDeque.add(3);
        linkedBlockingDeque.add(4);
        //从队首获取元素，同时获取的这个元素将从原队列删除；
        System.out.println(linkedBlockingDeque.poll());

        System.out.println(linkedBlockingDeque.pollLast());

        //表示返回栈顶的元素，同时该元素从栈中删除，当栈中没有元素时，调用该方法会发生异常
        System.out.println(linkedBlockingDeque.pop());


        System.out.println(linkedBlockingDeque.size());
    }


}
