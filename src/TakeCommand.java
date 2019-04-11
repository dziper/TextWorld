public class TakeCommand implements Command {

    String itemName;
    Room current;
    Player player;

    public TakeCommand(Player player){
        this.player = player;
    }

    @Override
    public void init(String input, Room current) {
        itemName = input;
        this.current = current;
    }

    @Override
    public boolean excecute() {
        Item item = current.removeItem(itemName);
        player.addItem(item);
        return (item != null);
    }

    @Override
    public boolean hasProperties() {
        return true;
    }

    @Override
    public String success() {
        return "Took " + itemName+"!";
    }

    @Override
    public String failed() {
        return "Couldn't take " + itemName;
    }
}
