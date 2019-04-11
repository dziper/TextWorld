public class LookCommand implements Command{
    Player player;
    Room currentRoom;
    public LookCommand(Player p) {
        player = p;
    }

    @Override
    public void init(String input, Room current) {
        currentRoom = current;
    }

    @Override
    public boolean excecute() {
        if(currentRoom != null){
            System.out.println(currentRoom.toString());
            System.out.println("You have: " + player.items);
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public boolean hasProperties() {
        return false;
    }

    @Override
    public String success() {
        return "";
    }

    @Override
    public String failed() {
        return "Couldn't look";
    }
}
