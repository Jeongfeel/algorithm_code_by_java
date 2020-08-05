package code.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_4963_섬의_개수 {
	private static final int MAXIMUM_LENGTH_OF_MAP = 50;
	private static final int LAND = 1;
	private static final int SEA = 0;
	private static final int COUNT_OF_DIRECTION = 8;
	private static final int MOVE_X_COORDINATE[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	private static final int MOVE_Y_COORDINATE[] = { 1, 1, 0, -1, -1, -1, 0, 1 };

	private static int map[][] = new int[MAXIMUM_LENGTH_OF_MAP][MAXIMUM_LENGTH_OF_MAP];
	private static boolean isVisitedMap[][] = new boolean[MAXIMUM_LENGTH_OF_MAP][MAXIMUM_LENGTH_OF_MAP];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());

			if (isInValidWidthAndHeight(width, height)) {
				break;
			}

			initIsVisitedIsland(width, height);
			makeIsland(br, width, height);

			int countOfIsland = getCountOfIsland(width, height);
			System.out.println(countOfIsland);
		}
	}

	private static boolean isInValidWidthAndHeight(int width, int height) {
		if (width == 0 && height == 0) {
			return true;
		}

		return false;
	}

	private static void initIsVisitedIsland(int width, int height) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				isVisitedMap[y][x] = false;
			}
		}
	}

	private static void makeIsland(BufferedReader br, int width, int height) throws IOException {
		for (int y = 0; y < height; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x = 0; x < width; x++) {
				int mapState = Integer.parseInt(st.nextToken());
				map[y][x] = mapState;
			}
		}
	}

	private static int getCountOfIsland(int width, int height) {
		int countOfIsland = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (map[y][x] == SEA) {
					continue;
				}
				if (isVisitedMap[y][x] == true) {
					continue;
				}

				countOfIsland += 1;
				checkAdjacentland(height, width, y, x);
			}
		}

		return countOfIsland;
	}

	private static void checkAdjacentland(int height, int width, int y, int x) {
		Queue<int[]> landQueue = new LinkedList<int[]>();
		landQueue.add(new int[] { y, x });
		isVisitedMap[y][x] = true;

		while (!landQueue.isEmpty()) {
			int landCoordinate[] = landQueue.poll();
			int landYCoordinate = landCoordinate[0];
			int landXCoordinate = landCoordinate[1];

			for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
				int nextLandYCoordinate = landYCoordinate + MOVE_Y_COORDINATE[direction];
				int nextLandXCoordinate = landXCoordinate + MOVE_X_COORDINATE[direction];

				if (nextLandYCoordinate < 0 || nextLandYCoordinate == height || nextLandXCoordinate < 0
						|| nextLandXCoordinate == width) {
					continue;
				}
				if (map[nextLandYCoordinate][nextLandXCoordinate] == SEA) {
					continue;
				}
				if (isVisitedMap[nextLandYCoordinate][nextLandXCoordinate] == true) {
					continue;
				}

				landQueue.add(new int[] { nextLandYCoordinate, nextLandXCoordinate });
				isVisitedMap[nextLandYCoordinate][nextLandXCoordinate] = true;
			}
		}
	}

}