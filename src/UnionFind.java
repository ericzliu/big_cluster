import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnionFind {
    class UFNode {
        public Node node;
        public int rank;
        public UFNode parent;
        public UFNode(Node intrinsic) {
            node = intrinsic;
            rank = 0;
            parent = this;
        }
        public boolean isRoot() {
            return parent == this;
        }
    }

    HashMap<Node, UFNode> nodeMap = new HashMap<>();
    int partitionNumber = 0;

    public UnionFind(List<Node> nodes) {
        for (Node node : nodes) {
            if (!nodeMap.containsKey(node)) {
                UFNode ufNode = new UFNode(node);
                nodeMap.put(node, ufNode);
            }
        }
        partitionNumber = nodeMap.size();
    }

    public Node getPartitionLead(Node node) {
        List<UFNode> path = new ArrayList<>();
        UFNode ufNode = nodeMap.get(node);
        while (!ufNode.isRoot()) {
            path.add(ufNode);
            ufNode = ufNode.parent;
        }
        for (UFNode p : path) {
            p.parent = ufNode;
        }
        return ufNode.node;
    }

    public void mergePartitions(Node node1, Node node2) {
        Node lead1 = getPartitionLead(node1);
        Node lead2 = getPartitionLead(node2);
        if (lead1.equals(lead2))
            return;
        UFNode ufNode1 = nodeMap.get(lead1);
        UFNode ufNode2 = nodeMap.get(lead2);
        if (ufNode1.rank >= ufNode2.rank) {
            ufNode2.parent = ufNode1;
            ufNode1.rank = Math.max(ufNode1.rank, ufNode2.rank + 1);
        }
        else {
            ufNode1.parent = ufNode2;
            ufNode2.rank = Math.max(ufNode2.rank, ufNode1.rank + 1);
        }
        partitionNumber -= 1;
    }
}
