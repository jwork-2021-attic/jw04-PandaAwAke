package com.anish.calabashbros;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {

    private ComparableMatrix<T> elements;

    @Override
    public void load(ComparableMatrix<T> elements) {
        this.elements = elements;
    }

    private void swap(int x1, int y1, int x2, int y2) {
        T temp;
        temp = elements.getElement(x1, y1);
        elements.setElement(x1, y1, elements.getElement(x2, y2));
        elements.setElement(x2, y2, temp);
        plan += "" + elements.getElement(x1, y1) + "<->" + elements.getElement(x2, y2) + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < elements.getSize() - 1; i++) {
                int x1 = i / elements.getColumns();
                int y1 = i % elements.getColumns();
                int x2 = (i + 1) / elements.getColumns();
                int y2 = (i + 1) % elements.getColumns();
                T element1 = elements.getElement(x1, y1);
                T element2 = elements.getElement(x2, y2);
                if (element1 == null || element1.compareTo(element2) > 0) {
                    swap(x1, y1, x2, y2);
                    sorted = false;
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}