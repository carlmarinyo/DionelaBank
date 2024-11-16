public class LibraryItem {
    private int itemId;
    private String title;   //mga general attributes
    private String creator;
    private int quantity;


    public LibraryItem (int itemId, String title, String creator, int quantity) {
        this.itemId = itemId;
        this.title = title;
        this.creator = creator;
        this.quantity = quantity;

    }

  public int getItemId() {
    return itemId;
  }

  public String getTitle() {
      return title;
  }

public String getCreator() {
    return creator;
}

public int getQuantity() {
    return quantity;
}

  public void displayInfo() {  //iooverride ng mga children
    System.out.println("Item ID: " + getItemId());
    System.out.println("Title: " + getTitle());
    System.out.println("Creator: " + getCreator());
    System.out.println("Quantity: " + getQuantity());
  }



}


   