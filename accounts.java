

public class accounts{
    private String name;
    private String password;
    private int UserID;
    private int ItemsBorrowed;
    private String Accounttype;
    private LibraryItem borrowedItem;

    //utilization ng constructor overloading
    //pra sa students
    public accounts(int UserID, String name, String password, LibraryItem borrowedItem, String Accounttype) {
        this.UserID = UserID;
        this.name = name;
        this.password = password;
        this.Accounttype = Accounttype;

        // array
        this.borrowedItem = borrowedItem;
        
    }


    // pra sa admins
    public accounts(String name, String password, String Accounttype) {
        this.name = name;
        this.password = password;
        this.Accounttype = Accounttype;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setAccounttype() {
        this.Accounttype = Accounttype;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String password) {
        this.password = password;
    }

    public void setItem(LibraryItem borrowedItem) {
        this.borrowedItem = borrowedItem;
    }

    public void setItemsBorrowed(int ItemsBorrowed) {
        this.ItemsBorrowed = ItemsBorrowed;
    }

    public String getAccounttype() {
        return Accounttype;
    }

    public int getBorrowedItems() {
        return ItemsBorrowed;
    }

    public String getpassword(){
        return password;
    }

    public int getUserID() {
        return UserID;
    }

    public String getName() {
        return name;
    }

    public LibraryItem getBorrowedItem() {
        return borrowedItem;
    }

    public void DisplayBorroweditems() { //para sa Libray class, kunin si Customer name at borrowed item
        System.out.println("Customer Name: " + name);
        borrowedItem.displayInfo();
        System.out.println();
        System.out.println("----Quantity of Borrowed items: " + getBorrowedItems() + "----For Customer: " + name);

    
        System.out.println();
    }

    public void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Password: " + getpassword());
        System.out.println("Account Type: " + getAccounttype());
        System.out.println();
    }

}