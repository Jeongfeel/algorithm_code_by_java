package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert__1210_Ladder1 {
	private static final int BLANK = 0;
	private static final int LADDER = 1;
	private static final int DESTINATION = 2;
	private static final int LADDER_SIZE = 100;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			int testcase = Integer.parseInt(br.readLine());

			int ladder[][] = makeLadder(br);
			int destinationPosition = getDestinationPosition(ladder);
			int startPosition = getStartPosition(ladder, destinationPosition);

			System.out.println("#" + testcase + " " + startPosition);
		}
	}

	private static int[][] makeLadder(BufferedReader br) throws IOException {
		int ladder[][] = new int[LADDER_SIZE][LADDER_SIZE];
		for (int i = 0; i < LADDER_SIZE; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < LADDER_SIZE; j++) {
				ladder[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		return ladder;
	}

	private static int getDestinationPosition(int[][] ladder) {
		for (int i = 0; i < LADDER_SIZE; i++) {
			if (ladder[i][LADDER_SIZE - 1] == DESTINATION) {
				return i;
			}
		}

		return 0;
	}

	private static int getStartPosition(int[][] ladder, int destinationPosition) {
		for (int i = LADDER_SIZE - 1; i >= 0; i--) {
			if (destinationPosition == 0) {
				destinationPosition = checkRightLadder(ladder, destinationPosition, i);
			} else if (destinationPosition == LADDER_SIZE - 1) {
				destinationPosition = checkLeftLadder(ladder, destinationPosition, i);
			} else {
				if (ladder[destinationPosition + 1][i] == LADDER) {
					destinationPosition = checkRightLadder(ladder, destinationPosition, i);
				} else if (ladder[destinationPosition - 1][i] == LADDER) {
					destinationPosition = checkLeftLadder(ladder, destinationPosition, i);
				}
			}
		}

		return destinationPosition;
	}

	private static int checkRightLadder(int[][] ladder, int destinationPosition, int ladderIndex) {
		int rightPosition = destinationPosition;
		for (int i = destinationPosition + 1; i < LADDER_SIZE; i++) {
			if (ladder[i][ladderIndex] == BLANK) {
				break;
			}

			rightPosition = i;
		}

		return rightPosition;
	}

	private static int checkLeftLadder(int[][] ladder, int destinationPosition, int ladderIndex) {
		int leftPosition = destinationPosition;
		for (int i = destinationPosition - 1; i >= 0; i--) {
			if (ladder[i][ladderIndex] == BLANK) {
				break;
			}

			leftPosition = i;
		}

		return leftPosition;
	}
}