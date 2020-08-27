package code.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1987_알파벳 {
	private static final int COUNT_OF_ALPHABET = 26;
	private static final char INIT_ALPHABET = 'A';

	private static final int COUNT_OF_DIRECTION = 4;
	private static final int MOVE_X_COORDINATE[] = { 1, -1, 0, 0 };
	private static final int MOVE_Y_COORDINATE[] = { 0, 0, 1, -1 };

	private static char board[][];
	private static int R, C;
	private static int maximumMovingCount = 0;
	private static boolean isVisitedAlphabet[] = new boolean[COUNT_OF_ALPHABET];

	public static void main(String[] args) throws IOException {
		inputData();
		findMaximumMovingCount(0, 0, 0);
		System.out.println(maximumMovingCount);
	}

	private static void inputData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];

		for (int i = 0; i < R; i++) {
			String boardData = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = boardData.charAt(j);
			}
		}
	}

	private static void findMaximumMovingCount(int y, int x, int movingCount) {
		char alphabet = board[y][x];
		if (isVisitedAlphabet[alphabet - INIT_ALPHABET] == true) {
			if (maximumMovingCount < movingCount) {
				maximumMovingCount = movingCount;
			}

			return;
		}

		isVisitedAlphabet[alphabet - INIT_ALPHABET] = true;
		for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
			int nextY = y + MOVE_Y_COORDINATE[direction];
			int nextX = x + MOVE_X_COORDINATE[direction];

			if (nextY < 0 || nextY == R || nextX < 0 || nextX == C) {
				continue;
			}

			findMaximumMovingCount(nextY, nextX, movingCount + 1);
		}
		isVisitedAlphabet[alphabet - INIT_ALPHABET] = false;
	}

}
