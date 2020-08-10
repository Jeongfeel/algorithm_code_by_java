package code.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_2468_안전_영역 {
	private static final int COUNT_OF_DIRECTION = 4;
	private static final int MOVE_X_COORDINATE[] = { 1, -1, 0, 0 };
	private static final int MOVE_Y_COORDINATE[] = { 0, 0, 1, -1 };

	private static int zone[][];
	private static boolean isSinkedZone[][];
	private static int sizeOfZone;
	private static int maximumHeight = 0;
	private static int maximumCountOfSafetyZone = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sizeOfZone = Integer.parseInt(br.readLine());
		makeZone(br);

		for (int height = 0; height < maximumHeight; height++) {
			checkMaximumCountOfSafetyZone(height);
		}

		System.out.println(maximumCountOfSafetyZone);
	}

	private static void makeZone(BufferedReader br) throws IOException {
		zone = new int[sizeOfZone][sizeOfZone];
		isSinkedZone = new boolean[sizeOfZone][sizeOfZone];

		for (int i = 0; i < sizeOfZone; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < sizeOfZone; j++) {
				int height = Integer.parseInt(st.nextToken());
				zone[i][j] = height;
				if (maximumHeight < height) {
					maximumHeight = height;
				}
			}
		}
	}

	private static void checkMaximumCountOfSafetyZone(int height) {
		initIsSinkedZone(height);

		int countOfSafetyZone = 0;
		for (int y = 0; y < sizeOfZone; y++) {
			for (int x = 0; x < sizeOfZone; x++) {
				if (isSinkedZone[y][x] == true) {
					continue;
				}

				countOfSafetyZone += 1;
				checkSafetyZone(y, x, height);
			}
		}

		if (maximumCountOfSafetyZone < countOfSafetyZone) {
			maximumCountOfSafetyZone = countOfSafetyZone;
		}
	}

	private static void initIsSinkedZone(int height) {
		for (int y = 0; y < sizeOfZone; y++) {
			for (int x = 0; x < sizeOfZone; x++) {
				if (zone[y][x] > height) {
					isSinkedZone[y][x] = false;
					continue;
				}

				isSinkedZone[y][x] = true;
			}
		}
	}

	private static void checkSafetyZone(int y, int x, int height) {
		Queue<int[]> coordinateQueue = new LinkedList<int[]>();
		coordinateQueue.add(new int[] { y, x });
		isSinkedZone[y][x] = true;

		while (!coordinateQueue.isEmpty()) {
			int coordinate[] = coordinateQueue.poll();
			int yCoordinate = coordinate[0];
			int xCoordinate = coordinate[1];

			for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
				int nextYCoordinate = yCoordinate + MOVE_Y_COORDINATE[direction];
				int nextXCoordinate = xCoordinate + MOVE_X_COORDINATE[direction];

				if (nextYCoordinate < 0 || nextYCoordinate == sizeOfZone || nextXCoordinate < 0
						|| nextXCoordinate == sizeOfZone) {
					continue;
				}

				if (isSinkedZone[nextYCoordinate][nextXCoordinate] == true) {
					continue;
				}

				isSinkedZone[nextYCoordinate][nextXCoordinate] = true;
				coordinateQueue.add(new int[] { nextYCoordinate, nextXCoordinate });
			}
		}

	}

}
