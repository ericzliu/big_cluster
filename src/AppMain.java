import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AppMain {
    public int clustering(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int nodeNumber = scanner.nextInt();
        int labelLength = scanner.nextInt();
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < nodeNumber; i++) {
            Node node = new Node(labelLength);
            for (int j = 0; j < labelLength; j++) {
                int b = scanner.nextInt();
                if (b == 1) {
                    node.label[j] = true;
                }
                else {
                    node.label[j] = false;
                }
            }
            nodes.add(node);
        }
        Set<Node> nodeSet = new HashSet<>(nodes);
        UnionFind unionFind = new UnionFind(nodes);
        for (Node node :
                nodeSet) {
            List<Node> nodeList = node.flipOnce();
            for (Node cost1Node : nodeList) {
                if (nodeSet.contains(cost1Node)) {
                    unionFind.mergePartitions(node, cost1Node);
                }
            }
            List<Node> nodeList1 = node.flipTwice();
            for (Node cost2Node : nodeList1) {
                if (nodeSet.contains(cost2Node)) {
                    unionFind.mergePartitions(node, cost2Node);
                }
            }
        }
        return unionFind.partitionNumber;
    }

    public static void main(String[] args) throws FileNotFoundException {
        AppMain app = new AppMain();
        int partitionNumber = app.clustering("clustering_big.txt");
        System.out.println(partitionNumber);
    }
}
