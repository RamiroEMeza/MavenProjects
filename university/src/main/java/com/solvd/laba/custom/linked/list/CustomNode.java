package com.solvd.laba.custom.linked.list;

public class CustomNode<T> {
    private CustomNode<T> next;
    private CustomNode<T> previous;
    private T data;

    public CustomNode(T data) {
        this.data = data;
    }

    public CustomNode(T data, CustomNode<T> next, CustomNode<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    public CustomNode<T> getNext() {
        return next;
    }

    public CustomNode<T> getPrevious() {
        return previous;
    }

    public T getData() {
        return data;
    }

    public void setNext(CustomNode<T> next) {
        this.next = next;
    }

    public void setPrevious(CustomNode<T> previous) {
        this.previous = previous;
    }

    public void setData(T data) {
        this.data = data;
    }
}
