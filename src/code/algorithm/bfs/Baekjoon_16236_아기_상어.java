package code.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_16236_아기_상어 {
	private static class Shark {
		int x;
		int y;
		int size;
		int countOfEatenFish;

		public Shark(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.size = 2;
			this.countOfEatenFish = 0;
		}
	}

	private static class SeaInfo {
		int x;
		int y;
		int movingTime;
		boolean isAbleToEat;

		public SeaInfo(int x, int y, int movingTime, boolean isAbleToEat) {
			super();
			this.x = x;
			this.y = y;
			this.movingTime = movingTime;
			this.isAbleToEat = isAbleToEat;
		}

	}

	private static final int INVALID_NUMBER = Integer.MAX_VALUE;
	private static final int BLANK = 0;
	private static final int BABY_SHARK = 9;

	private static final int COUNT_OF_DIRECTION = 4;
	private static final int MOVE_X[] = { 1, -1, 0, 0 };
	private static final int MOVE_Y[] = { 0, 0, 1, -1 };

	private static int N;
	private static int sea[][];
	private static boolean isVisitedSea[][];
	private static Shark babyShark;

	public static void main(String[] args) throws IOException {
		inputData();
		printMaximumTimeToEatFish();
	}

	private static void inputData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		sea = new int[N][N];
		isVisitedSea = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				sea[i][j] = Integer.parseInt(st.nextToken());
				if (sea[i][j] == BABY_SHARK) {
					babyShark = new Shark(i, j);
					sea[i][j] = BLANK;
				}
			}
		}
	}

	private static void printMaximumTimeToEatFish() {
		int maximumTimeToEatFish = 0;

		while (true) {
			initIsVistedSea();

			int minimumMovingTime = INVALID_NUMBER;
			int fishX = INVALID_NUMBER;
			int fishY = INVALID_NUMBER;

			Queue<SeaInfo> babySharkPositionQueue = new LinkedList<SeaInfo>();
			babySharkPositionQueue.add(new SeaInfo(babyShark.x, babyShark.y, 0, false));
			isVisitedSea[babyShark.x][babyShark.y] = true;

			while (!babySharkPositionQueue.isEmpty()) {
				SeaInfo babySharkPosition = babySharkPositionQueue.poll();
				int x = babySharkPosition.x;
				int y = babySharkPosition.y;
				int movingTime = babySharkPosition.movingTime;
				boolean isAbleToEat = babySharkPosition.isAbleToEat;

				if (minimumMovingTime != INVALID_NUMBER && movingTime == minimumMovingTime) {
					if (isAbleToEat == true) {
						if (fishX > x) {
							fishX = x;
							fishY = y;
						} else if (fishX == x) {
							if (fishY > y) {
								fishY = y;
							}
						}
					}

					continue;
				}

				for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
					int nextX = x + MOVE_X[direction];
					int nextY = y + MOVE_Y[direction];

					if (isImpossibleToMove(x, y, nextX, nextY)) {
						continue;
					}

					if (sea[nextX][nextY] != BLANK && sea[nextX][nextY] < babyShark.size) {
						if (minimumMovingTime == INVALID_NUMBER) {
							minimumMovingTime = movingTime + 1;
						}
						babySharkPositionQueue.add(new SeaInfo(nextX, nextY, movingTime + 1, true));
					} else {
						babySharkPositionQueue.add(new SeaInfo(nextX, nextY, movingTime + 1, false));
					}

					isVisitedSea[nextX][nextY] = true;
				}
			}

			if (minimumMovingTime == INVALID_NUMBER) {
				break;
			}

			maximumTimeToEatFish += minimumMovingTime;
			sea[fishX][fishY] = BLANK;
			babyShark.x = fishX;
			babyShark.y = fishY;
			babyShark.countOfEatenFish += 1;
			if (babyShark.size == babyShark.countOfEatenFish) {
				babyShark.size += 1;
				babyShark.countOfEatenFish = 0;
			}
		}

		System.out.println(maximumTimeToEatFish);
	}

	private static void initIsVistedSea() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				isVisitedSea[i][j] = false;
			}
		}
	}

	private static boolean isImpossibleToMove(int x, int y, int nextX, int nextY) {
		if (nextX < 0 || nextX == N || nextY < 0 || nextY == N) {
			return true;
		}
		if (isVisitedSea[nextX][nextY] == true) {
			return true;
		}
		if (sea[nextX][nextY] > babyShark.size) {
			return true;
		}
		return false;
	}
}
