import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    private String name;
    private HashMap<String, Room> neighbors;
    private String description;
    private ArrayList<Item> items;
    private ArrayList<Creature> creatures;

    public Room(String name) {
        neighbors = new HashMap<>();
        items = new ArrayList<>();
        this.name = name;
        this.description = "generic description";
        creatures = new ArrayList<>();
    }

    public Room(String name, String description) {
        neighbors = new HashMap<>();
        items = new ArrayList<>();
        this.name = name;
        this.description = description;
        creatures = new ArrayList<>();
    }

    public void addNeighbor(String s, String desc) {
        addNeighbor(new Room(s, desc));
    }

    public void addTwoDirectionNeighbor(String s, String desc) {
        addTwoDirectionNeighbor(new Room(s, desc));
    }

    public void addNeighbor(Room n) {
        neighbors.put(n.getName(), n);
    }

    public void addTwoDirectionNeighbor(Room n) {
        addNeighbor(n);
        n.addNeighbor(this);
    }

    public ArrayList<String> getCreatureNames(){
        ArrayList<String> out = new ArrayList<>();
        for (Creature c :
                creatures) {
            out.add(c.getName());
        }

        return out;
    }

    public Room getNeighbor(String name) {
        return neighbors.get(name);
    }

    public String getNeighborNames() {
        return neighbors.keySet().toString();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public String toString(){
        String out = "";
        out = "You are in the " + name + "\n";
        out += "Description: " + getDescription() + "\n";
        out += "You see paths leading to: " + getNeighborNames() + "\n";
        out += "You also see the items: " + getItemNames() + "\n";
        out += "You see the creatures: " + getCreatureNames();
        return out;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getItemNames() {
        return items.toString();
    }

    public void addCreature(Creature c){
        creatures.add(c);
    }

    public void removeCreature(Creature c){
        creatures.remove(c);
    }

    public Room getRandomNeighbor(){
        ArrayList<Room> rooms = new ArrayList<Room>(this.neighbors.values());
        if(rooms.size() <= 0) return null;
        return rooms.get((int)(Math.random()*rooms.size()));
    }

    public ArrayList<Room> getNeighbors(){
        ArrayList<Room> out = new ArrayList<>();
        for (Room r :
                neighbors.values()) {
            out.add(r);
        }
        return out;

    }

    public boolean containsClass(Class c) {
        for (Creature creature :
                creatures) {
            if(creature.getClass() == c) {
                return true;
            }
        }

        for (Item item :
                items) {
            if(item.getClass() == c) return true;
        }

        return false;

    }

}
