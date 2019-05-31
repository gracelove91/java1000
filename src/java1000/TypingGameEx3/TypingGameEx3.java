package java1000.TypingGameEx3;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

class TypingGameEx3 extends Frame {
	final int FRAME_WIDTH = 400;
	final int FRAME_HEIGHT = 300;

	final int SCREEN_WIDTH;
	final int SCREEN_HEIGHT;

	int speed = 500; // 단어가 떨어지는 속도... 높을 수록 느리다.
	int interval = 2 * 1000; // 새로운 단어가 나오는 간격

	int level = 0;
	int score = 0;
	int life = 3;

	boolean isPlaying = false;

	WordGenerator wg = null; // 단어를 생성하는 쓰레드
	WordDropper wm = null; // 단어를 떨어뜨리는 쓰레드

	FontMetrics fm; // 화면에서의 글자 길이를 구하는데 사용

	String[] data = { "태연", "유리", "윤아", "효연", "수영", "서현", "티파니", "써니", "제시카" };

	Vector<Word> words = new Vector<>();

	TextField tf = new TextField();
	Panel pScore = new Panel(new GridLayout(1, 3));
	Label lbLevel = new Label("Level:" + level, Label.CENTER);
	Label lbScore = new Label("Score:" + score, Label.CENTER);
	Label lbLife = new Label("Life:" + life, Label.CENTER);
	MyCanvas screen = new MyCanvas();

	TypingGameEx3() {
		this("Typing game ver1.0");
	}

	TypingGameEx3(String title) {
		super(title);

		pScore.setBackground(Color.YELLOW);
		pScore.add(lbLevel);
		pScore.add(lbScore);
		pScore.add(lbLife);
		add(pScore, "North");
		add(screen, "Center");
		add(tf, "South");

		MyEventHandler handler = new MyEventHandler();
		addWindowListener(handler);
		tf.addActionListener(handler);

		setBounds(500, 200, FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		setVisible(true);

		SCREEN_WIDTH = screen.getWidth();
		SCREEN_HEIGHT = screen.getHeight();
		fm = getFontMetrics(getFont());
	}

	public void repaint() {
		super.repaint();
		screen.repaint();
	}

	public void delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
		}
	}

	public void start() {
		isPlaying = true;

		wg = new WordGenerator();
		wg.start();

		wm = new WordDropper();
		wm.start();
	}

	public static void main(String[] args) {
		TypingGameEx3 win = new TypingGameEx3();
//             win.show(); 
		win.start();
	}

	class WordDropper extends Thread {
		public void run() {
			outer: while (isPlaying) {
				delay(speed);
				for (int i = 0; i < words.size(); i++) {
					Word tmp = (Word) words.get(i);

					tmp.y += tmp.step;

					if (tmp.y >= SCREEN_HEIGHT) {
						tmp.y = SCREEN_HEIGHT;
						words.remove(tmp);
						life--;
						lbLife.setText("Life:" + life);
						break;
					}

					if (life <= 0) {
						isPlaying = false;
						break outer;
					}
				} // for

				repaint();
			}
		} // end of run()
	}

	class WordGenerator extends Thread {
		public void run() {
			while (isPlaying) {
				int rand = (int) (Math.random() * data.length);

				Word word = new Word(data[rand]);
				words.add(word);
				delay(interval);
			}
		} // end of run()
	}

	class MyCanvas extends Canvas {
		public void clear() {
			Graphics g = getGraphics();
			g.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		}

		public void paint(Graphics g) {
			clear();

			for (int i = 0; i < words.size(); i++) {
				Word tmp = (Word) words.get(i);
				g.drawString(tmp.word, tmp.x, tmp.y);
			}
		}
	}

	class Word {
		String word = "";
		int x = 0;
		int y = 0;
		int step = 5;

		Word(String word) {
			this(word, 10);
		}

		Word(String word, int step) {
			this.word = word;
			this.step = step;

			int strWidth = fm.stringWidth(word);

			x = (int) (Math.random() * SCREEN_WIDTH);

			if (x + strWidth >= SCREEN_WIDTH)
				x = SCREEN_WIDTH - strWidth;
		}

		public String toString() {
			return word;
		}
	} // end of class Word

	class MyEventHandler extends WindowAdapter implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			/** TypingGrameEx3	의 인스턴스 변수들!.
			 * 	TextField tf = new TextField();
	Panel pScore = new Panel(new GridLayout(1, 3));
	Label lbLevel = new Label("Level:" + level, Label.CENTER);
	Label lbScore = new Label("Score:" + score, Label.CENTER);
	Label lbLife = new Label("Life:" + life, Label.CENTER);
	MyCanvas screen = new MyCanvas();
			 */
			
			//TextField의 값을 읽어 저장한다.
			String tfStr = tf.getText();
			tf.setText("");
			 
			while(isPlaying) {
				Iterator<Word> iter = words.iterator(); 
				// 
				while(iter.hasNext()) {
					Word word = iter.next();
					//뽑아와서 뭐할거야? 텍스트필드에 있는 스트링이랑 비교해야지.
					//비교해와서 있으면? score 처리해줘야지. score계산식? 단어의 문자개수 * 50 그 후 저장돼있는 iter에서 리무브햊 ㅓㅜ야지.
					if(tfStr.contains(word.toString().trim())) {
						score += word.toString().length() * 50;
						iter.remove();
						Toolkit.getDefaultToolkit().beep();
						lbScore.setText(score+"");
						//일치해도 리페인트? 불일치해도 리페인트? 리페인트가 대체뭔데? 화면 다시뿌려주는 거 아닌가?
						//근데 화면은 계속 바뀌어야하자너!
//						repaint();
					}
				}
			}
			/*
			 * 
			 * 다음의 코드를 완성하세요.
			 * 
			 * 
			 * 
			 * 1. TextField의 값을 읽어서 저장한다.(getText()사용)
			 * 
			 * 2. TextField를 비운다.
			 * 
			 * 3. 게임중(isPlaying)이 아니면, 빠져나간다.
			 * 
			 * 4. 반복문을 이용해서 사용자가 TextField에 입력한 단어를 words에서 찾는다.
			 * 
			 * 4.1 찾으면, words에서 제거한다.
			 * 
			 * 4.2 단어의 길이에 따라 score를 증가시킨다.(단어의 문자개수 * 50)
			 * 
			 * 4.3 '삑~'소리가 나게 한다.(Toolkit.getDefaultToolkit().beep()사용)
			 * 
			 * 5. 화면을 다시 그리게 한다.(repaint()사용)
			 * 
			 */
		}

		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			e.getWindow().dispose();
			System.exit(0);
		}
	} // class MyEventHandler
}
