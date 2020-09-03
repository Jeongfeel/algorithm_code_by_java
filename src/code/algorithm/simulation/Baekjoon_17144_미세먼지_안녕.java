package code.algorithm.simulation;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_17144_미세먼지_안녕 {
	private static final int AIR_CLEANER = -1;

	private static final int COUNT_OF_DIRECTION = 4;
	private static final int SPREAD_X_COORDINATE[] = { 1, -1, 0, 0 };
	private static final int SPREAD_Y_COORDINATE[] = { 0, 0, 1, -1 };
	private static final int FIRST_AIR_CLEANER_X_COORDINATE[] = { 0, -1, 0, 1 };
	private static final int FIRST_AIR_CLEANER_Y_COORDINATE[] = { 1, 0, -1, 0 };
	private static final int SECOND_AIR_CLEANER_X_COORDINATE[] = { 0, 1, 0, -1 };
	private static final int SECOND_AIR_CLEANER_Y_COORDINATE[] = { 1, 0, -1, 0 };

	private static int R, C, T;
	private static int house[][];
	private static Point airCleanerPosition[] = new Point[2];

	public static void main(String[] args) throws IOException {
		inputHouseData();
		makeDustAfterTSecond();
		printAmountOfDust();
	}

	private static void inputHouseData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		house = new int[R][C];

		int airCleanerIndex = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
				if (house[i][j] == AIR_CLEANER) {
					airCleanerPosition[airCleanerIndex++] = new Point(i, j);
				}
			}
		}
	}

	private static void makeDustAfterTSecond() {
		for (int i = 0; i < T; i++) {
			spreadDust();
			operateAirCleaner();
		}
	}

	private static void spreadDust() {
		int spreadDust[][] = new int[R][C];
		Point firstCleaner = airCleanerPosition[0];
		Point secondCleaner = airCleanerPosition[1];
		spreadDust[firstCleaner.x][firstCleaner.y] = AIR_CLEANER;
		spreadDust[secondCleaner.x][secondCleaner.y] = AIR_CLEANER;

		for (int x = 0; x < R; x++) {
			for (int y = 0; y < C; y++) {
				if (house[x][y] == AIR_CLEANER) {
					continue;
				}

				int dust = house[x][y];
				int amountOfSpreadDust = dust / 5;
				int countOfSpread = 0;
				for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
					int nextX = x + SPREAD_X_COORDINATE[direction];
					int nextY = y + SPREAD_Y_COORDINATE[direction];

					if (nextX < 0 || nextX == R || nextY < 0 || nextY == C) {
						continue;
					}
					if (house[nextX][nextY] == AIR_CLEANER) {
						continue;
					}

					spreadDust[nextX][nextY] += amountOfSpreadDust;
					countOfSpread += 1;
				}

				spreadDust[x][y] += dust - (countOfSpread * amountOfSpreadDust);
			}
		}

		for (int x = 0; x < R; x++) {
			for (int y = 0; y < C; y++) {
				house[x][y] = spreadDust[x][y];
			}
		}
	}

	private static void operateAirCleaner() {
		int nextHouse[][] = getOrigianlHouse();
		Point firstCleaner = airCleanerPosition[0];
		Point secondCleaner = airCleanerPosition[1];

		int x = firstCleaner.x;
		int y = firstCleaner.y + 1;
		int direction = 0;
		while (direction < COUNT_OF_DIRECTION) {
			int nextX = x + FIRST_AIR_CLEANER_X_COORDINATE[direction];
			int nextY = y + FIRST_AIR_CLEANER_Y_COORDINATE[direction];

			if (nextX < 0 || nextX == firstCleaner.x + 1 || nextY < 0 || nextY == C) {
				direction += 1;
				continue;
			}
			nextHouse[nextX][nextY] = house[x][y];
			x = nextX;
			y = nextY;
		}

		x = secondCleaner.x;
		y = secondCleaner.y + 1;
		direction = 0;
		while (direction < COUNT_OF_DIRECTION) {
			int nextX = x + SECOND_AIR_CLEANER_X_COORDINATE[direction];
			int nextY = y + SECOND_AIR_CLEANER_Y_COORDINATE[direction];

			if (nextX < secondCleaner.x || nextX == R || nextY < 0 || nextY == C) {
				direction += 1;
				continue;
			}
			nextHouse[nextX][nextY] = house[x][y];
			x = nextX;
			y = nextY;
		}

		makeOriginalHouse(nextHouse);
	}

	private static int[][] getOrigianlHouse() {
		int nextHouse[][] = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				nextHouse[i][j] = house[i][j];
			}
		}

		return nextHouse;
	}

	private static void makeOriginalHouse(int[][] nextHouse) {
		Point firstCleaner = airCleanerPosition[0];
		Point secondCleaner = airCleanerPosition[1];

		nextHouse[firstCleaner.x][firstCleaner.y] = AIR_CLEANER;
		nextHouse[secondCleaner.x][secondCleaner.y] = AIR_CLEANER;
		nextHouse[firstCleaner.x][firstCleaner.y + 1] = 0;
		nextHouse[secondCleaner.x][secondCleaner.y + 1] = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				house[i][j] = nextHouse[i][j];
			}
		}
	}

	private static void printAmountOfDust() {
		int amountOfDust = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (house[i][j] == AIR_CLEANER) {
					continue;
				}

				amountOfDust += house[i][j];
			}
		}

		System.out.println(amountOfDust);
	}

}
