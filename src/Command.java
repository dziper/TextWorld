public interface Command {
    void init(String input, Room current);
    boolean excecute();
    boolean hasProperties();
    String success();
    String failed();
}
