package com.solvd.laba.conections;

import java.sql.Connection;

public class ConectionPool<T> {
    //thread safe collection

    public T getConnection() {//this should wait, read thread safe collection
        return null;
    }

    public T releaseConnection(Connection con) {
        return null;
    }
}
