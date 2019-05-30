package java1000.TextToolEx3;

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

public class TextToolEx3 extends Frame implements WindowListener {
	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;

	String[] btnName = { "Undo", // 작업이전 상태로 되돌림
			"짝수줄삭제", // 짝수줄을 삭제하는 기능
			"문자삭제", // param1에 지정된 문자들을 삭제하는 기능
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
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());

				prevText = curText;

				Scanner s = new Scanner(curText);

				for (int i = 0; s.hasNextLine(); i++) {
					String line = s.nextLine();

					if (i % 2 == 0) {
						sb.append(line).append(CR_LF);
					}
				}

				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() { // 문자삭제 - Param1에 지정된 문자를 삭제하는 기능
			public void actionPerformed(ActionEvent ae) {

				String curText = ta.getText(); // 텍스트에어리어에 있는 글.
				StringBuffer sb = new StringBuffer(curText.length());
				sb.append(curText);

				prevText = curText;
				String tfStr1 = tfParam1.getText();
				
				//curText를 한글자씩?
//				char[] c = curText.toCharArray();
				for(int i = 0; i < tfStr1.length(); i++) {
					for(int j = 0; j < curText.length(); j++) {
						//curText[j]가 tfStr1[i] 가 동일한지 확인.
						if(curText.charAt(j) == tfStr1.charAt(i)) {
							
							sb.deleteCharAt(sb.indexOf(String.valueOf(curText.charAt(j))));
						}
					}
				}
				
				
				/*
				 * 
				 * 다음의 코드를 완성하세요.
				 * 1. TextField Param1의 값을 가져온다.(getText()사용)
				 * 
				 * 2. 반복문을 이용해서 curText를 한글자씩 읽어서
				 * 
				 * Param1에서 가져온 문자열에 포함되어 있는지 확인한다.
				 * 
				 * 2.1 만일 포함되어 있으면 sb에 저장하고
				 * 
				 * 2.2 포함되어 있지 않으면 sb에 저장하지 않는다.
				 * 
				 * 3. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText()사용)
				 * 
				 */
//						for(int i = 0; i < curText.length(); i++) {
//						for(int j = 0; j < tfStr1.length(); j++) {
//							if(curText.indexOf(tfStr1.charAt(j)) >= 0) {
//								
//							}else {
//								sb.append(tfStr1.charAt(j));
//							}
//						}
//					}
//					
					ta.setText(sb.toString());

			}
		});
	} // end of registerEventHandler()

	public static void main(String[] args) {
		TextToolEx3 win = new TextToolEx3("Text Tool");
		win.show();
	}

	public TextToolEx3(String title) {
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
 * a1b2c3d4f5g6 z0s9go1o2o777 asdg8ia0s9basldkfj l2k62l3kh5l2kjkjdsf0
 * 
 */
