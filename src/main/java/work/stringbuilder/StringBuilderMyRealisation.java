package work.stringbuilder;

import java.util.ArrayDeque;
import java.util.Deque;

public class StringBuilderMyRealisation {
    private StringBuilder sb = new StringBuilder();
    private final Deque<Snapshot> history = new ArrayDeque<>();

    public void append(String newText) {
        saveSnapshot();
        sb.append(newText);
    }

    public void delete(int start, int end) {
        if (start < 0 || end > sb.length() || start > end) {
            throw new StringIndexOutOfBoundsException();
        }
        saveSnapshot();
        sb.delete(start, end);
    }

    public void insert(int charAt, String newText) {
        if (charAt < 0 || charAt > sb.length()) {
            throw new StringIndexOutOfBoundsException(charAt);
        }
        saveSnapshot();
        sb.insert(charAt, newText);
    }

    public void undo() {
        if (!history.isEmpty()) {
            sb = new StringBuilder(history.pop().getText());
        }
    }

    public String getText() {
        return sb.toString();
    }

    private void saveSnapshot() {
        history.push(new Snapshot(sb.toString()));
    }
}
