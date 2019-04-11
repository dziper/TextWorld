import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Level {
    private HashMap<String, Room> rooms;
    ArrayList<Creature> creatures = new ArrayList<>();

    public Level() {
        rooms = new HashMap<>();
    }

    public void addRoom(String name){
        Room n = new Room(name);
        rooms.put(name,n);
    }

    public void init(){
        addRoom("hall", "cool place");
        addRoom("closet", "cold place");
        addRoom("dungeon", "very cold place");
        addRoom("bedroom", "sleepy");

        Item l = new Item("lobster");
        Item oldKey = new Item("oldKey");
        getRoom("hall").addItem(l);
        getRoom("closet").addItem(oldKey);

        addUndirectedEdge("hall", "dungeon");
        addUndirectedEdge("hall", "closet");
        addUndirectedEdge("hall", "bedroom");

        Room hall = getRoom("hall");

        Player p = new Player("bob", getRoom("bedroom"),this);
        Chicken chicken = new Chicken("chicky", getRoom("dungeon"), this);
        Chicken chicken1 = new Chicken("charles", getRoom("closet"), this);

        PopStar pop = new PopStar("Miley Cyrus", getRoom("closet"), this);

        Wumpus wumpy = new Wumpus("Wump", getRoom("bedroom"), this);

        creatures.add(p);
        creatures.add(chicken);
        creatures.add(chicken1);
        creatures.add(pop);
        creatures.add(wumpy);
    }

    public void addRoom(String name, String desc) {
        Room n = new Room(name, desc);
        rooms.put(name, n);
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        n1.addTwoDirectionNeighbor(n2);
    }

    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public Room getRandomRoom(){
        ArrayList<Room> rooms = new ArrayList<Room>(this.rooms.values());
        return rooms.get((int)(Math.random()*rooms.size()));

    }
}
