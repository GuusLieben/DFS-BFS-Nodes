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

    /*
     Stack based breadth-first search
     */
    public static int stackBfsSearches = 0;
    public Node stackBfs(String data) {
        Stack<Node> stack = new Stack<>();
        stack.add(this);

        while (!stack.isEmpty()) {
            for (Node node : stack) {
                stackBfsSearches++;
                if (node.getData().equals(data)) return node;
            }

            Stack<Node> next = new Stack<>();
            for (Node node : stack) {
                next.addAll(node.getChildren());
            }
            stack = next;
        }
        return null;
    }

    /*
     Queue based breadth-first search
     */
    public static int queueBfsSearches = 0;
    public Node queueBfs(String data) {
        queueBfsSearches++;
        if (this.getData().equals(data)) return this;
        Queue<Node> queue = new LinkedList<>(this.getChildren());

        Node child;
        while (!queue.isEmpty()) {
            child = queue.poll();
            queueBfsSearches++;
            if (child.getData().equals(data)) return child;
            queue.addAll(child.getChildren());
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
