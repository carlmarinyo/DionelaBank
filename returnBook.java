import java.util.Scanner;

public class returnBook(){
    public void returnBook(){
        Scanner input = new Scanner(System.in);
        boolean more = true;

        while(more){ //habang true -- loop
            System.out.println("Enter User ID: ");
            String userId = input.nextLine();

            System.out.println("Enter Item ID to be returned: ");
            int itemId = input.nextInt();
            input.nextLine();


            boolean found = false;
            for (int i = 0; i < count; i++){
                if (availablebooks[i].getItemId == itemId){
                    found = true;
                    availablebooks[i].increaseQuantity();
                    System.out.println("\"" + availablebooks[i].getTitle() + "\" has been returned");
                    break;
                }
            }

            if (!found){
                System.out.println("Item with ID " + itemId + "not found");
            }

            System.out.println("Return more? (y/n): ");
            char response = input.nextLine().charAt(0);

            if (response == 'y' || response == 'Y'){
                more = true;
            }
            else{
                more = false;
            }
        }
    }
}