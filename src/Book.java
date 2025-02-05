public class Book {
    private String bookID;
    private String title;
    private String author;
    private double price;
    private int quantity;

    // Constructor
    public Book(String bookID, String title, String author, double price, int quantity) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
