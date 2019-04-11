public class GoCommand implements Command {
    private Room currentRoom;
    String name;
    Player player;

    public GoCommand(Player p) {
        player = p;
    }

    @Override
    public void init(String input, Room current) {
        currentRoom = current;
        name = input;
    }

    @Override
    public boolean excecute() {
        if(currentRoom.getNeighborNames().contains(name)){
            player.move(currentRoom.getNeighbor(name));
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean hasProperties() {
        return true;
    }

    @Override
    public String success() {
        return "Went to " + name + "!" ;
    }

    @Override
    public String failed() {
        return "Couldn't go to " + name;
    }
}
