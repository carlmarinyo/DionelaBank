public class Book extends Library{
private int pages; //unique attribute ng book

public Book(int itemId, String title, String creator,int quantity, int pages) {  //para mainherit parameter ng libraryitem(id,title,creator,quantity)
    super(itemId,title, creator, quantity);                                     
    this.pages = pages;
}


public int getPages() {
    return pages;
}

@Override
public void displayInfo() {
    System.out.println("Type: Book");
    super.displayInfo(); //tinatawag yung displayInfo ng LibraryItem(para maprint yung id, title, creator, quantity)
    System.out.println("Pages: "+pages+"\n");
}


}


