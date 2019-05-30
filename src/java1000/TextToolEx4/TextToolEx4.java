package java1000.TextToolEx4;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Scanner;

public class TextToolEx4 extends Frame implements WindowListener {
	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;

	String[] btnName = { "Undo", // 작업이전 상태로 되돌림
			"짝수줄삭제", // 짝수줄을 삭제하는 기능
			"문자삭제", // Param1에 지정된 문자들을 삭제하는 기능
			"trim", // 라인의 앞뒤 공백을 제거
			"빈줄삭제", // 빈 줄 삭제
	};

	Button[] btn = new Button[btnName.length];

	private final String CR_LF = System.getProperty("line.separator"); // 줄바꿈문자

	private String prevText = "";

	private void registerEventHandler() {
		addWindowListener(this);

		int n = 0; // 버튼순서

		btn[n++].addActionListener(new ActionListener() { // Undo - 작업이전 상태로 되돌림
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();

				if (!prevText.equals("")) {
					ta.setText(prevText);
				}

				prevText = curText;
			}
		});

		btn[n++].addActionListener(new ActionListener() { // 짝수줄삭제 - 짝수줄을 삭제하는 기능
			public void actionPerformed(ActionEvent ae) {
				/* 내용 생략 */

			}
		});

		btn[n++].addActionListener(new ActionListener() { // 문자삭제 - Param1에 지정된 문자를 삭제하는 기능
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());

				prevText = curText;

				String delText = tfParam1.getText();

				if ("".equals(delText))
					return;

				for (int i = 0; i < curText.length(); i++) {
					char ch = curText.charAt(i);

					if (delText.indexOf(ch) == -1)
						sb.append(ch);
				}

				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() { // trim - 라인의 좌우공백을 제거하는 기능
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());

				prevText = curText;

				Scanner scan = new Scanner(curText).useDelimiter(CR_LF);

				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					line = line.trim();
					sb.append(line).append(CR_LF);
				}

				ta.setText(sb.toString());

				/*
				 * 
				 * 다음의 코드를 완성하세요.
				 * 
				 * 1. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				 * 
				 * (Scanner클래스의 hasNextLine(), nextLine()사용)
				 * 
				 * 2. 읽어온 라인의 왼쪼공백과 오른쪽 공백을 제거한다.(String클래스의 trim()사용)
				 * 
				 * 3. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText()사용)
				 * 
				 */
			}
		});

		btn[n++].addActionListener(new ActionListener() { // 빈줄삭제 - 빈 줄 삭제
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());

				prevText = curText;

				Scanner scan = new Scanner(curText).useDelimiter(CR_LF);

				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					
					//빈라인인 거 어케알쥐?
					//개행 CR_LF
//					System.out.println(line);
					if(line.trim().equals("")) {
//						String lineTrim = line.trim();
						//일단 찾음.. 이걸 curText에서 삭제하고 sb에 저장해야함.
					}else {
						sb.append(line).append(CR_LF);
					}
				}

				ta.setText(sb.toString());

				/*
				 * 
				 * 다음의 코드를 완성하세요.
				 * 
				 * 1. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				 * 
				 * (Scanner클래스의 hasNextLine(), nextLine()사용)
				 * 
				 * 2. 읽어온 라인이 내용이 없는 빈 라인이면 sb에 저장하지 않는다.
				 * 
				 * 3. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText()사용)
				 * 
				 */

			}
		});
	} // end of registerEventHandler()

	public static void main(String[] args) {
		TextToolEx4 win = new TextToolEx4("Text Tool");
		win.show();
	}

	public TextToolEx4(String title) {
		super(title);
		lb1 = new Label("param1:", Label.RIGHT);
		lb2 = new Label("param2:", Label.RIGHT);
		tfParam1 = new TextField(15);
		tfParam2 = new TextField(15);

		ta = new TextArea();
		pNorth = new Panel();
		pSouth = new Panel();

		for (int i = 0; i < btn.length; i++) {

			btn[i] = new Button(btnName[i]);
		}

		pNorth.setLayout(new FlowLayout());
		pNorth.add(lb1);
		pNorth.add(tfParam1);
		pNorth.add(lb2);
		pNorth.add(tfParam2);

		pSouth.setLayout(new GridLayout(2, 10));

		for (int i = 0; i < btn.length; i++) { // 버튼배열을 하단 Panel에 넣는다.
			pSouth.add(btn[i]);
		}

		add(pNorth, "North");
		add(ta, "Center");
		add(pSouth, "South");

		setBounds(100, 100, 600, 300);
		ta.requestFocus();
		registerEventHandler();
		setVisible(true);
	} // public TextToolEx1(String title) {

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}
} // end of class

/*
 * 테스트 데이터
 * 
 * 1111111111111 22222 22222222
 * 
 * 3333333333333333
 * 
 * 4444444444444444 5555555555555555555555
 * 
 */
