import java.util.ArrayList;

public class PopStar extends Creature {


    public PopStar(String name, Room currentRoom, Level level) {
        super(name, currentRoom, level);
    }

    @Override
    public void move(Room room) {

        final int spaces = 3;

        Room closePlayerRoom = search(Player.class, spaces, true, currentRoom, new ArrayList<>());

        Room roomToMove = getShortestDistanceRoom(closePlayerRoom, currentRoom, spaces);

        if(roomToMove == null) {
            return;
        }

        moveToRoom(roomToMove);
    }

    @Override
    public void act(String action) {

    }
}
