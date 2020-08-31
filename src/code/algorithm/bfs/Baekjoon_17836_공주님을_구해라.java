package code.algorithm.bfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_17836_공주님을_구해라 {
	private static final int BLANK = 0;
	private static final int WALL = 1;
	private static final int GRAM = 2;

	private static final int COUNT_OF_DIRECTION = 4;
	private static final int MOVE_X_POSITION[] = { 1, -1, 0, 0 };
	private static final int MOVE_Y_POSITION[] = { 0, 0, 1, -1 };
	private static final int MAXIMUM_VALUE = Integer.MAX_VALUE;

	private static int N, M, T;
	private static int castle[][];
	private static int visitedTime[][];
	private static int gramXPosition, gramYPosition;

	public static void main(String[] args) throws IOException {
		inputData();
		printMinimumTime();
	}

	private static void inputData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		castle = new int[N][M];
		visitedTime = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				castle[i][j] = Integer.parseInt(st.nextToken());
				visitedTime[i][j] = MAXIMUM_VALUE;
				if (castle[i][j] == GRAM) {
					gramXPosition = i;
					gramYPosition = j;
				}

			}
		}
	}

	private static void printMinimumTime() {
		Queue<Point> positionQueue = new LinkedList<Point>();
		positionQueue.add(new Point(0, 0));
		visitedTime[0][0] = 0;

		while (!positionQueue.isEmpty()) {
			Point position = positionQueue.poll();
			int xPosition = position.x;
			int yPosition = position.y;

			for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
				int nextXPosition = xPosition + MOVE_X_POSITION[direction];
				int nextYPosition = yPosition + MOVE_Y_POSITION[direction];

				if (nextXPosition < 0 || nextXPosition == N || nextYPosition < 0 || nextYPosition == M) {
					continue;
				}
				if (castle[nextXPosition][nextYPosition] == WALL) {
					continue;
				}
				if (visitedTime[nextXPosition][nextYPosition] != MAXIMUM_VALUE) {
					continue;
				}

				visitedTime[nextXPosition][nextYPosition] = visitedTime[xPosition][yPosition] + 1;
				positionQueue.add(new Point(nextXPosition, nextYPosition));
			}
		}

		int usedGramTime = visitedTime[gramXPosition][gramYPosition];
		if (usedGramTime != MAXIMUM_VALUE) {
			usedGramTime = visitedTime[gramXPosition][gramYPosition] + (N - gramXPosition - 1)
					+ (M - gramYPosition - 1);
		}

		int minimumTime = Math.min(usedGramTime, visitedTime[N - 1][M - 1]);
		if (minimumTime <= T) {
			System.out.println(minimumTime);
		} else {
			System.out.println("Fail");
		}
	}
}
