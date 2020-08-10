package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_17135_캐슬_디펜스 {
	private static final int BLANK = 0;
	private static final int ENEMY = 1;
	private static final int ARCHER = 2;
	private static final int MAXIMUM_ARCHER_COUNT = 3;
	private static final int INVALID_COORDINATE = -1;
	private static final int MAXIMUM_LENGTH_OF_CASTLE = 15;
	
	private static int originalCastle[][];
	private static int yLength;
	private static int xLength;
	private static int distance;
	private static int maximumDeadEnemy = 0;

	private static class Enemy {
		int xCoordinate;
		int yCoordinate;

		public Enemy(int xCoordinate, int yCoordinate) {
			super();
			this.xCoordinate = xCoordinate;
			this.yCoordinate = yCoordinate;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		yLength = Integer.parseInt(st.nextToken());
		xLength = Integer.parseInt(st.nextToken());
		distance = Integer.parseInt(st.nextToken());
		makeOriginalCastle(br);
		makeMaximumDeadEnemy();

		System.out.println(maximumDeadEnemy);
	}

	private static void makeOriginalCastle(BufferedReader br) throws IOException {
		originalCastle = new int[yLength + 1][xLength];

		for (int y = 0; y < yLength; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x = 0; x < xLength; x++) {
				originalCastle[y][x] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void makeMaximumDeadEnemy() {
		int castle[][] = new int[yLength + 1][xLength];
		positionedArcher(castle, 0, 0);
	}

	private static void positionedArcher(int[][] castle, int index, int countOfArcher) {
		if (countOfArcher == MAXIMUM_ARCHER_COUNT) {
			castle = copyOriginalCastle(castle);
			checkMaximumDeadEnemy(castle);
			return;
		}

		if (index == xLength) {
			return;
		}

		castle[yLength][index] = ARCHER;
		positionedArcher(castle, index + 1, countOfArcher + 1);
		castle[yLength][index] = BLANK;
		positionedArcher(castle, index + 1, countOfArcher);
	}

	private static int[][] copyOriginalCastle(int[][] castle) {
		for (int y = 0; y < yLength; y++) {
			for (int x = 0; x < xLength; x++) {
				castle[y][x] = originalCastle[y][x];
			}
		}

		return castle;
	}

	private static void checkMaximumDeadEnemy(int[][] castle) {
		int deadEnemy = 0;
		while (isExistedEnemy(castle)) {
			deadEnemy += shootEnemy(castle);
			downEnemy(castle);
		}

		if (deadEnemy > maximumDeadEnemy) {
			maximumDeadEnemy = deadEnemy;
		}
	}

	private static boolean isExistedEnemy(int[][] castle) {
		for (int y = 0; y < yLength; y++) {
			for (int x = 0; x < xLength; x++) {
				if (castle[y][x] == BLANK) {
					continue;
				}

				return true;
			}
		}

		return false;
	}

	private static int shootEnemy(int[][] castle) {
		int deadEnemy = 0;
		Enemy deadEnemyList[] = new Enemy[MAXIMUM_ARCHER_COUNT];
		int deadEnemyIndex = 0;

		for (int x = 0; x < xLength; x++) {
			if (castle[yLength][x] != ARCHER) {
				continue;
			}

			Enemy enemy = checkNearestEnemy(castle, x);
			deadEnemyList[deadEnemyIndex++] = enemy;
		}

		for (Enemy enemy : deadEnemyList) {
			if (enemy.yCoordinate == INVALID_COORDINATE && enemy.xCoordinate == INVALID_COORDINATE) {
				continue;
			}
			if (castle[enemy.yCoordinate][enemy.xCoordinate] == BLANK) {
				continue;
			}

			castle[enemy.yCoordinate][enemy.xCoordinate] = BLANK;
			deadEnemy += 1;
		}

		return deadEnemy;
	}

	private static Enemy checkNearestEnemy(int[][] castle, int archerIndex) {
		int xCoordinate = INVALID_COORDINATE;
		int yCoordinate = INVALID_COORDINATE;
		int shortestDistance = MAXIMUM_LENGTH_OF_CASTLE;

		for (int y = 0; y < yLength; y++) {
			for (int x = 0; x < xLength; x++) {
				if (castle[y][x] == BLANK) {
					continue;
				}

				int enemyDistance = yLength - y + Math.abs(archerIndex - x);
				if (enemyDistance > distance) {
					continue;
				}

				if (enemyDistance > shortestDistance) {
					continue;
				}

				if (enemyDistance < shortestDistance) {
					xCoordinate = x;
					yCoordinate = y;
					shortestDistance = enemyDistance;
					continue;
				}

				if (enemyDistance == shortestDistance) {
					if (x < xCoordinate) {
						xCoordinate = x;
						yCoordinate = y;
						continue;
					}
				}
			}
		}

		return new Enemy(xCoordinate, yCoordinate);
	}

	private static void downEnemy(int[][] castle) {
		for (int y = yLength - 1; y > 0; y--) {
			for (int x = 0; x < xLength; x++) {
				castle[y][x] = castle[y - 1][x];
			}
		}

		for (int x = 0; x < xLength; x++) {
			castle[0][x] = BLANK;
		}
	}
}
