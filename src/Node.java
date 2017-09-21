import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
    public boolean [] label;

    public Node(int size) {
        label = new boolean[size];
    }

    public Node(Node other) {
        label = Arrays.copyOf(other.label, other.label.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return Arrays.equals(label, node.label);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(label);
    }

    public List<Node> flipOnce() {
        List<Node> results = new ArrayList<>();
        int length = label.length;
        for (int i = 0; i < length; i++) {
            Node node = new Node(this);
            node.label[i] = !node.label[i];
            results.add(node);
        }
        return results;
    }

    public List<Node> flipTwice() {
        List<Node> results = new ArrayList<>();
        for (int i = 0; i < label.length; i++) {
            for (int j = i + 1; j < label.length; j++) {
                Node node = new Node(this);
                node.label[i] = !node.label[i];
                node.label[j] = !node.label[j];
                results.add(node);
            }
        }
        return results;
    }
}
