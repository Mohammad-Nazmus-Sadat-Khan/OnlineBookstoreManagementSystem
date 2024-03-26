import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize necessary objects and variables
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        Sales sales = new Sales();
        String inventoryFile = "inventory.txt";
        String salesFile = "sales.txt";

        // Load inventory and sales data from files
        inventory.loadInventoryFromFile(inventoryFile);
        sales.loadSalesFromFile(salesFile);

        // Display user interface and handle user inputs
        boolean exit = false;
        while (!exit) {
            System.out.println("\nOnline Bookstore Management System");
            System.out.println("1. Add new books to the inventory");
            System.out.println("2. Update book quantity");
            System.out.println("3. Display the list of available books");
            System.out.println("4. Process sales transactions");
            System.out.println("5. Display sales history");
            System.out.println("6. Save the inventory and sales data to .txt files");
            System.out.println("7. Exit the system");
            System.out.print("Please enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addNewBook(scanner, inventory);
                        break;
                    case 2:
                        updateBookQuantity(scanner, inventory);
                        break;
                    case 3:
                        inventory.displayBooks();
                        break;
                    case 4:
                        processSale(scanner, inventory, sales);
                        break;
                    case 5:
                        sales.displaySalesHistory();
                        break;
                    case 6:
                        inventory.saveInventoryToFile(inventoryFile);
                        sales.saveSalesToFile(salesFile);
                        break;
                    case 7:
                        exit = true;
                        System.out.println("Exiting The Online Bookstore Management System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        scanner.close();
    }
    private static void addNewBook(Scanner scanner, Inventory inventory) {
        System.out.print("Enter book ID: ");
        String bookID = scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Book newBook = new Book(bookID, title, author, price, quantity);
        inventory.addBook(newBook);
        System.out.println("The book is added to the inventory successfully.");
    }

    // Method to update book quantity
    private static void updateBookQuantity(Scanner scanner, Inventory inventory) {
        System.out.print("Please enter book ID: ");
        String bookID = scanner.nextLine();
        System.out.print("Please enter new quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if(inventory.updateQuantity(bookID, quantity)){
            System.out.println("The quantity of the book is updated successfully.");
        } else {
            System.out.println("We are sorry, the book is not in the inventory.");
        }

    }

    // Method to process a sale
    private static void processSale(Scanner scanner, Inventory inventory, Sales sales) {
        System.out.print("Please enter book ID for sale: ");
        String bookID = scanner.nextLine();
        if (inventory.getBookInventory().containsKey(bookID)) {
            Book book = inventory.getBookInventory().get(bookID);
            if (book.getQuantity() > 0) {
                System.out.print("Please enter customer ID: ");
                String customerID = scanner.nextLine();
                System.out.print("Please enter customer name: ");
                String customerName = scanner.nextLine();
                System.out.print("Please enter customer email: ");
                String customerEmail = scanner.nextLine();

                Customer customer = new Customer(customerID, customerName, customerEmail);
                int quantitySold = 1; // For simplicity, selling one book at a time
                book.setQuantity(book.getQuantity() - quantitySold);
                inventory.updateQuantity(bookID, book.getQuantity());
                String saleInfo = "Sale: Book ID - " + bookID + ", Title - " + book.getTitle() +
                        ", Quantity Sold - " + quantitySold + ", Customer - " + customer.getName();
                sales.addSale(saleInfo);
                System.out.println("The sale was processed successfully.");
            } else {
                System.out.println("Sorry, the book is out of stock.");
            }
        } else {
            System.out.println("Sorry, the book is not in the inventory.");
        }
    }
}