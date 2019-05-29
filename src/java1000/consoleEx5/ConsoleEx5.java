package java1000.consoleEx5;

import java.io.File;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ConsoleEx5 {
	static String[] argArr; // 입력한 매개변수를 담기위한 문자열배열
	static LinkedList q = new LinkedList(); // 사용자가 입력한 내용을 저장할 큐(Queue)
	static final int MAX_SIZE = 5; // Queue에 최대 5개까지만 저장되도록 한다.

	static File curDir;

	static {
		try {
			curDir = new File(System.getProperty("user.dir"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in); // 한번만 생성해서 재사용하면 되므로 반복문 밖으로 이동

		while (true) {
			try {
				String prompt = curDir.getCanonicalPath() + ">>";
				System.out.print(prompt);

				// 화면으로부터 라인단위로 입력받는다.
				String input = s.nextLine();

				save(input);

				input = input.trim(); // 입력받은 값에서 불필요한 앞뒤 공백을 제거한다.
				argArr = input.split(" +");

				String command = argArr[0].trim();

				if ("".equals(command))
					continue;

				command = command.toLowerCase(); // 명령어를 소문자로 바꾼다.

				if (command.equals("q")) { // q 또는 Q를 입력하면 실행종료한다.
					System.exit(0);
				} else if (command.equals("history")) {
					history();
				} else if (command.equals("dir")) {
					dir();
				} else {
					for (int i = 0; i < argArr.length; i++) {
						System.out.println(argArr[i]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("입력오류입니다.");
			}
		} // while(true)
	} // main

	public static void save(String input) {
		if (input == null || "".equals(input))
			return;

		q.offer(input); // queue에 저장한다.

		// queue의 최대크기를 넣으면 제일 오래된 저장값을 삭제한다.
		if (((LinkedList) q).size() > MAX_SIZE)
			q.remove();
	}

	public static void history() {
		int i = 0;

		// LinkedList의 내용을 보여준다.
		LinkedList tmp = (LinkedList) q;
		ListIterator it = tmp.listIterator();

		while (it.hasNext()) {
			System.out.println(++i + "." + it.next());
		}
	}

	public static void dir() {
		String pattern = "";

		switch (argArr.length) {
		case 1: // dir만 입력한 경우 현재 디렉토리의 모든 파일과 디렉토리를 보여준다.
			File[] files = curDir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("[" + file.getName() + "]");
				} else {
					System.out.println(file.getName());
				}
				System.out.println(file.getAbsolutePath());
			}
			break;
		case 2: // dir과 패턴을 같이 입력한 경우, 예를 들면 dir *.class
			pattern = argArr[1];
			pattern = pattern.toUpperCase(); // 패턴에서 대소문자를 구별하지 않도록 대문자로 변경한다.
			// dir *.class
			// *.class => 끝이 .class 인 파일 출력.
			// 정규표현식 치환? => ".*\.class
//			pattern.replace(target, replacement)

			// dir *ex5.*
			// *ex5.* => ex5라는 단어가 포함된 파일 출력.
			// 정규표현식 치환? => ".*ex5.*" .. ?

			// dir *ex5.????
			// *ex5.???? => ex5를 포함하며, ex5 뒤에 이어질 문자가 4문자인 파일을 출력.(.은 제외.)
			// 정규표현식 치환? => ".*ex5.{4}"

			pattern = pattern.replace(".", "\\.");
			pattern = pattern.replace("*", ".*");
			pattern = pattern.replace("?", ".{1}");
			// ?갯수(num) 세어서 {num} 해줘야한다
//			int count = 0;
//			if(pattern.contains("?")) {
//				Pattern tmp = Pattern.compile("?");
//				Matcher mTmp = tmp.matcher(pattern);
//				while (mTmp.find()) {
//					count++;
//				}
//				pattern.replaceAll("?+", "{" + count + "}");
//			}
			Pattern p = Pattern.compile(pattern);

			File[] matchFiles = curDir.listFiles();
			for (File f : matchFiles) {
				String name = f.getName().toUpperCase();
				Matcher m = p.matcher(name);

				if (m.matches()) {
					if (f.isDirectory()) {
						System.out.println("[" + name + "]");
					} else {
						System.out.println(name);
					}
				}
			}
			break;
		default:
			System.out.println("USAGE : dir [FILENAME]");
		} // switch
	}
} // class
