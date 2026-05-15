/*
문제4) 2차원 배열 scores에 학생 수를 입력하고 학생수에 따라 과목수를 입력하여, 각 학생의 과목에 대하여 점수를 입력한 후
각 학생에 대한 총점, 평균, 학점, 석차를 출력하시오.
- 평균은 소수점 둘째 자리까지 반올림하여 출력하시오.
- 학점은 평균이 90점이상이면 A, 80점 이상이면 B, 70점 이상이면 C, 60점 이상이면 D, 60점 미만이면 F로 출력하시오.
- 석차는 총점을 기준으로 정하시오.
- 마지막 줄에는 각 과목에 대한 평균이 출력되도록 하시오.
 */
import java.util.Scanner;
public class Array2D04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("학생 수를 입력하시오: ");
		int stu = sc.nextInt();
		
		int[][] scores = new int [stu][];
		
		// 2차원 배열 -> stu명의 학생에 대한 성적, stu행 subject열
		System.out.println("각 학생의 과목 수를 입력하시오.");
		for(int i=0; i<stu; i++) {
			System.out.printf("%d 번째 학생의 과목 수: ", i + 1);
			int sub = sc.nextInt();
			scores[i] = new int[sub];
		}
		
		// 각 과목에대한 점수 입력받기
		for(int i=0; i<scores.length; i++) {    // 학생의 명수만큼 반복
			for(int j=0; j<scores[i].length; j++) {		// 각 학생의 과목개수만큼 반복
				switch(j) {			
				case 0:
					System.out.printf("%d번 학생의 국어점수 입력: ", i + 1);
					break;			
				case 1:
					System.out.printf("%d번 학생의 영어점수 입력: ", i + 1);
					break;
				case 2:
					System.out.printf("%d번 학생의 수학점수 입력: ", i + 1);
					break;
				}
				scores[i][j] = sc.nextInt();
			}
		}

		// 1차원 배열 -> 각 학생의 평균, 실수형 배열
		double[] aves = new double[5];
		
		// 1차원 배열 -> 학생의 학점, 문자형 배열
		char[] grds = new char[5];
		
		// 1차원 배열 -> 학생의 석차
		// 석차는 값을 1로 초기화를 하고, 자신보다 큰 값이 있을때 자신의 값에 1을 더한다
		int[] ranks = new int[5];
		for(int i=0; i<ranks.length;i++) {
			ranks[i] = 1; //배열의 모든 값을 1로 초기화
		}
		
		// 1차원 배열 -> 국어, 영어, 수학, 총점, 평균의 전체평균
		double[] t_aves = new double[5];
		
		// 1차원 배열 -> 3개의 과목
		String[] subjects = new String[] {"KOR", "ENG", "MAT", "TOT", "AVE", "GRD", "RANK"};
		System.out.printf(" %3s |", "NO");
		for(int i=0; i<subjects.length; i++) {
			System.out.printf("%6s | ", subjects[i]);
		}
		System.out.println();
		
		// 라인 출력
		System.out.print("-----");
		for(int i=0; i<subjects.length; i++) {
			System.out.print("---------");
		}
		System.out.println();
		
		// 총점, 평균, 학점 계산
		for(int i=0; i<scores.length; i++) {
			for(int j=0; j<scores[i].length; j++) {
				//총점 저장
				if(j < scores[i].length) {
					scores[i][scores[i].length-1] += scores[i][j];
				}
				// 평균 저장
				aves[i] = (double)scores[i][scores[i].length-1] / 3;
				// 학점 저장
				switch((int)(aves[i]/10)) {
				case 10:
				case 9: grds[i] = 'A'; break;
				case 8: grds[i] = 'B'; break;
				case 7: grds[i] = 'C'; break;
				case 6: grds[i] = 'D'; break;
				default: grds[i] = 'F';
				}
			}
		}
		// 석차 계산 - i: 자신, j: 다른 학생
		for(int i=0; i<scores.length; i++) {
			for(int j=0; j<scores.length; j++) {
				if(scores[i][scores[i].length-1] < scores[j][scores[i].length-1]) {
					++ranks[i];
				}
			}
		}
		// 2차원 배열의 출력, 1차원 배열 출력
		for(int i=0; i<scores.length; i++) {
			System.out.printf(" %03d |", i+1);
			for(int j=0; j<scores[i].length; j++) {
				System.out.printf("%6d | ", scores[i][j]);
			}
			System.out.printf("%6.2f | %6c | %6d | \n", aves[i], grds[i], ranks[i]);
		}

		// 라인 출력
		System.out.print("-----");
		for(int i=0; i<subjects.length; i++) {
			System.out.print("---------");
		}
		System.out.println();
		
		//마지막 줄-> 국어, 영어, 수학, 총점, 평균의 전체 평균을 계산하여 출력
		// 0. 앞에 NO 칸맞추기
		System.out.printf(" %03d |", 0);
		
		// 1. 국어, 영어, 수학, 총점 먼저 평균계산
		for(int i=0; i<scores[i].length; i++) {
			for(int j=0; j<scores.length; j++) {
				t_aves[i] += scores[j][i]/scores.length;
			}
		}
		
		// 2. 평균의 평균 계산
		for(int i=0; i<aves.length; i++) {
				t_aves[t_aves.length-1] += aves[i];
		}
		t_aves[t_aves.length-1] = t_aves[t_aves.length-1]/aves.length;
		
		// 3. 계산한 평균들 모두 출력
		for(int i=0; i<scores.length; i++) {
			System.out.printf("%6.2f | ", t_aves[i]);
		}
		System.out.println();
		
		// 라인 출력
		System.out.print("-----");
		for(int i=0; i<subjects.length; i++) {
			System.out.print("---------");
		}
		System.out.println();
	
		sc.close();
		//end
	}
}
