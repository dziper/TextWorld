public class AddCommand implements Command{

    String name;
    Room currentRoom;
    Level level;

    public AddCommand(Level level){
        this.level = level;
    }

    @Override
    public void init(String input, Room current) {
        name = input;
        currentRoom = current;
    }

    @Override
    public boolean excecute() {
        if (level.getRoom(name) == null) {
            level.addRoom(name);
            level.addUndirectedEdge(currentRoom.getName(), name);
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
        return "Added room " + name;
    }

    @Override
    public String failed() {
        return "Couldn't add " + name;
    }
}
