package java1000.TextToolEx11;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*; // Pattern, Matcher클래스를 사용하기 위해 추가
import java.text.*;

public class TextToolEx11 extends Frame implements WindowListener {
	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;

	String[] btnName = { "Undo", // 작업이전 상태로 되돌림
			"짝수줄삭제", // 짝수줄을 삭제하는 기능
			"문자삭제", // Param1에 지정된 문자들을 삭제하는 기능
			"trim", // 라인의 앞뒤 공백을 제거
			"빈줄삭제", // 빈 줄 삭제
			"접두사추가", // Param1과 Param2의 문자열을 각 라인의 앞뒤에 붙이는 기능
			"substring", // Param1과 Param2에 지정된 문자열을 각 라인에서 제거하는 기능
			"substring2", // Param1과 Param2에 지정된 문자열로 둘러싸인 부분을 남기고 제거하는 기능
			"distinct", // 중복값제거한 후 정렬해서 보여주기
			"distinct2", // 중복값제거한 후 정렬해서 보여주기 - 중복카운트 포함
			"패턴적용", // 데이터에 지정된 패턴 적용하기
			"패턴제거", // 데이터에 적용된 패턴 제거하기
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
				/* 내용 생략 */

			}
		});

		btn[n++].addActionListener(new ActionListener() { // trim - 라인의 좌우공백을 제거하는 기능
			public void actionPerformed(ActionEvent ae) {
				/* 내용 생략 */

			}
		});

		btn[n++].addActionListener(new ActionListener() { // 빈줄삭제 - 빈 줄 삭제
			public void actionPerformed(ActionEvent ae) {
				/* 내용 생략 */

			}
		});

		btn[n++].addActionListener(new ActionListener() { // 접두사 - 각 라인에 접두사, 접미사 붙이기
			public void actionPerformed(ActionEvent ae) {
				/* 내용 생략 */

			}
		});

		btn[n++].addActionListener(new ActionListener() { // substring - 문자열 자르기
			public void actionPerformed(ActionEvent ae) {
				/* 내용 생략 */

			}
		});

		btn[n++].addActionListener(new ActionListener() { // substring2 - 지정된 문자를 찾아서 그 위치까지 잘라내기
			public void actionPerformed(ActionEvent ae) {
				/* 내용 생략 */

			}
		});

		btn[n++].addActionListener(new ActionListener() { // distinct - 중복 라인 제거
			public void actionPerformed(ActionEvent ae) {
				/* 내용 생략 */

			}
		});

		btn[n++].addActionListener(new ActionListener() { // distinct2 - 중복 라인 제거 + 카운트
			public void actionPerformed(ActionEvent ae) {
				/* 내용 생략 */

			}
		});

		btn[n++].addActionListener(new ActionListener() { // 패턴적용
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());

				prevText = curText;

				String pattern = tfParam1.getText();
				String delimiter = tfParam2.getText();

				if (delimiter.length() == 0)
					delimiter = ",";

				Scanner s = new Scanner(curText);

				for (int i = 0; s.hasNextLine(); i++) {
					String line = s.nextLine();

					String[] tokens = line.split(delimiter);

					sb.append(MessageFormat.format(pattern, tokens));
					sb.append(CR_LF);
				}

				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() { // 패턴제거
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());

				prevText = curText;

				String pattern = tfParam1.getText();
				String delimiter = tfParam2.getText();

				Pattern p = Pattern.compile(pattern);

				if (delimiter.length() == 0)
					delimiter = ",";
				
				System.out.println(delimiter);
				System.out.println(p.toString());
				Scanner scan = new Scanner(curText);

				while (scan.hasNextLine()) {
					String line = scan.nextLine();

					Matcher m = p.matcher(line);
					if(m.find()) {
						for(int i = 1; i <= m.groupCount(); i++) {
							sb.append(m.group(i)).append(delimiter);
						}
						
					}else {
						sb.append(line);
					}
					sb.append(CR_LF);
				}
				ta.setText(sb.toString());
				
				//(0\d{1,2})-(\d{3,4})-(\d{4})
				
				/*
				 * 
HP:011-1111-1111 
HP:02-222-2222 
HP:333-444-4444 


				 */

				/*
				 * 
				 * 다음의 코드를 완성하세요.
				 * 
				 * 
				 * 
				 * 1. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				 * 
				 * 2. 각 라인을 pattern에 맞게 매칭시킨다.(Pattern클래스의 matcher()사용)
				 * 
				 * 3. pattern에 매칭되는 데이터를 구분자와 함께 sb에 저장한다.
				 * 
				 * 4. sb의 내용을 TextArea에 보여준다.
				 * 
				 */

			}
		});

	} // end of registerEventHandler()

	public static void main(String[] args) {
		TextToolEx11 win = new TextToolEx11("Text Tool");
		win.show();
	}

	public TextToolEx11(String title) {
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
 * 테스트 데이터 (0\d{1,2})-(\d{3,4})-(\d{4})
 * 
 * HP:011-1111-1111 HP:02-222-2222 HP:333-444-4444
 */

// 
//
//[실행결과]
//
//1. 예제를 실행하고 TextArea에 테스트 데이터를 입력한다. param1과 param2에도 아래와 같이 입력한다.
//
//  param1에는 (0\d{1,2})-(\d{3,4})-(\d{4}) 를 입력한다.
//
//[참고]
//
//0\d{1,2} - 패턴의 의미는 0으로 시작하는 한자리 또는 두자리의 숫자
//
//\d{3,4}  - 3자리 또는 4자리의 숫자
//
//\d{4}    -  4자리의 숫자
//[출처] [Java1000제] Text데이터 편집도우미11 - 패턴제거 (남궁성의 코드초보스터디(자바 java, c언어, javascript, python) |작성자 남궁성