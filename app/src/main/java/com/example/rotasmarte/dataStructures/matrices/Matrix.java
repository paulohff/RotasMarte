package com.example.rotasmarte.dataStructures.matrices;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Matrix<T> implements Iterable<T>, Cloneable {
    private final Object[][] matrix;
    public final int HEIGHT, WIDTH;

    public Matrix(int rows, int cols) {
        if (rows < 1 || cols < 1)
            throw new ArrayIndexOutOfBoundsException();

        matrix = new Object[rows][cols];
        this.HEIGHT = rows;
        this.WIDTH = cols;
    }

    public Matrix(int order) {
        if (order < 1)
            throw new ArrayIndexOutOfBoundsException();

        matrix = new Object[order][order];
        this.HEIGHT = order;
        this.WIDTH = order;
    }

    private Matrix(Matrix<T> other) {
        this.matrix = other.matrix.clone();
        this.HEIGHT = other.HEIGHT;
        this.WIDTH  = other.WIDTH;
    }

    public T getValue(int row, int col) {
        @SuppressWarnings("unchecked")
        T ret = (T) matrix[row][col];
        return ret;
    }

    public void setValue(T value, int row, int col) {
        matrix[row][col] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;
        Matrix<?> matrix1 = (Matrix<?>) o;
        return Arrays.deepEquals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }

    @NonNull
    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int row = 0;
            int col = 0;

            @Override
            public boolean hasNext() {
                if (col < WIDTH)
                    return true;

                if (row < HEIGHT - 1) {
                    col = 0;
                    row++;
                    return true;
                }

                return false;
            }

            @Override
            public T next() {
                T ret = getValue(row, col);
                col++;
                return ret;
            }
        };
    }

    @Override
    public void forEach(@NonNull Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @NonNull
    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    @NonNull
    @Override
    public Matrix<T> clone() {
        return new Matrix<T>(this);
    }

    @NonNull
    public Matrix<T> transpose() {
        Matrix<T> t = new Matrix<T>(this.WIDTH, this.HEIGHT);
        for (int i = 0; i < this.HEIGHT; i++) {
            @SuppressWarnings("unchecked")
            T[] row = (T[]) this.matrix[i];
            for (int j = 0; j <this.WIDTH; j++) {
                // TODO: Clone the elements here
                t.matrix[j][i] = row[j];
            }
        }
        return t;
    }

    public T[] getRow(int index) {
        return (T[]) matrix[index];
    }
}