package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_18809_Gaaaaaaaaaarden {
	static class SprayedPosition {
		int x;
		int y;

		public SprayedPosition(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static final int LAKE = 0;
	private static final int IMPOSSIBLE_LIQUID_GROUND = 1;
	private static final int POSSIBLE_LIQUID_GROUND = 2;

	private static final int GREEN_LIQUID = 1;
	private static final int RED_LIQUID = 2;
	private static final int FLOWER = 3;

	private static final int COUNT_OF_DIRECTION = 4;
	private static final int MOVE_X_COORDINATE[] = { 1, -1, 0, 0 };
	private static final int MOVE_Y_COORDINATE[] = { 0, 0, 1, -1 };

	private static int N, M;
	private static int countOfGreenLiquid, countOfRedLiquid;
	private static int garden[][];
	private static int maximumCountOfFlower = 0;
	private static SprayedPosition sprayedGreenLiquidArray[];
	private static SprayedPosition sprayedRedLiquidArray[];

	private static BufferedReader br;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		inputGardenData();
		makeMaximumCountOfFlower();
		System.out.println(maximumCountOfFlower);
	}

	private static void inputGardenData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		garden = new int[N][M];

		countOfGreenLiquid = Integer.parseInt(st.nextToken());
		countOfRedLiquid = Integer.parseInt(st.nextToken());
		sprayedGreenLiquidArray = new SprayedPosition[countOfGreenLiquid];
		sprayedRedLiquidArray = new SprayedPosition[countOfRedLiquid];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				garden[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void makeMaximumCountOfFlower() {
		List<SprayedPosition> possibleLiquidGroundList = getPossibleLiquidGround();
		sprayGreenLiquid(0, 0, possibleLiquidGroundList);
	}

	private static List<SprayedPosition> getPossibleLiquidGround() {
		List<SprayedPosition> possibleLiquidGroundList = new ArrayList<SprayedPosition>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (garden[i][j] == LAKE) {
					continue;
				}
				if (garden[i][j] == IMPOSSIBLE_LIQUID_GROUND) {
					continue;
				}

				possibleLiquidGroundList.add(new SprayedPosition(i, j));
			}
		}

		return possibleLiquidGroundList;
	}

	private static void sprayGreenLiquid(int currentCountOfGreenLiquid, int index,
			List<SprayedPosition> possibleLiquidGroundList) {
		if (currentCountOfGreenLiquid == countOfGreenLiquid) {
			List<SprayedPosition> possibleRedLiquidGroundList = getPossibleLiquidGround(possibleLiquidGroundList);
			sprayRedLiquid(0, 0, possibleRedLiquidGroundList);

			return;
		}

		for (int i = index; i < possibleLiquidGroundList.size(); i++) {
			int possibleLiquidXCoordinate = possibleLiquidGroundList.get(i).x;
			int possibleLiquidYCoordinate = possibleLiquidGroundList.get(i).y;
			sprayedGreenLiquidArray[currentCountOfGreenLiquid] = new SprayedPosition(possibleLiquidXCoordinate,
					possibleLiquidYCoordinate);

			sprayGreenLiquid(currentCountOfGreenLiquid + 1, i + 1, possibleLiquidGroundList);
		}
	}

	private static List<SprayedPosition> getPossibleLiquidGround(List<SprayedPosition> possibleLiquidGroundList) {
		List<SprayedPosition> possibleRedLiquidGroundList = new ArrayList<SprayedPosition>();

		for (SprayedPosition possibleLiquidGround : possibleLiquidGroundList) {
			int possibleLiquidXCoordinate = possibleLiquidGround.x;
			int possibleLiquidYCoordinate = possibleLiquidGround.y;
			boolean isSprayedPosition = false;

			for (SprayedPosition sprayedGreenLiquid : sprayedGreenLiquidArray) {
				if (possibleLiquidXCoordinate == sprayedGreenLiquid.x
						&& possibleLiquidYCoordinate == sprayedGreenLiquid.y) {
					isSprayedPosition = true;
					break;
				}
			}

			if (isSprayedPosition == true) {
				continue;
			}

			possibleRedLiquidGroundList.add(new SprayedPosition(possibleLiquidXCoordinate, possibleLiquidYCoordinate));
		}

		return possibleRedLiquidGroundList;
	}

	private static void sprayRedLiquid(int currentCountOfRedLiquid, int index,
			List<SprayedPosition> possibleLiquidGroundList) {
		if (currentCountOfRedLiquid == countOfRedLiquid) {
			int countOfFlower = getCountOfFlower();
			if (maximumCountOfFlower < countOfFlower) {
				maximumCountOfFlower = countOfFlower;
			}

			return;
		}

		for (int i = index; i < possibleLiquidGroundList.size(); i++) {
			int possibleLiquidXCoordinate = possibleLiquidGroundList.get(i).x;
			int possibleLiquidYCoordinate = possibleLiquidGroundList.get(i).y;
			sprayedRedLiquidArray[currentCountOfRedLiquid] = new SprayedPosition(possibleLiquidXCoordinate,
					possibleLiquidYCoordinate);

			sprayRedLiquid(currentCountOfRedLiquid + 1, i + 1, possibleLiquidGroundList);
		}
	}

	private static int getCountOfFlower() {
		int countOfFlower = 0;

		int liquidTimeArray[][] = new int[N][M];
		int gardenLiquidState[][] = new int[N][M];
		Queue<SprayedPosition> sprayedPositionQueue = new LinkedList<SprayedPosition>();

		for (SprayedPosition sprayedPosition : sprayedGreenLiquidArray) {
			sprayedPositionQueue.add(sprayedPosition);
			gardenLiquidState[sprayedPosition.x][sprayedPosition.y] = GREEN_LIQUID;
		}

		for (SprayedPosition sprayedPosition : sprayedRedLiquidArray) {
			sprayedPositionQueue.add(sprayedPosition);
			gardenLiquidState[sprayedPosition.x][sprayedPosition.y] = RED_LIQUID;
		}

		while (!sprayedPositionQueue.isEmpty()) {
			SprayedPosition sprayedPosition = sprayedPositionQueue.poll();
			int xCoordinate = sprayedPosition.x;
			int yCoordinate = sprayedPosition.y;
			int time = liquidTimeArray[xCoordinate][yCoordinate];

			if (gardenLiquidState[xCoordinate][yCoordinate] == FLOWER) {
				continue;
			}

			for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
				int nextXCoordinate = xCoordinate + MOVE_X_COORDINATE[direction];
				int nextYCoordinate = yCoordinate + MOVE_Y_COORDINATE[direction];
				int nextTime = time + 1;

				if (nextXCoordinate < 0 || nextXCoordinate == N || nextYCoordinate < 0 || nextYCoordinate == M) {
					continue;
				}

				if (garden[nextXCoordinate][nextYCoordinate] == LAKE) {
					continue;
				}

				if (gardenLiquidState[nextXCoordinate][nextYCoordinate] == FLOWER) {
					continue;
				}

				if (gardenLiquidState[nextXCoordinate][nextYCoordinate] != 0) {
					if (gardenLiquidState[xCoordinate][yCoordinate] == gardenLiquidState[nextXCoordinate][nextYCoordinate]) {
						continue;
					}

					if (liquidTimeArray[nextXCoordinate][nextYCoordinate] != nextTime) {
						continue;
					}

					gardenLiquidState[nextXCoordinate][nextYCoordinate] = FLOWER;
					countOfFlower += 1;
					continue;
				}

				gardenLiquidState[nextXCoordinate][nextYCoordinate] = gardenLiquidState[xCoordinate][yCoordinate];
				liquidTimeArray[nextXCoordinate][nextYCoordinate] = time + 1;

				sprayedPositionQueue.add(new SprayedPosition(nextXCoordinate, nextYCoordinate));
			}
		}

		return countOfFlower;
	}

}
