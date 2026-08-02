package IteratorDesignPattern;

public interface Iterator<T> {
    boolean hasNext();
    T next();
    void reset();
}

