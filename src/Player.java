import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Creature{
    HashMap<String, Command> commands;

    public Player(String name, Room currentRoom, Level level) {
        super(name, currentRoom, level);
        commands = new HashMap<>();
        initCommands();
    }

    private void initCommands() {
        commands.put("take", new TakeCommand(this));
        commands.put("look", new LookCommand(this));
        commands.put("go", new GoCommand(this));
        commands.put("add", new AddCommand(this.level));
        commands.put("drop", new DropCommand(this));
        commands.put("wait", new WaitCommand());

    }

    @Override
    public void move(Room room) {
        moveToRoom(room);
    }

    @Override
    public void act(String action) {

        String[] strings = action.split(" ");

        if(strings[0].equals("quit")){
            System.out.println("Quitting the game...");
            return;
        }

        ArrayList<String> words = toArrList(strings);
        actuallyAct(words);

    }

    private ArrayList<String> toArrList(String[] strings) {
        ArrayList<String> out = new ArrayList<>();
        for (String s :
                strings) {
            out.add(s);
        }
        return out;
    }

    private void actuallyAct(ArrayList<String> words) {
        String command = words.get(0);

        Command c = commands.get(command);

        if(c == null){
            System.out.println(words.get(0) + " failed, type an existing command");
            Main.displayCommands();
            return;
        }

        if(c.hasProperties()) {
            if(words.size() == 1){
                System.out.println(words.get(0) + " failed, type \"" + words.get(0) + " <an object to " + words.get(0) + ">\"");
            }else{
                c.init(words.get(1), currentRoom);
            }

        }else{
            c.init(null, currentRoom);
        }
        if(c.excecute()){
            System.out.println(c.success());
        }
        else{
            System.out.println(c.failed());
        }
        System.out.println();

    }


}