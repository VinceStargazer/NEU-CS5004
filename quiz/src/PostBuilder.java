public class PostBuilder {
  private Post.PostType type;
  private String title;
  private int likes;

  public PostBuilder title(String title) {
    this.title = title;
    return this;
  }

  public PostBuilder type(Post.PostType type) {
    this.type = type;
    return this;
  }

  public PostBuilder incrementLikes() {
    likes++;
    return this;
  }

  public Post build() {
    return new Post(type, title, likes);
  }
}
