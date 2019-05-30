package java1000.TextToolEx2;

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

public class TextToolEx2 extends Frame implements WindowListener {
	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;

	String[] btnName = { "Undo", // 작업이전 상태로 되돌림
			"짝수줄삭제", // 짝수줄을 삭제하는 기능
	};

	Button[] btn = new Button[btnName.length];

	private final String CR_LF = System.getProperty("line.separator");

	private String prevText = ""; // TextArea ta의 내용을 저장하기 위한 변수

	private void registerEventHandler() {
		addWindowListener(this);

		btn[0].addActionListener(new ActionListener() { // Undo - 작업이전 상태로 되돌림
			public void actionPerformed(ActionEvent ae) {
				String tmp = ta.getText();
				ta.setText(prevText);
				prevText = tmp;
				/*
				 * 
				 * 다음의 코드를 완성하세요.
				 * 
				 */
			}
		});

		btn[1].addActionListener(new ActionListener() { // 짝수줄삭제 - 짝수줄을 삭제하는 기능
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
	} // end of registerEventHandler()

	public static void main(String[] args) {
		TextToolEx2 win = new TextToolEx2("Text Tool");
		win.show();
	}

	public TextToolEx2(String title) {
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
 * 테스트 데이터 111111111111111111 222222222222222222 333333333333333333
 * 444444444444444444 555555555555555555 666666666666666666 777777777777777777
 * 888888888888888888 999999999999999999 000000000000000000
 */
