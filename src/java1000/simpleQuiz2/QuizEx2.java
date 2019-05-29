package java1000.simpleQuiz2;

import java.util.Scanner;

class QuizEx2 {
	public static void main(String[] args) {
		String[] data = { "다음 중 키워드가 아닌 것은?`2`final`True`if`public", "다음 중 자바의 연산자가 아닌 것은?`6`&`|`++`!=`/`^",
				"다음 중 메서드의 반환값이 없음을 의미하는 키워드는?`1`void`null`false`", };

		Scanner s = new Scanner(System.in);
		int score = 0;
		int maxScore = data.length;

		for (int i = 0; i < data.length; i++) {
			String[] tmp = data[i].split("`", 3);

			String question = tmp[0];				//문제.
			String answer = tmp[1];					//답.
			String[] choices = tmp[2].split("`");	//선택지.

			System.out.println("[" + (i + 1) + "] " + question);

			for (int x = 0; x < choices.length; x++) {
				System.out.print((x + 1) + "." + choices[x] + "\t");
			}
			System.out.println();

			
			System.out.print("[답] : ");
			String myAnswer = s.nextLine();
			if(myAnswer.equals(answer)) {
				score++;
			}
			
			System.out.println();
			System.out.println();
		}	//for문 끝!.

		// 알맞은 코드를 넣으세요.
		
		System.out.println("정답갯수 : " +score + "/" +maxScore);

		/**
		 * [실행결과]

[1] 다음 중 키워드가 아닌 것은?
1.final 2.True  3.if    4.public
[답]2


[2] 다음 중 자바의 연산자가 아닌 것은?
1.&     2.|     3.++    4.!=    5./     6.^
[답]6


[3] 다음 중 메서드의 반환값이 없음을 의미하는 키워드는?
1.void  2.null  3.false
[답]1


정답개수/전체문항수 :3/3
		 */
	} // main
}
