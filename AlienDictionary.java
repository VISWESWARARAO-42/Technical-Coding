import java.util.*;

public class AlienDictionary {
    public static String findAlienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int minLength = Math.min(word1.length(), word2.length());
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            for (int j = 0; j < minLength; j++) {
                char from = word1.charAt(j);
                char to = word2.charAt(j);
                if (from != to) {
                    if (!graph.get(from).contains(to)) {
                        graph.get(from).add(to);
                        inDegree.put(to, inDegree.get(to) + 1);
                    }
                    break; 
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            char current = queue.poll();
            result.append(current);
            for (char neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

       return result.length() == inDegree.size() ? result.toString() : "";
    }

    // Test cases
    public static void main(String[] args) {
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        String[] words2 = {"z", "x", "z"};

        System.out.println("Alien Order (Test Case 1): " + findAlienOrder(words1));
        System.out.println("Alien Order (Test Case 2): " + findAlienOrder(words2)); 
    }
}
