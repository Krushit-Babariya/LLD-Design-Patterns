package IteratorDesignPattern;

public class LibraryCatalog {
    public static void displayCollection(IterableCollection<?> collection) {
        System.out.println("--- Catalog Items ---");
        
        Iterator<?> iterator = collection.createIterator();
        int count = 1;
        
        while (iterator.hasNext()) {
            Object item = iterator.next();
            System.out.println(count + ". " + item);
            count++;
        }
        
        System.out.println("Total items: " + (count - 1) + "\n");
    }

    public static void searchByTitle(IterableCollection<?> collection, String searchTerm) {
        System.out.println("Searching for: \"" + searchTerm + "\"");
        System.out.println("--- Results ---");
        
        Iterator<?> iterator = collection.createIterator();
        boolean found = false;
        
        while (iterator.hasNext()) {
            Object item = iterator.next();
            String itemString = item.toString().toLowerCase();
            if (itemString.contains(searchTerm.toLowerCase())) {
                System.out.println("✓ " + item);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No items found matching \"" + searchTerm + "\"");
        }
        System.out.println();
    }
}

