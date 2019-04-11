public class Chicken extends Creature{

    public Chicken(String name, Room currentRoom, Level level) {
        super(name, currentRoom, level);
    }

    @Override
    public void move(Room room) {
        moveToRoom(currentRoom.getRandomNeighbor());
    }

    @Override
    public void act(String action) {

    }
}
