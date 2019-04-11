import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Level level = new Level();

        level.init();

        String response = "";
        Scanner s = new Scanner(System.in);

        displayCommands();
        do {

            for (Creature c :
                    level.creatures) {

                if(c instanceof Player){
                    System.out.println("-------------------------");
                    System.out.println("\nYou are in the " + c.getCurrentRoom().getName());
//                    System.out.println("Current inventory: " + c.displayInventory());
                    System.out.print("What do you want to do? >");
                    response = s.nextLine();

                    c.act(response);

                }else{
                    c.move(null);
                }

            }

        } while (!response.equals("quit"));
    }

    public static void displayCommands() {
        System.out.println("\n'go <roomname>' = move to room");
        System.out.println("'look' = see possible moves");
        System.out.println("'add room <roomname> ' = add new neighbor to current room");
        System.out.println("'quit' = quit the game");
        System.out.println("'take <itemname>' = take item");
        System.out.println("'drop <itemname>' = drop item");
    }
}
