package IteratorDesignPattern;

public interface IterableCollection<T> {
    Iterator<T> createIterator();
}

