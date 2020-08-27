package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_3109_빵집 {
	private static final char BLANK = '.';
	private static final char RESTAURANT = 'x';
	private static final char FIRST_PIPE = '/';
	private static final char SECOND_PIPE = '-';
	private static final char THIRD_PIPE = '\\'; // 최종 결과 출력시 어떻게 연결해야 최적인지 보려고 파이프 나눠서 추가.

	private static final int MOVE_FIRST_PIPE[] = { -1, 1 };
	private static final int MOVE_SECOND_PIPE[] = { 0, 1 };
	private static final int MOVE_THIRD_PIPE[] = { 1, 1 };

	private static int R, C;
	private static char city[][];
	private static boolean isConstructed = false;

	public static void main(String[] args) throws IOException {
		inputCityData();
		printMaximumCountOfPipe();
	}

	private static void inputCityData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		city = new char[R][C];

		for (int i = 0; i < R; i++) {
			String cityData = br.readLine();
			for (int j = 0; j < C; j++) {
				city[i][j] = cityData.charAt(j);
			}
		}
	}

	private static void printMaximumCountOfPipe() {
		int countOfPipe = 0;
		for (int i = 0; i < R; i++) {
			isConstructed = false;
			constructPipe(i, 0);
		}

		for (int i = 0; i < R; i++) {
			if (city[i][C - 1] != BLANK) {
				countOfPipe += 1;
			}
		}

		System.out.println(countOfPipe);
	}

	private static void constructPipe(int y, int x) {
		if (x == C) {
			isConstructed = true;
			return;
		}

		if (isConstructedFirstPipe(y, x) && !isConstructed) {
			city[y][x] = FIRST_PIPE;
			constructPipe(y + MOVE_FIRST_PIPE[0], x + MOVE_FIRST_PIPE[1]);
		} else if (!isConstructed) {
			city[y][x] = BLANK;
		}

		if (isConstructedSecondPipe(y, x) && !isConstructed) {
			city[y][x] = SECOND_PIPE;
			constructPipe(y + MOVE_SECOND_PIPE[0], x + MOVE_SECOND_PIPE[1]);
		} else if (!isConstructed) {
			city[y][x] = BLANK;
		}

		if (isConstructedThirdPipe(y, x) && !isConstructed) {
			city[y][x] = THIRD_PIPE;
			constructPipe(y + MOVE_THIRD_PIPE[0], x + MOVE_THIRD_PIPE[1]);
		} else if (!isConstructed) {
			city[y][x] = BLANK;
		}
	}

	private static boolean isConstructedFirstPipe(int y, int x) {
		int nextY = y + MOVE_FIRST_PIPE[0];
		int nextX = x + MOVE_FIRST_PIPE[1];

		if (nextX == C) {
			return true;
		}
		if (nextY < 0 || nextX > C) {
			return false;
		}
		if (city[nextY][nextX] != BLANK) {
			return false;
		}

		return true;
	}

	private static boolean isConstructedSecondPipe(int y, int x) {
		int nextY = y + MOVE_SECOND_PIPE[0];
		int nextX = x + MOVE_SECOND_PIPE[1];

		if (nextX == C) {
			return true;
		}
		if (city[nextY][nextX] != BLANK) {
			return false;
		}

		return true;
	}

	private static boolean isConstructedThirdPipe(int y, int x) {
		int nextY = y + MOVE_THIRD_PIPE[0];
		int nextX = x + MOVE_THIRD_PIPE[1];

		if (nextX == C) {
			return true;
		}
		if (nextY == R) {
			return false;
		}
		if (city[nextY][nextX] != BLANK) {
			return false;
		}

		return true;
	}

}
