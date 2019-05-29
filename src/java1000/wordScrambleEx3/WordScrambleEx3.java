package java1000.wordScrambleEx3;

import java.util.Scanner;

/**
 * 
 * [실행결과]
 * 
 * Question :IWVE Your answer is :ievw ievw은/는 정답이 아닙니다. 다시 시도해보세요. Question
 * :IWVE Your answer is :view 정답입니다.
 * 
 * Question :HOEP Your answer is :hope 정답입니다.
 * 
 * Question :GNCAEH Your answer is :change 정답입니다.
 * 
 * Question :HECNAG Your answer is :q
 */
class WordScrambleEx3 {
	public static void main(String[] args) {
		String[] strArr = { "CHANGE", "LOVE", "HOPE", "VIEW" };

		while (true) {
			String answer = getAnswer(strArr);			//strArr 배열에 저장된 문자열 중 무작위로 선택해 뽑아온다. 
			String question = getScrambledWord(answer); //위에서 무작위로 뽑아온 문자열의 위치를 뒤섞는다.

			// hint : while문을 중첩해서(2개의 while문) 작성하세요.
			while (true) {
				System.out.println("Question :" + question);
				System.out.print("Your answer is :");

				Scanner scan = new Scanner(System.in);
				String input = scan.nextLine();

				// q면 종료.
				if (input.trim().equalsIgnoreCase("Q")) {
					System.out.println("빠잉");
					System.exit(0);
				}

				// 정답이 아니면? 다시 입력받자.
				if (!(input.trim().equalsIgnoreCase(answer))) {
					System.out.println("땡!!!!!! 다시.");
					continue;

					// 정답이면? 다른 문제 보여주자.
				} else if (input.trim().equalsIgnoreCase(answer)) {
					System.out.println("정답이다아아아아");
					break;
				}

			} // while
		}

	} // main

	public static String getAnswer(String[] strArr) {
		int idx = (int) (Math.random() * strArr.length);
		return strArr[idx];
	}

	public static String getScrambledWord(String str) {
		char[] chArr = str.toCharArray();

		for (int i = 0; i < str.length(); i++) {
			int idx = (int) (Math.random() * str.length());
			char tmp = chArr[i];
			chArr[i] = chArr[idx];
			chArr[idx] = tmp;
		}

		return new String(chArr);
	} // scramble(String str)
}
