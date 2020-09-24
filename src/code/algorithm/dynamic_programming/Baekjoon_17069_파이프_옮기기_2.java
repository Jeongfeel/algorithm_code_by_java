package code.algorithm.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_17069_파이프_옮기기_2 {
	private static final int BLANK = 0;
	private static final int WALL = 1;

	private static final int COUNT_OF_PIPE = 3;
	private static final int WIDTH_PIPE = 0;
	private static final int HEIGHT_PIPE = 1;
	private static final int DIAGONAL_PIPE = 2;

	private static int lengthOfSquareMap;
	private static int squareMap[][];
	private static long countOfPossiblePipe[][][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		inputData();
		makePossiblePipeStructure();

		System.out.println(countOfPossiblePipe[lengthOfSquareMap - 1][lengthOfSquareMap - 1][WIDTH_PIPE]
				+ countOfPossiblePipe[lengthOfSquareMap - 1][lengthOfSquareMap - 1][HEIGHT_PIPE]
				+ countOfPossiblePipe[lengthOfSquareMap - 1][lengthOfSquareMap - 1][DIAGONAL_PIPE]);
	}

	private static void inputData() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		lengthOfSquareMap = Integer.parseInt(br.readLine());
		squareMap = new int[lengthOfSquareMap][lengthOfSquareMap];
		countOfPossiblePipe = new long[lengthOfSquareMap][lengthOfSquareMap][COUNT_OF_PIPE];

		StringTokenizer st;
		for (int i = 0; i < lengthOfSquareMap; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < lengthOfSquareMap; j++) {
				squareMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void makePossiblePipeStructure() {
		countOfPossiblePipe[0][0][WIDTH_PIPE] = 1;
		countOfPossiblePipe[0][1][WIDTH_PIPE] = 1;

		for (int i = 0; i < lengthOfSquareMap; i++) {
			for (int j = 2; j < lengthOfSquareMap; j++) {
				if (squareMap[i][j] == WALL) {
					continue;
				}

				checkWidthPipe(i, j);
				checkHeightPipe(i, j);
				checkDiagonalPipe(i, j);
			}
		}
	}

	private static void checkWidthPipe(int x, int y) {
		int previousX = x;
		int previousY = y - 1;

		if (isValidRange(previousX, previousY) == false) {
			return;
		}

		countOfPossiblePipe[x][y][WIDTH_PIPE] += countOfPossiblePipe[previousX][previousY][WIDTH_PIPE]
				+ countOfPossiblePipe[previousX][previousY][DIAGONAL_PIPE];
	}

	private static void checkHeightPipe(int x, int y) {
		int previousX = x - 1;
		int previousY = y;

		if (isValidRange(previousX, previousY) == false) {
			return;
		}

		countOfPossiblePipe[x][y][HEIGHT_PIPE] += countOfPossiblePipe[previousX][previousY][HEIGHT_PIPE]
				+ countOfPossiblePipe[previousX][previousY][DIAGONAL_PIPE];
	}

	private static void checkDiagonalPipe(int x, int y) {
		int previousX = x - 1;
		int previousY = y - 1;

		if (isValidRange(previousX, previousY) == false) {
			return;
		}

		if (squareMap[previousX + 1][previousY] == WALL || squareMap[previousX][previousY + 1] == WALL) {
			return;
		}
		countOfPossiblePipe[x][y][DIAGONAL_PIPE] += countOfPossiblePipe[previousX][previousY][DIAGONAL_PIPE]
				+ countOfPossiblePipe[previousX][previousY][WIDTH_PIPE]
				+ countOfPossiblePipe[previousX][previousY][HEIGHT_PIPE];
	}

	private static boolean isValidRange(int previousX, int previousY) {
		if (previousX < 0 || previousY < 0) {
			return false;
		}
		if (squareMap[previousX][previousY] == WALL) {
			return false;
		}

		return true;
	}
}
