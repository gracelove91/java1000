package java1000.wordScrambleEx1;

public class WordScrambleEx1 {
//	[문제1] 다음의 예제를 완성하시오.
//
//	getAnswer(String[] strArr)는 배열strArr의 요소중의 하나를 임의로 골라서 반환한다.(Math.random()사용)
//
//	getScrambledWord(String str)는 주어진 문자열 str의 각 문자의 순서를 뒤섞은 다음, 새로운 문자열로 반환한다.
//
//	                                             (Math.random()사용)

	public static void main(String[] args) {
		String[] strArr = { "CHANGE", "LOVE", "HOPE", "VIEW" };

		String answer = getAnswer(strArr);
		String question = getScrambledWord(answer);

		System.out.println("Question:" + question);
		System.out.println("Answer:" + answer);
	} // main

	public static String getAnswer(String[] strArr) {
		int ranIndex = (int)(Math.random() * strArr.length);
		
		return strArr[ranIndex];

	}

	public static String getScrambledWord(String str) {
		char[] charArr = str.toCharArray();
		
		for(int i = 0; i < charArr.length * 2; i++) {
			int ranIndex = (int)(Math.random() * charArr.length);
			
			char tmp = charArr[0];
			charArr[0] = charArr[ranIndex];
			charArr[ranIndex] = tmp;
			
			
		}
		
		return String.valueOf(charArr);
		
	} // scramble(String str)

}
