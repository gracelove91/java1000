package java1000.TypingGameEx4;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

class TypingGameEx4 extends Frame {
	final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 600;

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

	Vector words = new Vector();

	TextField tf = new TextField();
	
	
	Panel pScore = new Panel(new GridLayout(1, 3));
	Label lbLevel = new Label("Level:" + level, Label.CENTER);
	Label lbScore = new Label("Score:" + score, Label.CENTER);
	Label lbLife = new Label("Life:" + life, Label.CENTER);
	MyCanvas screen = new MyCanvas();

	TypingGameEx4() {
		this("Typing game ver1.0");
		
	}

	TypingGameEx4(String title) {
		super(title);
//		screen.setFont(new Font("Serif", Font.BOLD, 100));
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

		setBounds(2000, 700, FRAME_WIDTH, FRAME_HEIGHT);
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
		TypingGameEx4 win = new TypingGameEx4();
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

				// 약 10번에 한번 꼴로 바이러스를 생성한다.
				boolean isVirus = ((int) (Math.random() * 10) + 1) / 10 != 0;

				Word word = new Word(data[rand], isVirus);
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
			Font beforeFont = g.getFont();
			for (int i = 0; i < words.size(); i++) {
				Word tmp = (Word) words.get(i);
				if(tmp.word.equals("윤아") || tmp.word.equals("태연")) {
					g.setFont(new Font("Serif", Font.BOLD, 100));
				}else {
					g.setFont(beforeFont);
				}
				g.setColor(tmp.color);
				g.drawString(tmp.word, tmp.x, tmp.y);
			}
		}
	}

	class VirusThread extends Thread {
		public void run() {
			int rand = (int) (Math.random() * 5);

			int oldValue = 0;
			int virusTime = 10 * 1000; // 10초간 바이러스가 동작한다.
			System.out.println(rand);
			switch (rand) {
			case 0 :
				oldValue = speed;
				speed = (int)(speed / 2);
				delay(virusTime);
				speed = oldValue;
				break;
			case 1 :
				oldValue = interval;
				interval = (int)(interval / 2);
				delay(virusTime);
				interval = oldValue;
				break;
			case 2:
				oldValue = speed;
				speed = speed * 2;
				delay(virusTime);
				speed = oldValue;
				break;
			case 3:
				oldValue = interval;
				interval = interval * 2;
				delay(virusTime);
				interval = oldValue;
				break;
			case 4:
				screen.clear();
				break;
				
				
				
				
			/*
			 * 
			 * 다음의 코드를 완성하세요.
			 * 
			 * 
			 * 
			 * 1. rand의 값이 0이면, virusTime동안 speed의 값을 절반으로 줄인다.
			 * 
			 * 2. rand의 값이 1이면, virusTime동안 interval의 값을 절반으로 줄인다.
			 * 
			 * 3. rand의 값이 2이면, virusTime동안 speed의 값을 두 배로 늘인다.
			 * 
			 * 4. rand의 값이 3이면, virusTime동안 interval의 값을 두 배로 늘인다.
			 * 
			 * 5. rand의 값이 4이면, 화면의 모든 단어를 없앤다.
			 * 
			 */

			} // swtich
		} // end of run()
	}

	class Word {
		String word = "";
		int x = 0;
		int y = 0;
		int step = 5;

		Color color = Color.BLACK;
		boolean isVirus = false;

		Word(String word) {
			this(word, 10, false);
		}

		Word(String word, boolean isVirus) {
			this(word, 10, isVirus);
		}

		Word(String word, int step, boolean isVirus) {
			this.word = word;
			this.step = step;
			this.isVirus = isVirus;

			if (isVirus)
				color = Color.RED;

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
			String input = tf.getText().trim();
			tf.setText("");

			System.out.println(input);

			if (!isPlaying)
				return;

			for (int i = 0; i < words.size(); i++) {
				Word tmp = (Word) words.get(i);

				if (input.equals(tmp.word)) {
					words.remove(i);
					score += input.length() * 50;
					lbScore.setText("Score:" + score);
					Toolkit.getDefaultToolkit().beep();

					
					//바이러스단어란 건 어떻게 알지?
					//Word에 isVirus 불린형있다.
					//
					if(tmp.isVirus) {
						new VirusThread().start();
					}
					/*
					 * 
					 * 다음의 코드를 완성하세요.
					 * 
					 * 
					 * 
					 * 1. 사용자가 입력한 단어가 바이러스면
					 * 
					 * VirusThread를 생성해서 실행시킨다.
					 * 
					 */
					break;
				}
			} // for

			repaint(); // 변화가 있을때 마다 호출해야 바로 바로 적용된다.
		}

		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			e.getWindow().dispose();
			System.exit(0);
		}
	} // class MyEventHandler
}
