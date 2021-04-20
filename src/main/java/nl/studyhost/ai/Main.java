package nl.studyhost.ai;

import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        // Assignment 1
        runTreeSearches();
    }

    private static void runTreeSearches() {
        Node start = new Node("Start", 2);
        start.createChildren(7, 7, 823544, start);

        benchmarkTreeSearch("DFS", () -> start.dfs("Exit"), () -> Node.dfsSearches);
        benchmarkTreeSearch("BFS (Stack)", () -> start.stackBfs("Exit"), () -> Node.stackBfsSearches);
        benchmarkTreeSearch("BFS (Queue)", () -> start.queueBfs("Exit"), () -> Node.queueBfsSearches);
    }

    private static void benchmarkTreeSearch(String method, Supplier<Node> runnable, Supplier<Integer> searches) {
        System.out.print("\nStarting " + method + "...");
        long millis = System.currentTimeMillis();
        Node node = runnable.get();
        long duration = System.currentTimeMillis() - millis;
        System.out.println("\tFound: " + node);
        System.out.println(method + ": " + searches.get() + " searches took " + duration + "ms");
    }

}
