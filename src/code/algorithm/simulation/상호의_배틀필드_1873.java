package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상호의_배틀필드_1873 {
	private static final char GROUND = '.';
	private static final char BRICK_WALL = '*';
	private static final char IRON_WALL = '#';
	private static final char WATER = '-';
	private static final char UP_TANK = '^';
	private static final char DOWN_TANK = 'v';
	private static final char RIGHT_TANK = '>';
	private static final char LEFT_TANK = '<';
	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int RIGHT = 2;
	private static final int LEFT = 3;
	private static final char ORDER_UP = 'U';
	private static final char ORDER_DOWN = 'D';
	private static final char ORDER_RIGHT = 'R';
	private static final char ORDER_LEFT = 'L';
	private static final char ORDER_SHOOT = 'S';
	private static final int MOVE_X_COORDINATE[] = { -1, 1, 0, 0 };
	private static final int MOVE_Y_COORDINATE[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testcase; tc++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int sizeOfX = Integer.parseInt(st1.nextToken());
			int sizeOfY = Integer.parseInt(st1.nextToken());

			char gameMap[][] = new char[sizeOfX][sizeOfY];
			for (int x = 0; x < sizeOfX; x++) {
				String inputData = br.readLine();
				for (int y = 0; y < sizeOfY; y++) {
					gameMap[x][y] = inputData.charAt(y);
				}
			}

			int countOfOrder = Integer.parseInt(br.readLine());
			String orders = br.readLine();

			int tankPositionAndDirection[] = getTankPositionAndDirection(gameMap); // x,y,direction
			gameMap = getGameMapAfterOrder(gameMap, tankPositionAndDirection, countOfOrder, orders);

			System.out.print("#" + tc + " ");
			for (int i = 0; i < sizeOfX; i++) {
				for (int j = 0; j < sizeOfY; j++) {
					System.out.print(gameMap[i][j]);
				}
				System.out.println();
			}
		}

		br.close();
	}

	private static int[] getTankPositionAndDirection(char[][] gameMap) {
		int tankPositionAndDirection[] = new int[3];

		for (int x = 0; x < gameMap.length; x++) {
			for (int y = 0; y < gameMap[0].length; y++) {
				if (gameMap[x][y] == UP_TANK) {
					tankPositionAndDirection[0] = x;
					tankPositionAndDirection[1] = y;
					tankPositionAndDirection[2] = UP;
					return tankPositionAndDirection;
				}
				if (gameMap[x][y] == DOWN_TANK) {
					tankPositionAndDirection[0] = x;
					tankPositionAndDirection[1] = y;
					tankPositionAndDirection[2] = DOWN;
					return tankPositionAndDirection;
				}
				if (gameMap[x][y] == RIGHT_TANK) {
					tankPositionAndDirection[0] = x;
					tankPositionAndDirection[1] = y;
					tankPositionAndDirection[2] = RIGHT;
					return tankPositionAndDirection;
				}
				if (gameMap[x][y] == LEFT_TANK) {
					tankPositionAndDirection[0] = x;
					tankPositionAndDirection[1] = y;
					tankPositionAndDirection[2] = LEFT;
					return tankPositionAndDirection;
				}
			}
		}

		return tankPositionAndDirection;
	}

	private static char[][] getGameMapAfterOrder(char[][] gameMap, int[] tankPositionAndDirection, int countOfOrder,
			String orders) {
		int tankXCoordinate = tankPositionAndDirection[0];
		int tankYCoordinate = tankPositionAndDirection[1];
		int tankDirection = tankPositionAndDirection[2];
		char stateOfTank = gameMap[tankXCoordinate][tankYCoordinate];

		for (int count = 0; count < countOfOrder; count++) {
			char order = orders.charAt(count);
			boolean isMoved = false;

			if (order == ORDER_SHOOT) {
				shootTank(gameMap, tankXCoordinate, tankYCoordinate, tankDirection);
			} else if (order == ORDER_UP) {
				stateOfTank = UP_TANK;
				tankDirection = UP;

				gameMap[tankXCoordinate][tankYCoordinate] = stateOfTank;

				isMoved = moveTank(gameMap, tankXCoordinate, tankYCoordinate, tankDirection);
			} else if (order == ORDER_DOWN) {
				stateOfTank = DOWN_TANK;
				tankDirection = DOWN;

				gameMap[tankXCoordinate][tankYCoordinate] = stateOfTank;
				isMoved = moveTank(gameMap, tankXCoordinate, tankYCoordinate, tankDirection);
			} else if (order == ORDER_RIGHT) {
				stateOfTank = RIGHT_TANK;
				tankDirection = RIGHT;

				gameMap[tankXCoordinate][tankYCoordinate] = stateOfTank;
				isMoved = moveTank(gameMap, tankXCoordinate, tankYCoordinate, tankDirection);
			} else if (order == ORDER_LEFT) {
				stateOfTank = LEFT_TANK;
				tankDirection = LEFT;

				gameMap[tankXCoordinate][tankYCoordinate] = stateOfTank;
				isMoved = moveTank(gameMap, tankXCoordinate, tankYCoordinate, tankDirection);
			}

			if (isMoved == true) {
				tankXCoordinate += MOVE_X_COORDINATE[tankDirection];
				tankYCoordinate += MOVE_Y_COORDINATE[tankDirection];
			}
		}

		return gameMap;
	}

	private static void shootTank(char[][] gameMap, int tankXCoordinate, int tankYCoordinate, int tankDirection) {
		int sizeOfX = gameMap.length;
		int sizeOfY = gameMap[0].length;
		int nextXCoordinate = tankXCoordinate;
		int nextYCoordinate = tankYCoordinate;

		while (true) {
			nextXCoordinate += MOVE_X_COORDINATE[tankDirection];
			nextYCoordinate += MOVE_Y_COORDINATE[tankDirection];

			if (nextXCoordinate < 0 || nextXCoordinate == sizeOfX || nextYCoordinate < 0
					|| nextYCoordinate == sizeOfY) {
				break;
			}

			if (gameMap[nextXCoordinate][nextYCoordinate] == IRON_WALL) {
				break;
			}

			if (gameMap[nextXCoordinate][nextYCoordinate] == GROUND
					|| gameMap[nextXCoordinate][nextYCoordinate] == WATER) {
				continue;
			}

			if (gameMap[nextXCoordinate][nextYCoordinate] == BRICK_WALL) {
				gameMap[nextXCoordinate][nextYCoordinate] = GROUND;
				break;
			}
		}
	}

	private static boolean moveTank(char[][] gameMap, int tankXCoordinate, int tankYCoordinate, int tankDirection) {
		int sizeOfX = gameMap.length;
		int sizeOfY = gameMap[0].length;
		int nextXCoordinate = tankXCoordinate + MOVE_X_COORDINATE[tankDirection];
		int nextYCoordinate = tankYCoordinate + MOVE_Y_COORDINATE[tankDirection];

		if (nextXCoordinate < 0 || nextXCoordinate == sizeOfX || nextYCoordinate < 0 || nextYCoordinate == sizeOfY) {
			return false;
		}
		if (gameMap[nextXCoordinate][nextYCoordinate] == WATER || gameMap[nextXCoordinate][nextYCoordinate] == IRON_WALL
				|| gameMap[nextXCoordinate][nextYCoordinate] == BRICK_WALL) {
			return false;
		}
		if (gameMap[nextXCoordinate][nextYCoordinate] == GROUND) {
			gameMap[nextXCoordinate][nextYCoordinate] = gameMap[tankXCoordinate][tankYCoordinate];
			gameMap[tankXCoordinate][tankYCoordinate] = GROUND;
		}

		return true;
	}
}
