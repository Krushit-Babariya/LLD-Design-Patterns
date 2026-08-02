package IteratorDesignPattern;

public class IteratorPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Iterator Design Pattern Demo ===");
        System.out.println("Real-World Analogy: Library Catalog System\n");

        BookCollection bookCollection = new BookCollection();
        bookCollection.addBook(new Book("Design Patterns", "Gang of Four", "978-0201633610"));
        bookCollection.addBook(new Book("Clean Code", "Robert Martin", "978-0132350884"));
        bookCollection.addBook(new Book("Effective Java", "Joshua Bloch", "978-0134685991"));
        bookCollection.addBook(new Book("Head First Design Patterns", "Eric Freeman", "978-0596007126"));

        MagazineCollection magazineCollection = new MagazineCollection();
        magazineCollection.addMagazine(new Magazine("Java World", "Oracle", 202));
        magazineCollection.addMagazine(new Magazine("Software Development", "Tech Media", 45));
        magazineCollection.addMagazine(new Magazine("Code Review", "Dev Publications", 128));

        System.out.println("=== Scenario 1: Display All Books ===");
        LibraryCatalog.displayCollection(bookCollection);

        System.out.println("=== Scenario 2: Display All Magazines ===");
        LibraryCatalog.displayCollection(magazineCollection);

        System.out.println("=== Scenario 3: Search Books by Title ===");
        LibraryCatalog.searchByTitle(bookCollection, "Design");

        System.out.println("=== Scenario 4: Search Magazines by Title ===");
        LibraryCatalog.searchByTitle(magazineCollection, "Java");

        System.out.println("=== Scenario 5: Manual Iteration ===");
        System.out.println("Iterating through books manually:");
        Iterator<Book> bookIterator = bookCollection.createIterator();
        while (bookIterator.hasNext()) {
            Book book = bookIterator.next();
            System.out.println("  - " + book.getTitle());
        }

        System.out.println("\n=== Scenario 6: Reset Iterator ===");
        bookIterator.reset();
        System.out.println("First book again: " + bookIterator.next());

        System.out.println("\n=== Demo Complete ===");
    }
}

