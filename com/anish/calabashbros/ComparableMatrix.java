package com.anish.calabashbros;

import java.util.ArrayList;

public class ComparableMatrix<T extends Comparable<T>> {

    private int rows, cols, size;
    private ArrayList<ArrayList<T>> elements;

    public ComparableMatrix(int rows, int cols, int size) {
        this.rows = rows;
        this.cols = cols;
        this.size = size;
        elements = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            ArrayList<T> row = new ArrayList<T>(cols);
            for (int j = 0; j < cols; j++) {
                row.add(null);
            }
            elements.add(row);
        }
    }

    public void setElement(int row, int col, T element) {
        elements.get(row).set(col, element);
    }

    public T getElement(int row, int col) {
        return elements.get(row).get(col);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return cols;
    }

    public int getSize() {
        return size;
    }

}
