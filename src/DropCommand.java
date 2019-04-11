public class DropCommand implements Command {

    Player player;
    String name;
    Room currentRoom;

    public DropCommand(Player p) {
        player = p;
    }

    @Override
    public void init(String input, Room current) {
        name = input;
        currentRoom = current;
    }

    @Override
    public boolean excecute() {
        Item i = player.removeItem(name);

        if (i != null) {
            currentRoom.addItem(i);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasProperties() {
        return true;
    }

    @Override
    public String success() {
        return "Dropped " + name + "!";
    }

    @Override
    public String failed() {
        return "Couldn't drop " + name;
    }
}
