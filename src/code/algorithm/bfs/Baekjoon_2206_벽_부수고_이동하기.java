package code.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_2206_벽_부수고_이동하기 {
	static class PositionState {
		int x;
		int y;
		int distance;
		int isBrokenWall;

		public PositionState(int x, int y, int distance, int isBrokenWall) {
			super();
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.isBrokenWall = isBrokenWall;
		}
	}

	private static final int BLANK = 0;
	private static final int WALL = 1;

	private static final int COUNT_OF_STATE = 2;
	private static final int NOT_BROKEN_WALL = 0;
	private static final int BROKEN_WALL = 1;

	private static final int COUNT_OF_DIRECTION = 4;
	private static final int MOVE_X_COORDINATE[] = { 1, -1, 0, 0 };
	private static final int MOVE_Y_COORDINATE[] = { 0, 0, 1, -1 };

	private static int N, M;
	private static int gameMap[][];

	private static BufferedReader br;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		inputData();
		printMinimumDistance();
	}

	private static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		gameMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			String mapData = br.readLine();
			for (int j = 0; j < M; j++) {
				gameMap[i][j] = mapData.charAt(j) - '0';
			}
		}
	}

	private static void printMinimumDistance() {
		Queue<PositionState> positionQueue = new LinkedList<PositionState>();
		boolean isVisitedPosition[][][] = new boolean[N][M][COUNT_OF_STATE];
		positionQueue.add(new PositionState(0, 0, 1, NOT_BROKEN_WALL));
		isVisitedPosition[0][0][NOT_BROKEN_WALL] = true;

		while (!positionQueue.isEmpty()) {
			PositionState positionState = positionQueue.poll();
			int x = positionState.x;
			int y = positionState.y;
			int distance = positionState.distance;
			int isBrokenWall = positionState.isBrokenWall;

			if (x == N - 1 && y == M - 1) {
				System.out.println(distance);
				return;
			}

			for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
				int nextX = x + MOVE_X_COORDINATE[direction];
				int nextY = y + MOVE_Y_COORDINATE[direction];
				int nextDistance = distance + 1;

				if (nextX < 0 || nextX == N || nextY < 0 || nextY == M) {
					continue;
				}

				if (gameMap[nextX][nextY] == BLANK) {
					if (isBrokenWall == NOT_BROKEN_WALL) {
						if (isVisitedPosition[nextX][nextY][NOT_BROKEN_WALL] == true) {
							continue;
						}

						isVisitedPosition[nextX][nextY][NOT_BROKEN_WALL] = true;
						positionQueue.add(new PositionState(nextX, nextY, nextDistance, NOT_BROKEN_WALL));
					} else if (isBrokenWall == BROKEN_WALL) {
						if (isVisitedPosition[nextX][nextY][BROKEN_WALL] == true) {
							continue;
						}

						isVisitedPosition[nextX][nextY][BROKEN_WALL] = true;
						positionQueue.add(new PositionState(nextX, nextY, nextDistance, BROKEN_WALL));
					}
				} else if (gameMap[nextX][nextY] == WALL) {
					if (isBrokenWall == NOT_BROKEN_WALL) {
						if (isVisitedPosition[nextX][nextY][BROKEN_WALL] == true) {
							continue;
						}

						isVisitedPosition[nextX][nextY][BROKEN_WALL] = true;
						positionQueue.add(new PositionState(nextX, nextY, nextDistance, BROKEN_WALL));
					} else if (isBrokenWall == BROKEN_WALL) {
						continue;
					}
				}

			}
		}

		System.out.println("-1");
	}
}
