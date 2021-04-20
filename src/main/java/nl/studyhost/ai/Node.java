package nl.studyhost.ai;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Node {
    private final String data;
    private final ArrayList<Node> children;
    private int counter;

    public Node(String data, int startpoint) {
        this.data = data;
        this.children = new ArrayList<>();
        this.counter = startpoint;
    }

    public String getData() {
        return this.data;
    }

    public int childLength() {
        return this.getChildren().size();
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public void addChild(Node node) {
        this.getChildren().add(node);
    }

    public void createChildren(int depth, int width, int target, Node root) {
        int w = width;

        while (w > 0 && depth > 0) {
            if (this.counter == target) {
                Node exitnode = new Node("Exit", this.counter);
                root.addChild(exitnode);
            }
            Node tempnode = new Node("Point: " + this.counter, this.counter++);
            root.addChild(tempnode);
            this.createChildren(depth - 1, width, target, tempnode);
            w--;
        }
    }

    /*
     Recursive depth-first search
     */
    public static int dfsSearches = 0;
    public Node dfs(String data) {
        dfsSearches++; // Correct for early equality check
        if (this.getData().equals(data)) return this;

        for (Node child : this.getChildren()) {
            dfsSearches++;
            Node out = child.dfs(data);
            if (out != null) return out;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data='" + this.getData() + '\'' +
                ", counter=" + this.counter +
                '}';
    }
}
