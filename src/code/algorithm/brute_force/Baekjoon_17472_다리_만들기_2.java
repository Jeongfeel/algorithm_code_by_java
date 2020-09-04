package code.algorithm.brute_force;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_17472_다리_만들기_2 {
	private static class IslandInfo implements Comparable<IslandInfo> {
		int islandNumber;
		int bridgeLength;

		public IslandInfo(int islandNumber, int bridgeLength) {
			super();
			this.islandNumber = islandNumber;
			this.bridgeLength = bridgeLength;
		}

		@Override
		public int compareTo(IslandInfo o) {
			return this.bridgeLength - o.bridgeLength;
		}

	}

	private static final int SEA = 0;
	private static final int GROUND = 0;
	private static final int INVALID_VALUE = Integer.MAX_VALUE;

	private static final int COUNT_OF_DIRECTION = 4;
	private static final int MOVE_X[] = { 1, -1, 0, 0 };
	private static final int MOVE_Y[] = { 0, 0, 1, -1 };

	private static int N, M;
	private static int map[][];
	private static int countOfIsland;
	private static List<List<Point>> islandList = new ArrayList<List<Point>>();
	private static int distanceOfIsland[][];

	public static void main(String[] args) throws IOException {
		inputMapData();
		checkIsland();
		makeIslandList();
		makeDistanceOfIsland();
		printMinimumBridgeLength();
	}

	private static void inputMapData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void checkIsland() {
		boolean isVisitedMap[][] = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == SEA) {
					continue;
				}
				if (isVisitedMap[i][j] == true) {
					continue;
				}

				countOfIsland += 1;
				makeIsland(i, j, isVisitedMap);
			}
		}
	}

	private static void makeIsland(int startX, int startY, boolean[][] isVisitedMap) {
		Queue<Point> positionQueue = new LinkedList<Point>();
		positionQueue.add(new Point(startX, startY));
		isVisitedMap[startX][startY] = true;

		while (!positionQueue.isEmpty()) {
			Point position = positionQueue.poll();
			int x = position.x;
			int y = position.y;
			map[x][y] = countOfIsland;

			for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
				int nextX = x + MOVE_X[direction];
				int nextY = y + MOVE_Y[direction];

				if (nextX < 0 || nextX == N || nextY < 0 || nextY == M) {
					continue;
				}
				if (isVisitedMap[nextX][nextY] == true) {
					continue;
				}
				if (map[nextX][nextY] == SEA) {
					continue;
				}

				positionQueue.add(new Point(nextX, nextY));
				isVisitedMap[nextX][nextY] = true;
			}
		}
	}

	private static void makeIslandList() {
		for (int i = 0; i <= countOfIsland; i++) {
			islandList.add(new ArrayList<Point>());
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == SEA) {
					continue;
				}

				islandList.get(map[i][j]).add(new Point(i, j));
			}
		}
	}

	private static void makeDistanceOfIsland() {
		distanceOfIsland = new int[countOfIsland + 1][countOfIsland + 1];
		for (int i = 1; i <= countOfIsland; i++) {
			for (int j = i + 1; j <= countOfIsland; j++) {
				int distance = getDistanceOfTwoIsland(i, j);
				distanceOfIsland[i][j] = distance;
				distanceOfIsland[j][i] = distance;
			}
		}
	}

	private static int getDistanceOfTwoIsland(int firstIslandIndex, int secondIslandIndex) {
		int minimumDistance = INVALID_VALUE;

		for (int i = 0; i < islandList.get(firstIslandIndex).size(); i++) {
			int twoIslandDistance = INVALID_VALUE;

			Point firstIsland = islandList.get(firstIslandIndex).get(i);
			int firstIslandX = firstIsland.x;
			int firstIslandY = firstIsland.y;
			for (int j = 0; j < islandList.get(secondIslandIndex).size(); j++) {
				Point secondIsland = islandList.get(secondIslandIndex).get(j);
				int secondX = secondIsland.x;
				int secondY = secondIsland.y;
				if (firstIslandX != secondX && firstIslandY != secondY) {
					continue;
				}

				if (firstIslandX == secondX) {
					if (isPossibleHorizontalBridge(firstIslandX, firstIslandY, secondX, secondY)) {
						if (Math.abs(firstIslandY - secondY) - 1 != 1) {
							twoIslandDistance = Math.min(twoIslandDistance, Math.abs(firstIslandY - secondY) - 1);
						}
					}
				} else if (firstIslandY == secondY) {
					if (isPossibleVerticalBridge(firstIslandX, firstIslandY, secondX, secondY)) {
						if (Math.abs(firstIslandX - secondX) - 1 != 1) {
							twoIslandDistance = Math.min(twoIslandDistance, Math.abs(firstIslandX - secondX) - 1);
						}
					}
				}
			}

			if (minimumDistance > twoIslandDistance) {
				minimumDistance = twoIslandDistance;
			}
		}

		if (minimumDistance == INVALID_VALUE) {
			return 0;
		}

		return minimumDistance;
	}

	private static boolean isPossibleHorizontalBridge(int firstIslandX, int firstIslandY, int secondIslandX,
			int secondIslandY) {
		int index = 0;
		if (firstIslandY - secondIslandY > 0) {
			index = -1;
		} else {
			index = 1;
		}

		while (true) {
			firstIslandY += index;
			if (firstIslandY == secondIslandY) {
				break;
			}
			if (map[firstIslandX][firstIslandY] != SEA) {
				return false;
			}
		}
		return true;
	}

	private static boolean isPossibleVerticalBridge(int firstIslandX, int firstIslandY, int secondIslandX,
			int secondIslandY) {
		int index = 0;
		if (firstIslandX - secondIslandX > 0) {
			index = -1;
		} else {
			index = 1;
		}

		while (true) {
			firstIslandX += index;
			if (firstIslandX == secondIslandX) {
				break;
			}
			if (map[firstIslandX][firstIslandY] != SEA) {
				return false;
			}
		}
		return true;
	}

	private static void printMinimumBridgeLength() {
		int lengthOfBridge = 0;

		boolean isVisitedIsland[] = new boolean[countOfIsland + 1];
		PriorityQueue<IslandInfo> islandInfoPQ = new PriorityQueue<IslandInfo>();
		islandInfoPQ.add(new IslandInfo(1, 0));

		int count = 0;
		while (!islandInfoPQ.isEmpty()) {
			IslandInfo islandInfo = islandInfoPQ.poll();
			int islandNumber = islandInfo.islandNumber;
			int bridgeLength = islandInfo.bridgeLength;

			if (isVisitedIsland[islandNumber]) {
				continue;
			}

			isVisitedIsland[islandNumber] = true;
			count += 1;
			lengthOfBridge += bridgeLength;
			if (count == countOfIsland) {
				break;
			}

			for (int i = 1; i <= countOfIsland; i++) {
				if (isVisitedIsland[i] == true) {
					continue;
				}
				if (distanceOfIsland[islandNumber][i] < 2) {
					continue;
				}

				islandInfoPQ.add(new IslandInfo(i, distanceOfIsland[islandNumber][i]));
			}
		}

		if (lengthOfBridge == 0 || count != countOfIsland) {
			System.out.println("-1");
		} else {
			System.out.println(lengthOfBridge);
		}
	}
}
