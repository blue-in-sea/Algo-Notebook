package TikTok;

import java.util.*;

// tasks = [ elementary, middle, high, college, trade]
// ins = [ [e, m], [m, h], [h, c], [h, t] ]
// res = [ elementary, middle, high, college, trade ]

public class CourseScheduleTikTok {

    public static List<String> findPath(List<String> tasks, List<List<String>> ins) {
        if (tasks == null || tasks.size() == 0) return new ArrayList<>();

        Map<String, List<String>> graph = buildGraph(tasks, ins);

        Map<String, Integer> indegree = new HashMap<>();
        for (int i = 0; i < tasks.size(); i++) {
            String node = tasks.get(i);
            for (String nei : graph.get(node)) {
                indegree.put(nei, indegree.getOrDefault(nei, 0) + 1);
            }
        }

        Queue<String> queue = new ArrayDeque<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            String node = tasks.get(i);
            if (!indegree.containsKey(node)) {
                queue.offer(node);
                result.add(node);
            }
        }

        while(!queue.isEmpty()) {
            String node = queue.poll();
            for (String nei : graph.get(node)) {
                // decrease the indegree
                indegree.put(nei, indegree.get(nei) - 1);

                if (indegree.get(nei) == 0) {
                    queue.offer(nei);
                    result.add(nei);
                }
            }
        }

        if (result.size() == tasks.size()) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    private static Map<String, List<String>> buildGraph(List<String> tasks, List<List<String>> ins) {
        Map<String, List<String>> graph = new HashMap<>();

        for (int i = 0; i < tasks.size(); i++) {
            String node = tasks.get(i);
            graph.put(node, new ArrayList<>());
        }

        // <K, V> : <Pre, for the course list>
        for (int i = 0; i < ins.size(); i++) {
            String pre = ins.get(i).get(0);
            String cur = ins.get(i).get(1);
            graph.get(pre).add(cur);
        }

        return graph;
    }

    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>();
        tasks.add("e");
        tasks.add("m");
        tasks.add("h");
        tasks.add("c");
        tasks.add("t");

        System.out.println(tasks);

        List<String> indexO = new ArrayList<>();
        indexO.add("e");
        indexO.add("m");

        List<String> index1 = new ArrayList<>();
        index1.add("m");
        index1.add("h");

        List<String> index2 = new ArrayList<>();
        index2.add("h");
        index2.add("c");

        List<String> index3= new ArrayList<>();
        index3.add("h");
        index3.add("t");

        List<List<String>> ins = new ArrayList<>();
        ins.add(indexO);
        ins.add(index1);
        ins.add(index2);
        ins.add(index3);

        System.out.println(ins);

        System.out.println(findPath(tasks, ins));
    }
}
