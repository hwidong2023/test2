

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) {
		System.out.println("프로그램 시작");
		Scanner sc = new Scanner(System.in);
		ArrayList<Article> post = new ArrayList<>();
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

				boolean b = false;
				for(int i = 0; i< post.size(); i++) {
					if (post.get(i).id == num) {
						Article temp =post.get(i);
						System.out.println(num + "번째글 id : " + temp.id);
						System.out.println(num + "번째글 제목 : " + temp.title);
						System.out.println(num + "번째글 내용 : " + temp.content);
						System.out.println(num + "번째글 시간 : " + temp.date);
						post.get(i).views++;
						System.out.println(num + "번째글 조회수 : " + post.get(i).views);
						b = true;
						break;
					}
				}

				if (b == false) {
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
				boolean b = false;
				for(int i = 0; i< post.size(); i++) {
					if(post.get(i).id == num){
						post.remove(i);
						System.out.println(num +"게시글 삭제했습니다.");
						b = true;
						break;
					}
				}
				if (b == false) {
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
				int tempNum=-1;
				for(int i = 0; i< post.size(); i++) {
					if(post.get(i).id == num){
						temp = post.get(i);
						post.remove(i);
						tempNum = i;
						break;
					}
				}
				if (tempNum == -1) {
					System.out.println("게시글이 없습니다.");
				}
				else {
					System.out.print("제목 : ");
					String tempTitle = sc.nextLine();
					System.out.print("내용 : ");
					String tempContent = sc.nextLine();
					temp.title=tempTitle;
					temp.content=tempContent;
					post.add(tempNum,temp);
				}
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		sc.close();
		System.out.println("프로그램 끝");
	}
}

class Article {
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