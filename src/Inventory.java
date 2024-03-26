import java.io.*;
import java.util.HashMap;

public class Inventory {
    private HashMap<String, Book> bookInventory;

    // Constructor
    public Inventory() {
        bookInventory = new HashMap<>();
    }

    // Method to add a new book to inventory
    public void addBook(Book book) {
        bookInventory.put(book.getBookID(), book);
    }

    // Method to update book quantity
    public boolean updateQuantity(String bookID, int quantity) {
        if (bookInventory.containsKey(bookID)) {
            Book book = bookInventory.get(bookID);
            book.setQuantity(quantity);
            return true;
        } else {
            System.out.println("We are sorry, the book is not in the inventory.");
            return false;
        }
    }

    // Method to display available books
    public void displayBooks() {
        System.out.println("The books available:");
        for (Book book : bookInventory.values()) {
            System.out.println("ID: " + book.getBookID() + ", Title: " + book.getTitle() +
                    ", Author: " + book.getAuthor() + ", Price: $" + book.getPrice() +
                    ", Quantity: " + book.getQuantity());
        }
    }

    // Method to save inventory data to text file
    public void saveInventoryToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Book book : bookInventory.values()) {
                writer.println(book.getBookID() + "," + book.getTitle() + "," +
                        book.getAuthor() + "," + book.getPrice() + "," + book.getQuantity());
            }
            System.out.println("The inventory data is saved to text file: " + filename);
        } catch (IOException e) {
            System.out.println("There was an error saving the inventory data to text file: " + e.getMessage());
        }
    }

    // Method to load inventory data from text file
    public void loadInventoryFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String bookID = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    int quantity = Integer.parseInt(parts[4]);
                    Book book = new Book(bookID, title, author, price, quantity);
                    bookInventory.put(bookID, book);
                }
            }
            System.out.println("The inventory data loaded from text file: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading inventory data from file: " + e.getMessage());
        }
    }

    public HashMap<String, Book> getBookInventory() {
        return bookInventory;
    }
}
