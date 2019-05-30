package java1000.consoleEx9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ConsoleEx9 {
	static String[] argArr; // 입력한 매개변수를 담기위한 문자열배열
	static LinkedList q = new LinkedList(); // 사용자가 입력한 내용을 저장할 큐(Queue)
	static final int MAX_SIZE = 5; // Queue에 최대 5개까지만 저장되도록 한다.

	static File curDir;

	static {
		try {
			curDir = new File(System.getProperty("user.dir"));
		} catch (Exception e) {
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
				} else if (command.equals("type")) {
					type();
				} else if (command.equals("find")) {
					find();
				} else if (command.equals("find2")) {
					find2();
				} else if (command.equals("cd")) {
					cd();
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
		/* 내용 생략 */

	}

	public static void history() {
		/* 내용 생략 */

	}

	public static void dir() {
		/* 내용 생략 */

	} // dir()

	public static void type() throws IOException {
		/* 내용 생략 */

	}

	public static void find() throws IOException {
		/* 내용 생략 */

	}

	public static void find2() throws IOException {
		if (argArr.length != 3) {
			System.out.println("USAGE : find2 KEYWORD FILE_NAME");
			return;
		}

		String keyword = argArr[1];
		String pattern = argArr[2];

		pattern = pattern.toUpperCase();
		pattern = pattern.replace(".", "\\.");
		pattern = pattern.replace("*", ".*");
		pattern = pattern.replace("?", ".{1}");

		Pattern p = Pattern.compile(pattern);

		for (File f : curDir.listFiles()) {
			String tmp = f.getName().toUpperCase();
			Matcher m = p.matcher(tmp);

			if (m.matches()) {
				if (f.isDirectory())
					continue;

				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);

				String line = "";

				System.out.println("----------------" + f.getName());
				for (int i = 1; (line = br.readLine()) != null; i++) {
					// keyword를 포함한 라인을 출력한다.
					if (line.indexOf(keyword) != -1)
						System.out.println(i + ":" + line);
				}
			}
		} // for

		return;
	}

	public static void cd() throws IOException {
		
		
		if (argArr.length == 1) {
			System.out.println(curDir);
			return;
		} else if (argArr.length > 2) {
			System.out.println("USAGE : cd directory");
			return;
		}

		String subDir = argArr[1].trim();
		
		if(subDir.equals(".")) {
			System.out.println(curDir);
			return;
		}else if(subDir.equals("..")) {
			File pFile = curDir.getParentFile();
			curDir = pFile;
			System.out.println(curDir);
			return;
			
			//cd ???? 일 때.
		}else {
			//아오 잘못함!!!!!!!!!!!!!!!!!!!!!!!!!!
//			File parentFile = curDir.getParentFile().getParentFile();
//			File[] dirList = parentFile.listFiles();
//		
//			for(File dir : dirList) {
//				if(dir.getName().equals(subDir)) {
//					curDir = new File(parentFile.getCanonicalPath() + "//"+ subDir);
//					System.out.println(curDir);
//					break;
//				}else {
//					continue;
//				}
//			}
			
			// ???? 는 subDir에 있다.
			// ???? 가 현재 디렉터리의 하위폴더인지 확인한다. (isDirectory) , (listFile)
			//  --? 현재디렉터리를 언제 다시 재할당해주지? cd .. 했을 때도 재할당해줘야하고.
			// 하위폴더가 아니면 유효하지않은파일입니다 출력.
			// 하위폴더라면 curDir를 subF로 바꾼다.
			
			File[] files = curDir.listFiles(); //현재 파일의 하위파일들.
			File subF = new File(curDir.getCanonicalPath() + "/"+subDir);
			
			for(File file : files) {
				if(file.equals(subF) && subF.isDirectory()) {
					curDir = subF;
					break;
				}
			}
			
			if(!curDir.equals(subF)) {
				System.out.println("유효하지않은 파일입니다.");
			}
			
			
		}
		
		

		/*
		 * 
		 * 다음의 코드를 완성하세요.
		 * 
		 * 1. 입력된 디렉토리(subDir)가 ".."이면,
		 * 
		 * 1.1 현재 디렉토리의 조상 디렉토리를 얻어서 현재 디렉토리로 지정한다.
		 * 
		 * (File클래스의 getParentFile()을 사용)
		 * 
		 * 2. 입력된 디렉토리(subDir)가 "."이면,
		 * 
		 * 단순히 현재 디렉토리의 경로를 화면에 출력한다.
		 * 
		 * 3. 1 또는 2의 경우가 아니면, 입력된 디렉토리(subDir)가 현재 디렉토리의 하위디렉토리인지 확인한다.
		 * 
		 * 3.1 확인결과가 true이면, 현재 디렉토리(curDir)을 입력된 디렉토리(subDir)로 변경한다.
		 * 
		 * 3.2 확인결과가 false이면, "유효하지 않은 디렉토리입니다."고 화면에 출력한다.
		 * 
		 */

	} // cd()
} // class

//[실행결과] - 현재 작업중인 폴더가 C:\java1000\work\Console일 경우
//C:\java1000\work\Console>>cd
//C:\java1000\work\Console
//C:\java1000\work\Console>>cd .
//C:\java1000\work\Console
//C:\java1000\work\Console>>cd ..
//C:\java1000\work>>cd console
//C:\java1000\work\Console>>q
//[출처] [Java1000제] 콘솔 만들기 9 - 도스창 따라하기(cd) (남궁성의 코드초보스터디(자바 java, c언어, javascript, python) |작성자 남궁성