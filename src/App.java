package com.kim.java.AM;

import dto.Article;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;





public class App {
  ArrayList<Article> post= new ArrayList<>();
  public void start(){

    System.out.println("프로그램 시작");
    Scanner sc = new Scanner(System.in);

    LocalDateTime now = LocalDateTime.now();
    String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
    for(int i = 0; i< 3; i++) {
      post.add(new Article(i+1, "temp",formatedNow, i+1 +" temp"));
    }
    int cnt = post.size()+1;
    while(true) {
      String test = sc.nextLine();
      if (test.equals("article list")){
        if (post.isEmpty()){
          System.out.println("게시글이 없습니다.");
        }
        else {
          System.out.println("번호 | 제목 | 내용 | 날짜 | 조회수");
          for(int i =0; i<post.size(); i++){
            System.out.println(post.get(i).id + " | "+  post.get(i).title + " | " + post.get(i).content + " | " + post.get(i).date +" | " +post.get(i).views);

          }
        }
      }
      else if (test.equals("article write")){
        now = LocalDateTime.now();
        formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        System.out.print("제목 : ");
        String tempTitle = sc.nextLine();
        System.out.print("내용 : ");
        String tempContent = sc.nextLine();
        post.add(new Article(cnt, tempTitle,formatedNow, tempContent));
        System.out.println(cnt+"번 글이 생성되었습니다.");
        cnt++;
      }
      else if (test.equals("system exit")) {
        break;
      }
      else if (test.startsWith("article detail ") ) {
        int num;
        try {
          num = Integer.parseInt(test.split(" ")[2]);
        }
        catch(Exception e) {
          System.out.println("숫자를 입력하시오");
          continue;
        }

        int temp = getArticleById(num);
        if (temp != -1) {
          System.out.println(num + "번째글 id : " + post.get(temp).id);
          System.out.println(num + "번째글 제목 : " + post.get(temp).title);
          System.out.println(num + "번째글 내용 :  " + post.get(temp).content);
          System.out.println(num + "번째글 시간 : " + post.get(temp).date);
          post.get(temp).views++;
          System.out.println(num + "번째글 조회수 : " + post.get(temp).views);
        }
        else {
          System.out.println("게시글이 없습니다.");
        }
      }
      else if (test.startsWith("article delete ") ) {
        int num;
        try {
          num = Integer.parseInt(test.split(" ")[2]);
        }
        catch(Exception e) {
          System.out.println("숫자를 입력하시오");
          continue;
        }
        int temp = getArticleById(num);
        if(temp != -1){
          post.remove(temp);
          System.out.println(num +"게시글 삭제했습니다.");
        }
        else {
          System.out.println("게시글이 없습니다.");
        }
      }
      else if (test.startsWith("article modify ") ) {
        int num;
        try {
          num = Integer.parseInt(test.split(" ")[2]);
        }
        catch(Exception e) {
          System.out.println("숫자를 입력하시오");
          continue;
        }
        Article temp = null;
        int tempNum = getArticleById(num);
        if(tempNum != -1){
          temp = post.get(tempNum);
          post.remove(tempNum);
          System.out.print("제목 : ");
          String tempTitle = sc.nextLine();
          System.out.print("내용 : ");
          String tempContent = sc.nextLine();
          temp.title=tempTitle;
          temp.content=tempContent;
          post.add(tempNum,temp);
        }
        else {
          System.out.println("게시글이 없습니다.");
        }
      }
      else {
        System.out.println("존재하지 않는 명령어입니다.");
      }
    }
    sc.close();
    System.out.println("프로그램 끝");
  }
  int getArticleById(int id){
    for(int i = 0; i<post.size(); i++){
      if (id == post.get(i).id) {
        return i;
      }
    }
    return -1;
  }
}
