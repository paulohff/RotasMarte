package com.example.rotasmarte.dataStructures.matrices;

import androidx.annotation.NonNull;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class KeyMatrix<K, T> extends Matrix<T> {
    K[] rowKeys, colKeys;

    public KeyMatrix(K[] rowKeys, K[] colKeys) throws KeyException {
        super(rowKeys.length, colKeys.length);
        setRowKeys(rowKeys);
        setColKeys(colKeys);
    }

    public KeyMatrix(K[] keys) throws KeyException {
        super(keys.length);
        setRowKeys(keys);
        this.colKeys = keys;
    }

    public KeyMatrix(List<K> keys) throws KeyException{
        super(keys.size());
        setRowKeys(keys);
        this.colKeys = (K[]) keys.stream().toArray(Object[] :: new);
    }

    public K[] getRowKeys() {
        return rowKeys;
    }

    public K[] getColKeys() {
        return colKeys;
    }

    public void setRowKeys(K[] rowKeys) throws KeyException {
        checkKeyIntegrity(rowKeys);
        this.rowKeys = rowKeys;
    }

    public void setColKeys(K[] colKeys) throws KeyException {
        checkKeyIntegrity(colKeys);
        this.colKeys = colKeys;
    }

    public void setRowKeys(List<K> rowKeys) throws KeyException {
        @SuppressWarnings("unchecked")
        K[] keys = (K[]) rowKeys.stream().toArray(Object[] :: new);
        setRowKeys(keys);
    }

    public void setColKeys(List<K> colKeys) throws KeyException {
        @SuppressWarnings("unchecked")
        K[] keys = (K[]) colKeys.stream().toArray(Object[] :: new);
        setColKeys(keys);
    }

    private void checkKeyIntegrity(K[] keys) throws KeyException {
        ArrayList<K> repeats = new ArrayList<K>(keys.length);
        int i = 0;

        for (K k : keys) {
            if (k == null)
                throw new NullPointerException();

            if (repeats.contains(k))
                throw new KeyException("Key already present");

            repeats.add(k);
        }
    }

    @NonNull
    @Override
    public KeyMatrix<K, T> transpose() {
        KeyMatrix<K, T> t = (KeyMatrix<K, T>) super.transpose();
        t.colKeys = this.rowKeys.clone();
        t.rowKeys = this.colKeys.clone();
        return t;
    }

    public T getValue(K row, K col) {
        int ridx, cidx;
        ridx = Arrays.asList(rowKeys).indexOf(row);
        cidx = Arrays.asList(colKeys).indexOf(col);
        if (ridx == -1 || cidx == -1)
            throw new NoSuchElementException("\"" + row.toString() + "\" is not a key.");
        return super.getValue(ridx, cidx);
    }

    public void setValue(T value, K row, K col) {
        int ridx, cidx;
        ridx = Arrays.asList(rowKeys).indexOf(row);
        cidx = Arrays.asList(colKeys).indexOf(col);
        if (ridx == -1 || cidx == -1)
            throw new NoSuchElementException("\"" + row.toString() + "\" is not a key.");
        super.setValue(value, ridx, cidx);
    }

    public T[] getRow(K key) {
        return super.getRow(Arrays.asList(rowKeys).indexOf(key));
    }
}
