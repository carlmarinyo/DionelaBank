

public class Customer{
    private String Custname;
    private int UserId;
    private int ItemsBorrowed;

    private LibraryItem borrowedItem;
    

    public Customer(int UserId, String Custname, int ItemsBorrowed, LibraryItem borrowedItem) {
        this.Custname = Custname;
        this.borrowedItem = borrowedItem;
        this.UserId = UserId;
        this.ItemsBorrowed = ItemsBorrowed;
    }

    public void setName(String Custname) {
        this.Custname = Custname;
    }

    public void setItem(LibraryItem borrowedItem) {
        this.borrowedItem = borrowedItem;
    }

    public void setItemsBorrowed(int ItemsBorrowed) {
        this.ItemsBorrowed = ItemsBorrowed;
    }

    public int getBorrowedItems() {
        return ItemsBorrowed;
    }

    public int getUserID() {
        return UserId;
    }

    public String getName() {
        return Custname;
    }

    public LibraryItem getBorrowedItem() {
        return borrowedItem;
    }

    public void CustDisplayBorroweditems() { //para sa Libray class, kunin si Customer name at borrowed item
        System.out.println("Customer ID: " + UserId);
        System.out.println("Customer Name: " + Custname);
        borrowedItem.displayInfo();
        System.out.println();
        System.out.println("----Quantity of Borrowed items: " + getBorrowedItems() + "----For Customer: " + Custname);

    
        System.out.println();
    }
}
