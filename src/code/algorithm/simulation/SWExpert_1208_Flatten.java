package code.algorithm.simulation;

import java.util.Scanner;

public class SWExpert_1208_Flatten {
	private static final int TEST_CASE = 10;
	private static final int COUNT_OF_BOX = 100;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 1; i <= TEST_CASE; i++) {
			int countOfDump = sc.nextInt();

			int boxArray[] = new int[COUNT_OF_BOX + 1];
			int minimumHeight = 100;
			int maximumHeight = 0;
			for (int j = 0; j < COUNT_OF_BOX; j++) {
				int boxHeight = sc.nextInt();
				boxArray[boxHeight] += 1;

				if (boxHeight > maximumHeight) {
					maximumHeight = boxHeight;
				}
				if (boxHeight < minimumHeight) {
					minimumHeight = boxHeight;
				}
			}

			for (int j = 0; j < countOfDump; j++) {
				boxArray[maximumHeight] -= 1;
				boxArray[maximumHeight - 1] += 1;
				boxArray[minimumHeight] -= 1;
				boxArray[minimumHeight + 1] += 1;

				if (boxArray[maximumHeight] == 0) {
					maximumHeight -= 1;
				}
				if (boxArray[minimumHeight] == 0) {
					minimumHeight += 1;
				}

				if (maximumHeight <= minimumHeight) {
					break;
				}
			}

			System.out.println("#" + i + " " + Math.abs(maximumHeight - minimumHeight));
		}

		sc.close();
	}
}