package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JungOl_1733_오목 {
	private static final int LENGTH_OF_BADUK_BOARD = 19;
	private static final int BLANK = 0;
	private static final int BLACK = 1;
	private static final int WHITE = 2;

	private static int badukBoard[][] = new int[LENGTH_OF_BADUK_BOARD + 1][LENGTH_OF_BADUK_BOARD + 1];
	private static boolean isVistedBadukBoard[][][] = new boolean[LENGTH_OF_BADUK_BOARD + 1][LENGTH_OF_BADUK_BOARD
			+ 1][4];

	public static void main(String[] args) throws IOException {
		inputData();
		printWinColor();
	}

	private static void inputData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 1; i <= LENGTH_OF_BADUK_BOARD; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= LENGTH_OF_BADUK_BOARD; j++) {
				badukBoard[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void printWinColor() {
		for (int i = 1; i <= LENGTH_OF_BADUK_BOARD; i++) {
			for (int j = 1; j <= LENGTH_OF_BADUK_BOARD; j++) {
				if (badukBoard[j][i] == BLANK) {
					continue;
				}

				if (isContinuousFive(j, i) == true) {
					System.out.println(badukBoard[j][i]);
					System.out.println(j + " " + i);
					return;
				}
			}
		}

		System.out.println("0");
	}

	private static boolean isContinuousFive(int y, int x) {
		int stoneColor = badukBoard[y][x];

		int index = 1;
		int countOfStone = 1;
		while (true) {
			int nextY = y + index;

			if (nextY > LENGTH_OF_BADUK_BOARD) {
				break;
			}
			if (stoneColor != badukBoard[nextY][x]) {
				break;
			}
			if (isVistedBadukBoard[nextY][x][0]) {
				break;
			}

			isVistedBadukBoard[nextY][x][0] = true;
			index += 1;
			countOfStone += 1;
		}
		if (countOfStone == 5) {
			return true;
		}

		index = 1;
		countOfStone = 1;
		while (true) {
			int nextX = x + index;

			if (nextX > LENGTH_OF_BADUK_BOARD) {
				break;
			}
			if (stoneColor != badukBoard[y][nextX]) {
				break;
			}
			if (isVistedBadukBoard[y][nextX][1]) {
				break;
			}

			isVistedBadukBoard[y][nextX][1] = true;
			index += 1;
			countOfStone += 1;
		}
		if (countOfStone == 5) {
			return true;
		}

		index = 1;
		countOfStone = 1;
		while (true) {
			int nextX = x + index;
			int nextY = y + index;

			if (nextX > LENGTH_OF_BADUK_BOARD || nextY > LENGTH_OF_BADUK_BOARD) {
				break;
			}
			if (stoneColor != badukBoard[nextY][nextX]) {
				break;
			}
			if (isVistedBadukBoard[nextY][nextX][2]) {
				break;
			}

			isVistedBadukBoard[nextY][nextX][2] = true;
			index += 1;
			countOfStone += 1;
		}
		if (countOfStone == 5) {
			return true;
		}

		index = 1;
		countOfStone = 1;
		while (true) {
			int nextX = x + index;
			int nextY = y - index;

			if (nextX == 0 || nextY > LENGTH_OF_BADUK_BOARD) {
				break;
			}
			if (stoneColor != badukBoard[nextY][nextX]) {
				break;
			}
			if (isVistedBadukBoard[nextY][nextX][3]) {
				break;
			}

			isVistedBadukBoard[nextY][nextX][3] = true;
			index += 1;
			countOfStone += 1;
		}
		if (countOfStone == 5) {
			return true;
		}

		return false;
	}

}
