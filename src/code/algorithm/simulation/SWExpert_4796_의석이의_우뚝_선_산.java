package code.algorithm.simulation;

import java.io.IOException;
import java.util.Scanner;

public class SWExpert_4796_의석이의_우뚝_선_산 {
	private static Scanner sc;

	private static int N;
	private static int mountain[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		sc = new Scanner(System.in);
		int countOfTestcase = sc.nextInt();

		for (int testcase = 1; testcase <= countOfTestcase; testcase++) {
			inputData();
			printMountainSection(testcase);
		}
	}

	private static void inputData() throws NumberFormatException, IOException {
		N = sc.nextInt();
		mountain = new int[N];

		for (int i = 0; i < N; i++) {
			mountain[i] = sc.nextInt();
		}
	}

	private static void printMountainSection(int testcaseNumber) {
		int countOfMountainSection = 0;

		int startIndex = 0;
		int leftMountain = 0;
		while (true) {
			if (startIndex + 1 >= N) {
				break;
			}

			if (mountain[startIndex] < mountain[startIndex + 1]) {
				leftMountain += 1;
				startIndex += 1;
				continue;
			}

			int endIndex = startIndex;
			int rightMountain = 0;
			while (true) {
				if (endIndex + 1 >= N) {
					break;
				}

				if (mountain[endIndex] > mountain[endIndex + 1]) {
					rightMountain += 1;
					endIndex += 1;
					continue;
				}

				break;
			}

			countOfMountainSection += leftMountain * rightMountain;
			leftMountain = 0;
			startIndex = endIndex;
		}

		System.out.println("#" + testcaseNumber + " " + countOfMountainSection);
	}
}
