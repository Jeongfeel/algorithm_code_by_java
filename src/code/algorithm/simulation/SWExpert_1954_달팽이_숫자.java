package code.algorithm.simulation;

import java.util.Scanner;

public class SWExpert_1954_달팽이_숫자 {
	private static final int RIGHT = 0;
	private static final int DOWN = 1;
	private static final int LEFT = 2;
	private static final int UP = 3;
	private static final int MOVE_X_COORDINATE[] = { 0, 1, 0, -1 };
	private static final int MOVE_Y_COORDINATE[] = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int testcase = sc.nextInt();

		for (int tc = 1; tc <= testcase; tc++) {
			int sizeOfSnailMap = sc.nextInt();

			int snailMap[][] = new int[sizeOfSnailMap][sizeOfSnailMap];

			int xCoordinate = 0;
			int yCoordinate = 0;
			int direction = RIGHT;

			for (int index = 1; index <= sizeOfSnailMap * sizeOfSnailMap; index++) {
				snailMap[xCoordinate][yCoordinate] = index;

				if (direction == RIGHT) {
					if (yCoordinate == sizeOfSnailMap - 1
							|| snailMap[xCoordinate + MOVE_X_COORDINATE[direction]][yCoordinate
									+ MOVE_Y_COORDINATE[direction]] != 0) {
						direction = DOWN;
					}
				} else if (direction == DOWN) {
					if (xCoordinate == sizeOfSnailMap - 1
							|| snailMap[xCoordinate + MOVE_X_COORDINATE[direction]][yCoordinate
									+ MOVE_Y_COORDINATE[direction]] != 0) {
						direction = LEFT;
					}
				} else if (direction == LEFT) {
					if (yCoordinate == 0 || snailMap[xCoordinate + MOVE_X_COORDINATE[direction]][yCoordinate
							+ MOVE_Y_COORDINATE[direction]] != 0) {
						direction = UP;
					}
				} else if (direction == UP) {
					if (xCoordinate == 0 || snailMap[xCoordinate + MOVE_X_COORDINATE[direction]][yCoordinate
							+ MOVE_Y_COORDINATE[direction]] != 0) {
						direction = RIGHT;
					}
				}

				xCoordinate += MOVE_X_COORDINATE[direction];
				yCoordinate += MOVE_Y_COORDINATE[direction];
			}

			System.out.println("#" + tc);
			for (int i = 0; i < sizeOfSnailMap; i++) {
				for (int j = 0; j < sizeOfSnailMap; j++) {
					System.out.print(snailMap[i][j] + " ");
				}
				System.out.println();
			}
		}

		sc.close();
	}
}