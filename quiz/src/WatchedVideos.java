import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class WatchedVideos {
  public static List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
    int n = friends.length;
    Queue<Integer> q = new LinkedList<>();
    q.add(id);
    boolean[] visit = new boolean[n];
    visit[id] = true;
    while (level-- > 0) {
      for (int i = q.size(); i > 0; i--) {
        int person = q.poll();
        for (int nei : friends[person]) {
          if (!visit[nei]) {
            q.add(nei);
            visit[nei] = true;
          }
        }
      }
    }
    Map<String, Integer> cnt = new HashMap<>();
    System.out.println(q);
    for (int person : q)
      for (String video : watchedVideos.get(person))
        cnt.put(video, cnt.getOrDefault(video, 0) + 1);
    List<String> res = new ArrayList<>(cnt.keySet());
    res.sort((a, b) -> {
      int freq1 = cnt.get(a), freq2 = cnt.get(b);
      return freq1 == freq2 ? a.compareTo(b) : freq1 - freq2;
    });
    for (String s : res)
      System.out.println(s + ": " + cnt.get(s));
    return res;
  }

  public static void main(String[] args) {
    String[][] videos = new String[][]{{"Stranger Things", "Fast & Furious", "Avengers"}, {"Stranger Things", "Game of Thrones", "Empresses in the Palace"}, {"Attack on Titans", "Breaking Bad", "Avengers", "Saw"}, {"Attack on Titans", "Stranger Things", "Game of Thrones", "Rick and Morty", "Interstellar"}, {"Sponge Bob", "Titanic", "Saw", "Ultraman", "Empresses in the Palace"}, {"Sponge Bob", "Fast & Furious", "Interstellar", "Ultraman"}, {"Attack on Titans", "Stranger Things", "Game of Thrones"}, {"Breaking Bad", "Fast & Furious", "Avengers"}, {"Stranger Things", "SpongeBob", "Rick and Morty"}, {"Rick and Morty", "Planet Earth", "Interstellar", "Ultraman", "Empresses in the Palace"}, {"Game of Thrones", "Titanic", "Rick and Morty", "Planet Earth", "Empresses in the Palace"}, {"Attack on Titans", "Game of Thrones", "Harry Potter", "Breaking Bad", "Planet Earth"}, {"Stranger Things", "Harry Potter", "Rick and Morty", "Planet Earth"}, {"Stranger Things", "Breaking Bad", "Fast & Furious"}, {"Fast & Furious", "Avengers", "Titanic", "Interstellar"}, {"Stranger Things", "Game of Thrones", "Breaking Bad", "Fast & Furious"}};
    int[][] friends = {{1, 2, 10, 11, 14, 8}, {0, 3}, {0, 3, 5, 11, 13, 14}, {1, 2, 4, 8, 10, 12, 13}, {3, 9}, {2, 6, 7, 12, 13, 15}, {5}, {5}, {0, 3}, {4}, {0, 3}, {0, 2}, {3, 5}, {2, 3, 5}, {0, 2}, {5}};
    List<List<String>> watchedVideos = new ArrayList<>();
    for (String[] arr : videos)
      watchedVideos.add(Arrays.asList(arr));
//    watchedVideosByFriends(watchedVideos, friends, 0, 2);

    Map<String, Integer> cnt = new HashMap<>();
    for (String[] video : videos)
      for (String v : video) cnt.put(v, cnt.getOrDefault(v, 0) + 1);
    List<String> res = new ArrayList<>(cnt.keySet());
    res.sort((a, b) -> {
      int freq1 = cnt.get(a), freq2 = cnt.get(b);
      return freq1 == freq2 ? a.compareTo(b) : freq1 - freq2;
    });
    for (String s : res)
      System.out.println(s + ": " + cnt.get(s));
  }
}
