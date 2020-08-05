package code.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_2667_단지번호붙이기 {
	private static final int MAXIMUM_LENGTH_OF_MAP = 25;
	private static final char BLANK = '0';
	private static final char HOUSE = '1';
	private static final int COUNT_OF_DIRECTION = 4;
	private static final int MOVE_X_COORDINATE[] = { 1, -1, 0, 0 };
	private static final int MOVE_Y_COORDINATE[] = { 0, 0, 1, -1 };

	private static char map[][] = new char[MAXIMUM_LENGTH_OF_MAP][MAXIMUM_LENGTH_OF_MAP];
	private static boolean isVisitedMap[][] = new boolean[MAXIMUM_LENGTH_OF_MAP][MAXIMUM_LENGTH_OF_MAP];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int lengthOfMap = Integer.parseInt(br.readLine());
		makeMap(br, lengthOfMap);
		ArrayList<Integer> countOfHouseInBlock = checkCountOfBlock(lengthOfMap);

		Collections.sort(countOfHouseInBlock);
		System.out.println(countOfHouseInBlock.size());
		for (int i : countOfHouseInBlock) {
			System.out.println(i);
		}
	}

	private static void makeMap(BufferedReader br, int lengthOfMap) throws IOException {
		for (int i = 0; i < lengthOfMap; i++) {
			String rowData = br.readLine();
			for (int j = 0; j < lengthOfMap; j++) {
				map[i][j] = rowData.charAt(j);
			}
		}
	}

	private static ArrayList<Integer> checkCountOfBlock(int lengthOfMap) {
		ArrayList<Integer> countOfHouseInBlock = new ArrayList<Integer>();

		for (int i = 0; i < lengthOfMap; i++) {
			for (int j = 0; j < lengthOfMap; j++) {
				if (map[i][j] == BLANK) {
					continue;
				}
				if (isVisitedMap[i][j] == true) {
					continue;
				}

				int countOfHouse = getCountOfHouse(lengthOfMap, i, j);
				countOfHouseInBlock.add(countOfHouse);
			}
		}

		return countOfHouseInBlock;
	}

	private static int getCountOfHouse(int lengthOfMap, int i, int j) {
		Queue<int[]> houseQueue = new LinkedList<int[]>();
		houseQueue.add(new int[] { i, j });
		isVisitedMap[i][j] = true;

		int countOfHouse = 0;

		while (!houseQueue.isEmpty()) {
			int houseCoordinate[] = houseQueue.poll();
			int houseXCoordinate = houseCoordinate[0];
			int houseYCoordinate = houseCoordinate[1];
			countOfHouse += 1;

			for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
				int nextHouseXCoordinate = houseXCoordinate + MOVE_X_COORDINATE[direction];
				int nextHouseYCoordinate = houseYCoordinate + MOVE_Y_COORDINATE[direction];

				if (nextHouseXCoordinate < 0 || nextHouseXCoordinate == lengthOfMap || nextHouseYCoordinate < 0
						|| nextHouseYCoordinate == lengthOfMap) {
					continue;
				}
				if (map[nextHouseXCoordinate][nextHouseYCoordinate] == BLANK) {
					continue;
				}
				if (isVisitedMap[nextHouseXCoordinate][nextHouseYCoordinate] == true) {
					continue;
				}

				isVisitedMap[nextHouseXCoordinate][nextHouseYCoordinate] = true;
				houseQueue.add(new int[] { nextHouseXCoordinate, nextHouseYCoordinate });
			}
		}

		return countOfHouse;
	}

}
