

public class Customer{
    private String Custname;
    private int UserId;
    private LibraryItem borrowedItem;
    

    public Customer(int UserId, String Custname, LibraryItem borrowedItem) {
        this.Custname = Custname;
        this.borrowedItem = borrowedItem;
        this.UserId = UserId;
    }

    public void setName(String Custname) {
        this.Custname = Custname;
    }

    public void setItem(LibraryItem borrowedItem) {
        this.borrowedItem = borrowedItem;
    }
    public int getUserID() {
        return UserId;
    }

    public String getName() {
        return Custname;
    }

    public LibraryItem getItem() {
        return borrowedItem;
    }

    public void CustDisplayBorroweditems() { //para sa Libray class, kunin si Customer name at borrowed item
        System.out.println("Customer ID: " + UserId);
        System.out.println("Customer Name: " + Custname);
        borrowedItem.displayInfo();
    
        System.out.println();
    }
}
