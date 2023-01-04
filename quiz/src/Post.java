public class Post {
  public enum PostType {
    image, text, video
  }

  private final PostType type;
  private final String title;
  private int likes;

  public Post(PostType type, String title, int likes) {
    this.type = type;
    this.title = title;
    this.likes = likes;
  }
}
