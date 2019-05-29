package java1000.wordScrambleEx4;

import java.util.Scanner;

class WordScrambleEx4 {
	public static void main(String[] args) {
		String[] strArr = { "CHANGE", "LOVE", "HOPE", "VIEW" };
		Scanner s = new Scanner(System.in); // 한번 생성해 놓고 재사용하면 되므로 반복문 밖으로 이동

		while (true) {
			String answer = getAnswer(strArr);
			String question = getScrambledWord(answer);
			
			//힌트.
			char[] hint = new char[answer.length()];

			// hint를 '_'로 초기화 한다. 정답이 LOVE라면 hint는 "____"이 된다.
			for (int i = 0; i < hint.length; i++) {
				hint[i] = '_';			
			}

			while (true) {
				System.out.println("Question :" + question);
				System.out.print("Your answer is :");

				String input = s.nextLine();

				if (input.equalsIgnoreCase("q"))
					System.exit(0);

				if (input.equalsIgnoreCase(answer)) {
					System.out.println("정답입니다.");
					System.out.println();
					break;
				} else {
					System.out.println(input + "은/는 정답이 아닙니다. 다시 시도해보세요.");
					System.out.println("Hint:" + getHint(answer, hint));
				}
			} // while
		} // outer while

	} // main

	public static String getAnswer(String[] strArr) {
		int idx = (int) (Math.random() * strArr.length);
		return strArr[idx];
	}

	// 섞은 결과가 정답과 같은경우도 있을 수 있음. 복잡하지 않게... 그냥 간단히
	public static String getScrambledWord(String str) { 
            char[] chArr = str.toCharArray(); 

            for(int i=0;i<chArr.length;i++) {

                 int idx = (int)(Math.random()*str.length()); 
                  
                  char tmp = chArr[i]; 
                  chArr[i] = chArr[idx]; 
                  chArr[idx] = tmp; 
            }

		return new String(chArr);

	} // scramble(String str)

	public static String getHint(String answer, char[] hint) { 
            int count = 0; // 힌트에 포함된 '_'의 개수
            
           // 1. 반복문을 이용해서 hint에 포함된 '_'의 개수를 센다.
            for(int i = 0; i < hint.length; i++) {
            	if(hint[i] == '_') count++;
            }
            
           // 2. count의 값이 2보다 클 때만 정답의 한 글자를 hint에 넣는다.
            if(count > 2) {
            	//answer에서 ranIndex번째 한 문자만 뽑아오자.
            	//hint[ranIndex]에 위에서 뽑아온 한 문자를 넣어주자.
            	int ranIndex = (int)(Math.random() * answer.length());
            	char ch = answer.charAt(ranIndex);
            	
            	//만약 뽑아온 문자가 이미 힌트에 존재한다면?? 어떻게 해야할까요오
            	
            	
            	
            	hint[ranIndex] = ch;
            }
           //    정답을 다 알려주는 상황이 되지 않게 하기 위함.


           //    [주의] 반드시 이전 힌트 보다 한글자를 더 보여줘야함.

           //    예를 들어 정답이 "LOVE"이고 이전 힌트가 "L___"이었다면

           //    그 다음 힌트는 "L__E"또는 "L_V_" 와 같은 식이어야 한다.

           //    Math.random()을 사용해서 정답의 한 글자를 골라서 힌트에 넣으면 된다.

            return new String(hint);
      } // getHint()
}

/**
 * /**
 * [실행결과]

Question :HEANGC
Your answer is :chaneg
chaneg은/는 정답이 아닙니다. 다시 시도해보세요.
Hint:__A___
Question :HEANGC
Your answer is :chance
chance은/는 정답이 아닙니다. 다시 시도해보세요.
Hint:__A_G_
Question :HEANGC
Your answer is :hcange
hcange은/는 정답이 아닙니다. 다시 시도해보세요.
Hint:__ANG_
Question :HEANGC
Your answer is :hange
hange은/는 정답이 아닙니다. 다시 시도해보세요.
Hint:C_ANG_
Question :HEANGC
Your answer is :ceange
ceange은/는 정답이 아닙니다. 다시 시도해보세요.
Hint:C_ANG_
Question :HEANGC
Your answer is :change
정답입니다.

Question :VEIW
Your answer is :q

 * @author govlmo91
 *
 */