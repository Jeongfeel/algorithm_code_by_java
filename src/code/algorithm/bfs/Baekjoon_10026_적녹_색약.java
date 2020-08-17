package code.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_10026_적녹_색약 {
	private static final int MAXIMUM_LENGTH_OF_GRID = 100;
	private static final char RED = 'R';
	private static final char GREEN = 'G';
	private static final char BLUE = 'B';
	private static final int COUNT_OF_DIRECTION = 4;
	private static final int MOVE_X_COORDINATE[] = { 1, -1, 0, 0 };
	private static final int MOVE_Y_COORDINATE[] = { 0, 0, 1, -1 };

	private static char grid[][] = new char[MAXIMUM_LENGTH_OF_GRID][MAXIMUM_LENGTH_OF_GRID];
	private static boolean isVisitedGrid[][] = new boolean[MAXIMUM_LENGTH_OF_GRID][MAXIMUM_LENGTH_OF_GRID];
	private static int lengthOfGrid;

	public static void main(String[] args) throws NumberFormatException, IOException {
		inputData();

		int countOfColorByNormal = getCountOfColorByNormal();
		int countOfColorByColorWeakness = getCountOfColorByColorWeakness();

		System.out.println(countOfColorByNormal + " " + countOfColorByColorWeakness);
	}

	private static void inputData() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		lengthOfGrid = Integer.parseInt(br.readLine());
		for (int i = 0; i < lengthOfGrid; i++) {
			String colorData = br.readLine();
			for (int j = 0; j < lengthOfGrid; j++) {
				grid[i][j] = colorData.charAt(j);
			}
		}
	}

	private static int getCountOfColorByNormal() {
		initIsVisitedGrid();

		int areaCount = 0;
		for (int i = 0; i < lengthOfGrid; i++) {
			for (int j = 0; j < lengthOfGrid; j++) {
				if (isVisitedGrid[i][j] == true) {
					continue;
				}

				areaCount += 1;
				checkAreaByNormal(i, j);
			}
		}
		return areaCount;
	}

	private static void checkAreaByNormal(int i, int j) {
		Queue<int[]> gridPositionQueue = new LinkedList<int[]>();
		gridPositionQueue.add(new int[] { i, j });
		isVisitedGrid[i][j] = true;

		char color = grid[i][j];

		while (!gridPositionQueue.isEmpty()) {
			int gridCoordinate[] = gridPositionQueue.poll();
			int xCoordinate = gridCoordinate[0];
			int yCoordinate = gridCoordinate[1];

			for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
				int nextXCoordinate = xCoordinate + MOVE_X_COORDINATE[direction];
				int nextYCoordinate = yCoordinate + MOVE_Y_COORDINATE[direction];

				if (nextXCoordinate < 0 || nextXCoordinate == lengthOfGrid || nextYCoordinate < 0
						|| nextYCoordinate == lengthOfGrid) {
					continue;
				}

				if (color != grid[nextXCoordinate][nextYCoordinate]) {
					continue;
				}

				if (isVisitedGrid[nextXCoordinate][nextYCoordinate] == true) {
					continue;
				}

				gridPositionQueue.add(new int[] { nextXCoordinate, nextYCoordinate });
				isVisitedGrid[nextXCoordinate][nextYCoordinate] = true;
			}
		}
	}

	private static int getCountOfColorByColorWeakness() {
		initIsVisitedGrid();

		int areaCount = 0;
		for (int i = 0; i < lengthOfGrid; i++) {
			for (int j = 0; j < lengthOfGrid; j++) {
				if (isVisitedGrid[i][j] == true) {
					continue;
				}

				areaCount += 1;
				checkAreaByColorWeakness(i, j);
			}
		}
		return areaCount;
	}

	private static void checkAreaByColorWeakness(int i, int j) {
		Queue<int[]> gridPositionQueue = new LinkedList<int[]>();
		gridPositionQueue.add(new int[] { i, j });
		isVisitedGrid[i][j] = true;

		char color = grid[i][j];

		while (!gridPositionQueue.isEmpty()) {
			int gridCoordinate[] = gridPositionQueue.poll();
			int xCoordinate = gridCoordinate[0];
			int yCoordinate = gridCoordinate[1];

			for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
				int nextXCoordinate = xCoordinate + MOVE_X_COORDINATE[direction];
				int nextYCoordinate = yCoordinate + MOVE_Y_COORDINATE[direction];

				if (nextXCoordinate < 0 || nextXCoordinate == lengthOfGrid || nextYCoordinate < 0
						|| nextYCoordinate == lengthOfGrid) {
					continue;
				}

				if (color == RED || color == GREEN) {
					if (grid[nextXCoordinate][nextYCoordinate] == BLUE) {
						continue;
					}
				} else if (color == BLUE) {
					if (color != grid[nextXCoordinate][nextYCoordinate]) {
						continue;
					}
				}

				if (isVisitedGrid[nextXCoordinate][nextYCoordinate] == true) {
					continue;
				}

				gridPositionQueue.add(new int[] { nextXCoordinate, nextYCoordinate });
				isVisitedGrid[nextXCoordinate][nextYCoordinate] = true;
			}
		}

	}

	private static void initIsVisitedGrid() {
		for (int i = 0; i < lengthOfGrid; i++) {
			for (int j = 0; j < lengthOfGrid; j++) {
				isVisitedGrid[i][j] = false;
			}
		}
	}

}
