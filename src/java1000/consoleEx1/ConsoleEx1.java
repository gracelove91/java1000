package java1000.consoleEx1;
import java.util.*; 

class ConsoleEx1 { 
      public static void main(String[] args) { 
    	  	Scanner scan = new Scanner(System.in);
            while(true) { 
                  String prompt = ">>"; 
                  System.out.print(prompt); 
                  String input = scan.nextLine();
                  
                  if(input.equalsIgnoreCase("q")) break;

                  /*

                     다음과 같은 내용의 코드를 넣으세요.                  

                     1. 화면으로부터 라인단위로 입력받는다.  - java.util.Scanner클래스 사용
                     2. q 또는 Q를 입력하면 실행종료한다.

                  */


             } // while(true) 
            scan.close();
      } // main 
} // class

 

 

//[실행결과]
//
//>>hello
//hello
//>>help
//help
//>>ok
//ok
//>>q

