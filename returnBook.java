public class returnBook{
    public void returnBook(int itemId){
        for(int i = 0; i < count; i++){
            if(availablebooks[i].getItemId() == itemId){ //ichecheck kung magm-match yung itemId na hiningi sa itemId sa list
                availablebooks[i].increaseQuantity(); //dito ko ginamit yung dinagdag ko na method sa libraryItem mo carl
                System.out.println(" \"" + availablebooks[i].getTitle() + "\" has been returned");
                return;
            }
        }
    }
}