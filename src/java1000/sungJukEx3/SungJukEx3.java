package java1000.sungJukEx3;

import java.util.*;

class SungJukEx3 {
	public static void main(String[] args) {
		ArrayList<Student> list = new ArrayList<Student>();

		// 이름, 반, 번호, 국어, 수학, 영어
		list.add(new Student("남궁성", 3, 2, 100, 100, 100));
		list.add(new Student("왕자바", 3, 1, 90, 100, 80));
		list.add(new Student("자바왕", 3, 3, 70, 100, 100));
		list.add(new Student("킹왕짱", 1, 2, 100, 60, 90));
		list.add(new Student("자바짱", 1, 1, 100, 100, 100));
		list.add(new Student("최고수", 1, 3, 100, 80, 60));
		list.add(new Student("홍길동", 2, 1, 50, 80, 100));
		list.add(new Student("일지매", 2, 3, 70, 80, 100));
		list.add(new Student("변강쇠", 2, 4, 80, 80, 85));
		list.add(new Student("이원구", 2, 2, 90, 90, 90));

		System.out.println("[반별 총점높은 순으로 정렬]");
		Collections.sort(list, new ClassTotalComparator()); // 반, 총점 순으로 정렬
		printList(list);

		System.out.println();

		System.out.println("[반, 번호 순으로 정렬]");
		Collections.sort(list, new ClassStudentNoComparator()); // 반, 번호 순으로 정렬
		printList(list);
	}

	public static void printList(List<Student> list) {
		System.out.println("이름\t학년\t반\t국어\t수학\t영어\t총점 ");
		System.out.println("====================================================");

		for (Student s : list) {
			System.out.println(s);
		}

		System.out.println("====================================================");
	}
}

class Student implements Comparable<Student> {
	String name = "";
	int classNo = 0;
	int studentNo = 0;
	int koreanScore = 0;
	int mathScore = 0;
	int englishScore = 0;

	int total = 0;

	Student(String name, int classNo, int studentNo, int koreanScore, int mathScore, int englishScore) {
		this.name = name;
		this.classNo = classNo;
		this.studentNo = studentNo;
		this.koreanScore = koreanScore;
		this.mathScore = mathScore;
		this.englishScore = englishScore;

		total = koreanScore + mathScore + englishScore;
	}

	public String toString() {
		return name + "\t" + classNo + "\t" + studentNo + "\t" + koreanScore + "\t" + mathScore + "\t" + englishScore
				+ "\t" + total + "\t";
	}

	public int compareTo(Student obj) {
		return obj.total - this.total;
//             return this.name.compareTo(obj.name); // 이름기준으로 오름차순 정렬 
	}

	/*
	 * 제네릭스(Generics) 사용하지 않은 버젼 
	 * public int compareTo(Object obj) { 
	 * 	int result = -1;
	 * 
	 * 	if(obj instanceof Student) { 
	 * 
	 * 		Student tmp = (Student)obj;
	 * 
	 * 		result = tmp.total - this.total; }
	 * 
	 * 		return result; 
	 * 	}
	 */
} // end of class Student

//"[반별 총점높은 순으로 정렬]"
class ClassTotalComparator implements Comparator<Student> {

	public int compare(Student s1, Student s2) {
		//먼저 반별로 정렬한 후 총점 정렬한다.
//		int result = s1.classNo - s2.classNo;
		int ban = s1.classNo;
		
		if(ban == s2.classNo) 
			return s2.total - s1.total;
		
		return s1.classNo - s2.classNo;
	}
}

//"[반, 번호 순으로 정렬]"
class ClassStudentNoComparator implements Comparator<Student> {
	public int compare(Student s1, Student s2) {

		/*
		 * 
		 * 코드를 완성하세요.
		 * 
		 */
		
		//먼저 반으로 줄세운 후 번호로 줄세운다.
		int result = s1.classNo - s2.classNo;
		if(result == 0) {
			return s1.studentNo - s2.studentNo;
		}

		return result;
	}
}

//
//[실행결과]
//
//[반별 총점높은 순으로 정렬]
//이름    반     번호    국어    수학    영어    총점
//
//====================================================
//자바짱  1       1       100     100     100     300   <----- 1반 1등
//킹왕짱  1       2       100     60      90      250
//최고수  1       3       100     80      60      240
//이원구  2       2       90      90      90      270    <------ 2반 1등
//일지매  2       3       70      80      100     250
//변강쇠  2       4       80      80      85      245
//홍길동  2       1       50      80      100     230
//남궁성  3       2       100     100     100     300  <------- 3반 1등
//왕자바  3       1       90      100     80      270
//자바왕  3       3       70      100     100     270
//====================================================
//
// 
//
//[반, 번호 순으로 정렬]
//이름    반     번호    국어    수학    영어    총점
//====================================================
//자바짱  1       1       100     100     100     300
//킹왕짱  1       2       100     60      90      250
//최고수  1       3       100     80      60      240
//홍길동  2       1       50      80      100     230
//이원구  2       2       90      90      90      270
//일지매  2       3       70      80      100     250
//변강쇠  2       4       80      80      85      245
//왕자바  3       1       90      100     80      270
//남궁성  3       2       100     100     100     300
//자바왕  3       3       70      100     100     270
//====================================================
