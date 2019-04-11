import java.util.ArrayList;
import java.util.HashMap;

public abstract class Creature {
    Room currentRoom;
    String name;
    String description;
    Level level;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    ArrayList<Item> items;

    public Creature(String name, Room currentRoom, Level level){
        this.name = name;
        this.currentRoom = currentRoom;
        items = new ArrayList<>();
        this.level = level;
        currentRoom.addCreature(this);
    }

    public abstract void move(Room room);

    public abstract void act(String action);

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                return items.remove(items.indexOf(i));
            }
        }
        return null;
    }

    public boolean destroyItem(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String displayInventory() {
        return items.toString();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room n) {
        currentRoom = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean moveToRoom(String name) {
        if (currentRoom.getNeighbor(name) != null) {
            Room newRoom = currentRoom.getNeighbor(name);
            newRoom.addCreature(this);
            currentRoom = newRoom;
            currentRoom.removeCreature(this);

            return true;
        } else return false;
    }

    protected Room getRandomRoom(){
        return level.getRandomRoom();
    }

    protected void moveToRoom(Room room){
        if(room == null) return;
        currentRoom.removeCreature(this);
        room.addCreature(this);
        currentRoom = room;
    }

    protected void moveToRandomRoom(){
        moveToRoom(currentRoom.getRandomNeighbor());
    }

    protected Room search(Class c, int spaces, boolean towards, Room current, ArrayList<Room> visited){
        ArrayList<Room> neighbours = current.getNeighbors();
        visited.add(current);
        for (Room r :
                neighbours) {
            if (visited.contains(r)) continue;
            visited.add(r);
            if((r.containsClass(c) && towards) || (!r.containsClass(c) && !towards)){
                return r;
            }
            else {
                Room nRoom = search(c, spaces - 1, towards, r, visited);
                if (nRoom != null) return nRoom;
            }
        }

        return null;


    }

    protected Room getShortestDistanceRoom(Room target, Room currentRoom, int tolerance){
        HashMap<Room, Integer> dist = new HashMap<>();
        ArrayList<Room> neighbours = currentRoom.getNeighbors();
        ArrayList<Room> settled = new ArrayList<>();

        for (Room nei: neighbours) {
            settled.add(nei);

            dist.put(nei, distance(target, nei, tolerance - 1, settled) + 1);
        }

        return minDistRoom(dist);

    }

    private int distance(Room target, Room currentRoom, int tolerance, ArrayList<Room> settled){
        if(tolerance == 0) return -5;
        if(currentRoom.equals(target)) return 0;
        HashMap<Room, Integer> dist = new HashMap<>();
        ArrayList<Room> neighbours = currentRoom.getNeighbors();

        for (Room nei:
             neighbours) {
            if(settled.contains(nei)) continue;
            else{
                settled.add(nei);
                dist.put(nei, distance(target, nei, tolerance - 1, settled) + 1);
            }
        }

        int smallestDist = minDist(dist);
        return smallestDist;
    }

    private int minDist(HashMap<Room, Integer> dist){
        int smallest = Integer.MAX_VALUE;
        for (Integer i :
                dist.values()) {
            if(i < smallest && i > -1) smallest = i;
        }
        if(smallest == Integer.MAX_VALUE) return -5;
        return smallest;
    }

    private Room minDistRoom(HashMap<Room, Integer> dist){
        int smallest = Integer.MAX_VALUE;

        for (Integer i :
                dist.values()) {
            if(i < smallest && i > -1) smallest = i;
        }

        for (Room r:
             dist.keySet()) {
            if(dist.get(r) == smallest) return r;
        }

        return null;

    }


}
