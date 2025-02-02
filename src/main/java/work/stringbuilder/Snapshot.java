package work.stringbuilder;

public class Snapshot {
    private final String text;

    public Snapshot(String state) {
        this.text = state;
    }

    public String getText() {
        return text;
    }
}
