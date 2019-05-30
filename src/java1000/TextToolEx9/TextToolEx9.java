package java1000.TextToolEx9;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class TextToolEx9 extends Frame implements WindowListener {
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
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());

				prevText = curText;

				Scanner s = new Scanner(curText);
				HashSet set = new HashSet();

				for (int i = 0; s.hasNextLine(); i++) {
					String line = s.nextLine();
					set.add(line);
				}

				ArrayList list = new ArrayList(set);
				Collections.sort(list);

				int size = list.size();

				for (int i = 0; i < size; i++) {
					sb.append(list.get(i));
					sb.append(CR_LF);
				}

				ta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener() { // distinct2 - 중복 라인 제거 + 카운트
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());

				prevText = curText;
				Scanner scan = new Scanner(curText);
				Map<String, Integer> map = new TreeMap<>();
				
				while(scan.hasNextLine()) {
					String line = scan.nextLine().trim();
					
					//중복횟수는 어떻게 셀까?
					//key에는 line저장! key는 중복이 불가하다!.
					
					if(map.containsKey(line)) {
						//key는 중복이 불가하니까, map에 key에 line이 있다면 똑같은 line이 map에 key로 저장되어있다는 뜻! 
						//그러니까 중복을 카운트하는 map의 value에 +1 해줍시다요 예!?!?!?!? 아시겠어여!?
						//근데! 공백이 포함되면? 중복아니라고 또 저장할 수도 있으니! trim()으로 미리 초벌!!
						int count = map.get(line);
//						map.replace(line, count, count+1);
						map.replace(line, ++count);
					}else {
						//자꾸 아무것도 없는 값에 count된다 빡친다.
						//if문으로 걸러보자.
						if(line.equals("")) continue;
						map.put(line, 1);
					}
				}
				
				String delim = ",";
				if(!(tfParam1.getText().isEmpty())) {
					delim = tfParam1.getText();
				}
				
				Set<Entry<String, Integer>> entrySet = map.entrySet();
				
				Iterator<Entry<String, Integer>> iter = entrySet.iterator();
				
				while(iter.hasNext()) {
					Entry<String, Integer> e = iter.next();
					String line = e.getKey();
					int count = e.getValue();
					
					sb.append(line).append(delim).append(count).append(CR_LF);
				}
				ta.setText(sb.toString());

				/*
				 * 다음의 코드를 완성하세요. 
				 * 1. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽어서 TreeMap에 담는다.
				 * 
				 * 1.1 TreeMap에 담을 때, 각 라인을 키로 저장하고 값으로는 중복회수를 저장한다.
				 * 
				 * 1.2 TreeMap에 담을 때, 이미 같은 내용의 값이 저장되어 있는지 확인하고
				 * 
				 * 1.1.1 이미 같은 내용이 저장되어 있으면, 해당 키의 값을 읽어서 1증가시키고
				 * 
				 * 1.1.2 새로운 키값이면 1을 값으로 저장한다.
				 * 
				 * 2. param1에 지정된 문자열이 있으면, 그 문자열을 키와 값의 구분자로 사용하고
				 * 
				 * 없으면, ','를 구분자로 지정한다.
				 * 
				 * 3. Iterator를 이용해서 TreeMap에 저장된 키와 값을 구분자와 함께 sb에 저장한다.
				 * 
				 * (TreeMap을 사용했기 때문에, 자동적으로 키값을 기준으로 오름차순 정렬된다.)
				 * 
				 * 4. sb에 저장된 내용을 TextArea에 보여준다.
				 * 
				 */

			}
		});
	} // end of registerEventHandler()

	public static void main(String[] args) {
		TextToolEx9 win = new TextToolEx9("Text Tool");
		win.show();
	}

	public TextToolEx9(String title) { 
            super(title); 
            lb1 = new Label("param1:", Label.RIGHT); 
            lb2 = new Label("param2:", Label.RIGHT); 
            tfParam1 = new TextField(15); 
            tfParam2 = new TextField(15); 

            ta = new TextArea(); 
            pNorth = new Panel(); 
            pSouth = new Panel(); 

            for(int i=0;i < btn.length;i++) {

                   btn[i] = new Button(btnName[i]); 
            } 

            pNorth.setLayout(new FlowLayout()); 
            pNorth.add(lb1); 
            pNorth.add(tfParam1); 
            pNorth.add(lb2); 
            pNorth.add(tfParam2); 

            pSouth.setLayout(new GridLayout(2,10)); 

            for(int i=0;i < btn.length;i++) {  //<BTN.LENGTH;I++) style="COLOR: #008200" { // 버튼배열을 하단 Panel에 넣는다. 
                  pSouth.add(btn[i]); 
            } 

            add(pNorth,"North"); 
            add(ta,"Center"); 
            add(pSouth,"South"); 

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
 * 테스트 데이터 ccc aaa aaa bbb bbb bbb ccc
 */
