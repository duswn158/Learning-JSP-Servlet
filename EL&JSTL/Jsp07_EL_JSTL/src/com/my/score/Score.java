package com.my.score;

public class Score {
	
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private double avg;
	private String grade;
	
	
	public Score() {
		
	}
	
	public Score(String name, int kor, int eng, int math) {
		
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		
		// setter에서 연산처리
		setSum();
		setAvg(sum);
		setGrade(avg);
		
	}
	
	
	//getter setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}

	public int setSum() {
		return this.sum = kor + eng + math;
	}

	public double getAvg() {
		return avg;
	}

	public double setAvg(int sum) {
		return this.avg = sum/3.0;
	}

	public String getGrade() {
		return grade;
	}

	public String setGrade(double avg) {
		
		String res = null; 
		
		
		switch((int)avg/10) { 
		
		case 10:
		case 9:
			res = "A";
			break;
		case 8:
			res = "B";
			break;
		case 7:
			res = "C";
			break;
		case 6: 
			res = "D";
			break;
		default:
			res = "F";
			break;	
		}
		
		return this.grade = res;
	}

	/*
	  
	// System.out.println(); 하면 아래의 리턴값을 출력
	@Override
	public String toString() {
		return "이름:" + name + "\t국어:" + kor + "\t영어:" + eng + "\t    수학:" + math 
				+ "\n\t\t총점:" + getSum() + "\t평균:" + String.format("%.2f", getAvg()) + "  등급:" + getGrade();
	}
		
	*/
	
	/*
	 * Score ruy = new Score();
		kim.setName("유연주");
		kim.setKor(85);
		kim.setEng(90);
		kim.setMath(25);
		System.out.println(ryu);
		
		System.out.println();
		
		Score hong = new Score("홍길동", 50, 35, 100);
		System.out.println(hong);
		
		두가지 방법 모두 정상 출력
	 */

}
