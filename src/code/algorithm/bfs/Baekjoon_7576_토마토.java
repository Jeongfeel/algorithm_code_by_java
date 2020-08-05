package code.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_7576_토마토 {
	private static final int MAXIMUM_TOMATO_BOX_LENGTH = 1000;
	private static final int RIPEN_TOMATO = 1;
	private static final int UNRIPEN_TOMATO = 0;
	private static final int BLANK = -1;
	private static final int COUNT_OF_DIRECTION = 4;
	private static final int MOVE_X_COORDINATE[] = { 1, -1, 0, 0 };
	private static final int MOVE_Y_COORDINATE[] = { 0, 0, 1, -1 };

	private static int ripenTomatoDate[][] = new int[MAXIMUM_TOMATO_BOX_LENGTH][MAXIMUM_TOMATO_BOX_LENGTH];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int xLength = Integer.parseInt(st.nextToken());
		int yLength = Integer.parseInt(st.nextToken());

		makeTomatoBox(br, xLength, yLength);
		System.out.println(getAllTomatoRipenDate(xLength, yLength));
	}

	private static void makeTomatoBox(BufferedReader br, int xLength, int yLength) throws IOException {
		for (int y = 0; y < yLength; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x = 0; x < xLength; x++) {
				int tomatoState = Integer.parseInt(st.nextToken());
				ripenTomatoDate[y][x] = tomatoState;
			}
		}
	}

	private static int getAllTomatoRipenDate(int xLength, int yLength) {
		Queue<int[]> RipenTomatoQueue = new LinkedList<int[]>();
		for (int y = 0; y < yLength; y++) {
			for (int x = 0; x < xLength; x++) {
				if (ripenTomatoDate[y][x] == RIPEN_TOMATO) {
					RipenTomatoQueue.add(new int[] { y, x });
				}
			}
		}

		while (!RipenTomatoQueue.isEmpty()) {
			int tomatoCoordinate[] = RipenTomatoQueue.poll();
			int tomatoYCoordinate = tomatoCoordinate[0];
			int tomatoXCoordinate = tomatoCoordinate[1];

			for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
				int nextTomatoYCoordinate = tomatoYCoordinate + MOVE_Y_COORDINATE[direction];
				int nextTomatoXCoordinate = tomatoXCoordinate + MOVE_X_COORDINATE[direction];

				if (nextTomatoXCoordinate < 0 || nextTomatoXCoordinate == xLength || nextTomatoYCoordinate < 0
						|| nextTomatoYCoordinate == yLength) {
					continue;
				}

				if (ripenTomatoDate[nextTomatoYCoordinate][nextTomatoXCoordinate] != UNRIPEN_TOMATO) {
					continue;
				}
				ripenTomatoDate[nextTomatoYCoordinate][nextTomatoXCoordinate] = ripenTomatoDate[tomatoYCoordinate][tomatoXCoordinate]
						+ 1;
				RipenTomatoQueue.add(new int[] { nextTomatoYCoordinate, nextTomatoXCoordinate });
			}
		}

		int allTomatoRipenDate = 0;
		for (int y = 0; y < yLength; y++) {
			for (int x = 0; x < xLength; x++) {
				if (ripenTomatoDate[y][x] == 0) {
					return -1;
				}

				if (allTomatoRipenDate < ripenTomatoDate[y][x]) {
					allTomatoRipenDate = ripenTomatoDate[y][x];
				}
			}
		}

		return allTomatoRipenDate - 1;
	}
}
