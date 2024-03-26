import java.io.*;
import java.util.ArrayList;

public class Sales {
    private ArrayList<String> salesHistory;

    // Constructor
    public Sales() {
        salesHistory = new ArrayList<>();
    }

    // Method to add a sale
    public void addSale(String saleInfo) {
        salesHistory.add(saleInfo);
    }

    // Method to reduce quantity of sold books from inventory
    public void reduceQuantityFromInventory(String bookID, int quantity, Inventory inventory) {
        if (inventory.getBookInventory().containsKey(bookID)) {
            Book book = inventory.getBookInventory().get(bookID);
            int remainingQuantity = book.getQuantity() - quantity;
            if (remainingQuantity >= 0) {
                book.setQuantity(remainingQuantity);
                inventory.updateQuantity(bookID, remainingQuantity);
            } else {
                System.out.println("The quantity is not sufficient in the inventory.");
            }
        } else {
            System.out.println("We are sorry, the book is not in the inventory.");
        }
    }

    // Method to display sales history
    public void displaySalesHistory() {
        System.out.println("Sales History:");
        for (String sale : salesHistory) {
            System.out.println(sale);
        }
    }

    // Method to save sales data to text file
    public void saveSalesToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String sale : salesHistory) {
                writer.println(sale);
            }
            System.out.println("The sales data is saved to text file: " + filename);
        } catch (IOException e) {
            System.out.println("There was an error saving the sales data to text file: " + e.getMessage());
        }
    }

    // Method to load sales data from text file
    public void loadSalesFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                salesHistory.add(line);
            }
            System.out.println("The sales data loaded from text file: " + filename);
        } catch (IOException e) {
            System.out.println("There was an error loading the sales data from text file: " + e.getMessage());
        }
    }
}
