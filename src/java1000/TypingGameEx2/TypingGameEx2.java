package java1000.TypingGameEx2;

import java.awt.Toolkit;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

class TypingGameEx2 {
	Vector<Word> words = new Vector<>();
	String[] data = { "A", "B", "C", "D", "F", "G", "H", "Q", "W" };
	int interval = 2 * 1000; // 2초

	int life = 3;
	int score = 0;

	WordGenerator wg = new WordGenerator();
	WordDropper wd = new WordDropper();

	public static void main(String args[]) {
		TypingGameEx2 game = new TypingGameEx2();

		game.wg.start();
		game.wd.start();

		Vector<Word> words = game.words;

		while (true) {
			System.out.println("LIFE:" + game.life + " SCORE:" + game.score);
			System.out.println(words);

			String prompt = ">>";
			System.out.print(prompt);

			// 화면으로부터 라인단위로 입력받는다.
			Scanner s = new Scanner(System.in);
			String input = s.nextLine().trim();

			/*
			 * 
			 * 다음의 코드를 완성하세요.
			 * 
			 * 
			 * 
			 * 1. 반복문을 이용해서 사용자가 입력한 단어가 words에 있는지 확인한다.
			 * 
			 * 2. 있으면 words에서 삭제하고 '삑~'소리가 나게 한다.
			 * 
			 * (java.awt.Toolkit.getDefaultToolkit().beep()사용)
			 * 
			 * 3. 점수(score)의 값을 계산해서 증가시킨다.
			 * 
			 * (입력한 단어의 글자수 * 남은시간 * 50으로 점수를 계산한다.)
			 * 
			 */
			Iterator<Word> iter = words.iterator();
			while(iter.hasNext()) {
				Word word = iter.next();
				String wordStr = word.toString();
				System.out.println(wordStr);
				if(wordStr.equals(input)) {
					iter.remove();
					System.out.println("삭제.");
					Toolkit tool = Toolkit.getDefaultToolkit();
					tool.beep();
					game.score += wordStr.length() * word.y * 50;
				}
			}
//			for(Object name : words) {
//				if(input.equals(name)) {
//					System.out.println("sad");
//					words.remove(name);
//					Toolkit tool = Toolkit.getDefaultToolkit();
//					tool.beep();
//				}
//				System.out.println("SADK");
//			}

		} // while(true)
	} // main

	public void delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
		}
	}

	class WordGenerator extends Thread {
		public void run() {
			while (true) {
				int rand = (int) (Math.random() * data.length);

				words.add(new Word(data[rand]));
				delay(interval);
			}
		} // run()
	} // class WordGenerator

	class WordDropper extends Thread {
		public void run() {
//			for(Object word : words) {
//				Word w = (Word) word;
//				w.y--;
//				if(w.y <= 0) {
//					words.remove(word);
//					life--;
//					if(life == 0) {
//						System.out.println("LIFE : " + life + "  SCORE : "+score);
//						System.exit(0);
//					}
//				}
//				delay(interval);
//				System.out.println(w.y);
//			}
			
//			Iterator<Word> iter = words.iterator();
//			while(iter.hasNext()) {
//				
//				Word word = iter.next();
//				
//				word.y = word.y - 1;
//				System.out.println("SAD"+word.y);
//				
//				words.add(word);
//				
//				if(word.y <= 0) {
//					iter.remove();
//					life--;
//					if(life == 0) {
//						System.out.println("LIFE : "+life + ", "+" SCORE : "+score);
//					}
//				}
//				delay(1000);
//			}
			
			while(true) {
				Iterator<Word> iter = words.iterator();
				while(iter.hasNext()) {
					Word word = iter.next();
					word.y--;
					if(word.y <= 0) {
						iter.remove();
						life--;
						
						if(life == 0) {
							System.out.println("LIFE : "+life+"\t"+"SCORE : "+score);
							System.exit(0);
						}
					}
				}
				delay(1000);
			}
			/*
			 * 
			 * 다음의 코드를 완성하세요.
			 * 
			 * 
			 * 
			 * 1. words에 저장된 모든 단어(Word인스턴스)의 y값을 1 감소시킨다.
			 * 
			 * 2. y의 값이 0보다 작거나 같으면, words에서 단어를 제거하고 life를 1 감소시킨다.
			 * 
			 * 3. life의 값이 0이 되면 life와 점수를 출력하고 게임을 종료한다.
			 * 
			 * 4. 1초간 시간을 지연시킨다.(delay()사용)
			 * 
			 * 5. 반복문을 이용해서 1~4의 작업을 반복한다.
			 * 
			 */
		} // run()
	} // class WordDropper

	class Word {
		String word = "";
		int y = 10;

		Word(String word) {
			this(word, 10);
		}

		Word(String word, int y) {
			this.word = word;
			this.y = y;
		}

		public String toString() {

			return word+y;
		}
	} // class Word
} // TypingGameEx2

//[실행결과]
//
//LIFE:3 SCORE:0
//[서현10]
//>>서현
//LIFE:3 SCORE:600
//[윤아7, 효연9]
//>>효연
//LIFE:3 SCORE:1300
//[윤아5, 태연9]
//>>태연
//LIFE:3 SCORE:2000
//[윤아3, 써니9]
//>>써ㅗ니
//LIFE:2 SCORE:2000    <---- 시간내에 '윤아'를 입력하지 못해서 LIFE가 하나 줄어듬
//[써니4, 제시카5, 수영7, 효연9] <---- '윤아'는 시간이 지나서 없어짐
//>>
//LIFE:0 SCORE:2000    <---- LIFE가 0이 되어 게임이 종료됨
// ========= Game Over =========
