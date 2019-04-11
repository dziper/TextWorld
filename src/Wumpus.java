import java.util.ArrayList;

public class Wumpus extends Creature {

    public Wumpus(String name, Room currentRoom, Level level) {
        super(name, currentRoom, level);
    }

    @Override
    public void move(Room room) {
        Room roomToMove = search(Player.class, 1, false, currentRoom, new ArrayList<>());

        if(roomToMove == null){
            moveToRandomRoom();
        }

        else{
            moveToRoom(roomToMove);
        }

    }

    @Override
    public void act(String action) {

    }
}
