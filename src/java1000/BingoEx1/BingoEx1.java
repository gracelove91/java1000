package java1000.BingoEx1;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 

class BingoEx1 extends Frame { 

      final int SIZE = 5;  // 빙고판의 크기 
      int bingoCnt = 0;   // 완성된 라인의 수 

      Button[] btnArr = null; 
      boolean[][] bArr = new boolean[SIZE][SIZE]; // 빙고판 체크여부 확인을 위한 배열 

      BingoEx1() { 
            this("Bingo Game Ver1.0"); 
      } 

      BingoEx1(String title) { 
            super(title); 

            setLayout(new GridLayout(SIZE, SIZE)); 

            MyEventHandler handler = new MyEventHandler(); 
            addWindowListener(handler); 

            btnArr = new Button[SIZE*SIZE]; 

            // Frame에 버튼을 추가한다. 
            for(int i=0; i < SIZE*SIZE;i++) { 
                  btnArr[i] = new Button(i+1+""); 
                  add(btnArr[i]); 
                  btnArr[i].addActionListener(handler); 
            } 

            setBounds(500, 200, 300, 300); 
            setVisible(true); 
      } 

      void print() { // 배열 bArr을 출력한다.

           /*

               코드를 완성하세요.

                배열 bArr의 내용을 실행결과와 같은 형식으로 출력하세요.

           */
      } 

      boolean checkBingo() { // 빙고가 완성되었는지를 확인한다. 
            bingoCnt = 0; 
            int garoCnt = 0;     // 가로줄의 O의 개수

            int seroCnt = 0;     //  세로줄의 O의 개수
            int crossCnt1 = 0;  //  대각선의 O의 개수
            int crossCnt2 = 0;  // 역대각선의 O의 개수

            /*

                다음의 코드를 완성하세요.

 

               1. 이중 반복문을 이용해서 배열 bArr의 값을 체크한다.

               2. 완성된 라인의 수를 세어서 SIZE의 개수보다 크거나 같으면 true를

                  그렇지 않으면 false를 반환한다.

            */

      } 

      public static void main(String args[]) { 
            BingoEx1 win = new BingoEx1("Bingo Game Ver1.0"); 
            win.show(); 
      } 

      class MyEventHandler extends WindowAdapter implements ActionListener { 
            public void actionPerformed(ActionEvent ae) { 
                  Button btn = (Button)ae.getSource();

 

                 /*

                     다음의 코드를 완성하세요.

 

                     1. 반복문을 통해, 버튼 배열 btnArr에서 btn(눌러진 버튼)과 같은 것을 찾는다.

                         1.1 찾으면, btnArr에서 btn을 찾은 위치(index)에 대응하는 bArr의 값을 확인한다.

                             (btnArr은 일차원 배열이고, bArr은 2차원 배열이므로 이 두 배열간의 관계를 잘고려해야한다.)

                             1.1.1 bArr의 값이 true이면 "이미 누른 버튼입니다."라고 콘솔에 출력한다.

                             1.1.2 bArr의 값이 false이면, true로 바꿔서 저장한다.

                         1.2 btnArr에서 btn을 찾아서 작업을 마쳤으므로 반복문을 빠져나간다.

                      2. 1에서 찾은 버튼의 배경색을 노란색으로 바꾼다.(setBackground()사용)

                      3. 배열 bArr을 콘솔에 출력한다.(BingoEx1클래스의 print()사용)

                      4. checkBingo()를 호출해서 빙고가 완성되었는지 확인하고, 완성되었으면

                          콘솔에 "Bingo~!!!"라고 출력한다.

                 */

            } 

            public void windowClosing(WindowEvent e) { 
                  e.getWindow().setVisible(false); 
                  e.getWindow().dispose(); 
                  System.exit(0); 
            } 
      } 
}
