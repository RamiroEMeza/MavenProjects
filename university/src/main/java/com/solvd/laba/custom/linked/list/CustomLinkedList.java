package com.solvd.laba.custom.linked.list;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CustomLinkedList<T>{
    private static final Logger LOGGER = LogManager.getLogger();
    private CustomNode<T> head;
    private CustomNode<T> tail;
    private int size;

    public void insertFirst(T value){
        CustomNode<T> node = new CustomNode<>(value);
        node.setNext(this.head);
        if (this.head != null) {
            this.head.setPrevious(node);
            if (this.tail == null){
                this.tail = this.head;
            }
        }
        this.head = node;
    }

    public void display(){
        CustomNode<T> node = this.head;
        while (node != null) {
            LOGGER.info(node.getData() );
            node = node.getNext();
        }
    }

    public void displayReverse(){
        CustomNode<T> node = this.tail;
        while (node != null) {
            LOGGER.info(node.getData());
            node = node.getPrevious();
        }
    }

    public int getSize() {
        return this.size;
    }

}
