package com.solvd.laba.custom.linked.list;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CustomLinkedList<T> {
    private static final Logger LOGGER = LogManager.getLogger();
    private CustomNode<T> head;
    private CustomNode<T> tail;
    private int size;

    public void insertFirst(T value) {
        CustomNode<T> node = new CustomNode<>(value);
        node.setNext(this.head);
        if (this.head != null) {
            this.head.setPrevious(node);
            if (this.tail == null) {
                this.tail = this.head;
            }
        }
        this.head = node;
        this.size++;
    }

    public void remove(int position) {
        if (position == 0) {
            this.head = this.head.getNext();
            this.head.setPrevious(null);
            this.size--;
        } else if (position > 0 && position < this.size) {
            CustomNode<T> node = head;
            for (int i = 0; i < position; i++) {
                node = node.getNext();
            }
            if (node != null) {
                node.getPrevious().setNext(node.getNext());
                node.getNext().setPrevious(node.getPrevious());
                this.size--;
            }
        } else {
            throw new IndexOutOfBoundsException("No CustomNode found in position");
        }
    }

    public CustomNode<T> getNode(int position) {
        CustomNode<T> node = head;
        if (position == 0) {
            return head;
        } else if (position > 0 && position < this.size) {
            for (int i = 0; i < position; i++) {
                node = node.getNext();
            }
            return node;
        } else {
            throw new IndexOutOfBoundsException("No CustomNode found in position");
        }
    }

    public void display() {
        CustomNode<T> node = this.head;
        while (node != null) {
            LOGGER.info(node.getData());
            node = node.getNext();
        }
    }

    public void displayReverse() {
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
