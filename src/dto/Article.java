package dto;

public class Article {
  public int id;
  public String title;
  public String content;
  public String date;
  public int views = 0;

  public Article(int id, String title, String date, String content) {
    this.id =id;
    this.title =title;
    this.date = date;
    this.content = content;
  }
}