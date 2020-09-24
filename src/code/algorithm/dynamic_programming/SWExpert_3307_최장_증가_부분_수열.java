package code.algorithm.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_3307_최장_증가_부분_수열 {
	private static BufferedReader br;
	private static StringTokenizer st;

	private static int lengthOfArray;
	private static int numberArray[];
	private static int lengthArray[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		int countOfTestcase = Integer.parseInt(br.readLine());
		for (int i = 1; i <= countOfTestcase; i++) {
			inputData();
			printMostLongestArray(i);
		}
	}

	private static void inputData() throws NumberFormatException, IOException {
		lengthOfArray = Integer.parseInt(br.readLine());
		numberArray = new int[lengthOfArray + 1];
		lengthArray = new int[lengthOfArray + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < lengthOfArray + 1; i++) {
			numberArray[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void printMostLongestArray(int testcase) {
		for (int i = 1; i <= lengthOfArray; i++) {
			int mostLongestLength = 0;
			int mostLongestNumber = numberArray[i];

			for (int j = i - 1; j >= 0; j--) {
				if (numberArray[j] > numberArray[i]) {
					continue;
				}
				if (mostLongestLength > lengthArray[j]) {
					continue;
				}

				if (mostLongestLength < lengthArray[j]) {
					mostLongestLength = lengthArray[j];
					mostLongestNumber = numberArray[j];
				} else if (mostLongestNumber > numberArray[j]) {
					mostLongestNumber = numberArray[j];
				}
			}

			lengthArray[i] = mostLongestLength + 1;
		}

		int mostLongestLength = 0;
		for (int i = 1; i <= lengthOfArray; i++) {
			if (mostLongestLength < lengthArray[i]) {
				mostLongestLength = lengthArray[i];
			}
		}

		System.out.println("#" + testcase + " " + mostLongestLength);
	}
}
