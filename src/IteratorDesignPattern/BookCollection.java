package IteratorDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class BookCollection implements IterableCollection<Book> {
    private final List<Book> books;

    public BookCollection() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public int size() {
        return books.size();
    }

    @Override
    public Iterator<Book> createIterator() {
        return new BookIterator(books);
    }

    private static class BookIterator implements Iterator<Book> {
        private final List<Book> books;
        private int position;

        public BookIterator(List<Book> books) {
            this.books = books;
            this.position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < books.size();
        }

        @Override
        public Book next() {
            if (hasNext()) {
                return books.get(position++);
            }
            return null;
        }

        @Override
        public void reset() {
            position = 0;
        }
    }
}

