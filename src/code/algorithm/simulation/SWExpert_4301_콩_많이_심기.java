package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_4301_콩_많이_심기 {
	private static final int MAXIMUM_LENGTH_OF_GROUND = 1000;
	private static final boolean BLANK = false;
	private static final boolean BEAN = true;
	private static final int COUNT_OF_DIRECTION = 2;
	private static final int MOVE_X_COORDINATE[] = { -2, 0 };
	private static final int MOVE_Y_COORDINATE[] = { 0, -2 };

	private static boolean ground[][] = new boolean[MAXIMUM_LENGTH_OF_GROUND][MAXIMUM_LENGTH_OF_GROUND];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testcase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int xLength = Integer.parseInt(st.nextToken());
			int yLength = Integer.parseInt(st.nextToken());

			initGround(xLength, yLength);

			System.out.println("#" + tc + " " + getMaximumCountOfBean(xLength, yLength));
		}
	}

	private static void initGround(int xLength, int yLength) {
		for (int y = 0; y < yLength; y++) {
			for (int x = 0; x < xLength; x++) {
				ground[y][x] = BLANK;
			}
		}
	}

	private static int getMaximumCountOfBean(int xLength, int yLength) {
		int maximumCountOfBean = 0;
		for (int y = 0; y < yLength; y++) {
			for (int x = 0; x < xLength; x++) {
				if (isValidGround(x, y, xLength, yLength)) {
					maximumCountOfBean += 1;
					ground[y][x] = BEAN;
				}
			}
		}

		return maximumCountOfBean;
	}

	private static boolean isValidGround(int x, int y, int xLength, int yLength) {
		for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
			int nextXCoordinate = x + MOVE_X_COORDINATE[direction];
			int nextYCoordinate = y + MOVE_Y_COORDINATE[direction];

			if (nextXCoordinate < 0 || nextXCoordinate >= xLength || nextYCoordinate < 0
					|| nextYCoordinate >= yLength) {
				continue;
			}

			if (ground[nextYCoordinate][nextXCoordinate] == BEAN) {
				return false;
			}
		}

		return true;
	}
}
