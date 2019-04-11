public class WaitCommand implements Command {

    @Override
    public void init(String input, Room current) {

    }

    @Override
    public boolean excecute() {
        System.out.println("Waiting...");
        return true;
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
        return "";
    }
}
