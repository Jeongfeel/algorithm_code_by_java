package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_17406_배열_돌리기_4 {
	static class RotationArray {
		int r;
		int c;
		int s;

		public RotationArray(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	private static int array[][];
	private static int N, M, K;
	private static List<RotationArray> rotationArrayList = new ArrayList<RotationArray>();
	private static RotationArray rotationArraySequence[];
	private static boolean isVistedSequence[];
	private static int countOfRotationSequence;
	private static int minimumRowSum = Integer.MAX_VALUE;

	private static BufferedReader br;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		inputData();
		assignRotationSequence(0);

		System.out.println(minimumRowSum);
	}

	private static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		array = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			rotationArrayList.add(new RotationArray(r, c, s));
		}

		countOfRotationSequence = rotationArrayList.size();
		rotationArraySequence = new RotationArray[countOfRotationSequence];
		isVistedSequence = new boolean[countOfRotationSequence];
	}

	private static void assignRotationSequence(int countOfSequence) {
		if (countOfSequence == countOfRotationSequence) {
			int rotationArray[][] = makeRotationArray();

			for (int i = 1; i <= N; i++) {
				int rowSum = 0;
				for (int j = 1; j <= M; j++) {
					rowSum += rotationArray[i][j];
				}

				if (rowSum < minimumRowSum) {
					minimumRowSum = rowSum;
				}
			}

			return;
		}

		for (int i = 0; i < rotationArrayList.size(); i++) {
			if (isVistedSequence[i] == true) {
				continue;
			}

			isVistedSequence[i] = true;
			rotationArraySequence[countOfSequence] = rotationArrayList.get(i);
			assignRotationSequence(countOfSequence + 1);
			isVistedSequence[i] = false;
		}
	}

	private static int[][] makeRotationArray() {
		int rotationArray[][] = new int[N + 1][M + 1];
		int changedArray[][] = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				rotationArray[i][j] = array[i][j];
				changedArray[i][j] = array[i][j];
			}
		}

		for (int sequence = 0; sequence < countOfRotationSequence; sequence++) {
			rotatedRotationArray(rotationArray, changedArray, sequence);
		}

		return rotationArray;
	}

	private static void rotatedRotationArray(int rotationArray[][], int changedArray[][], int sequence) {
		int r = rotationArraySequence[sequence].r;
		int c = rotationArraySequence[sequence].c;
		int s = rotationArraySequence[sequence].s;

		int rotationIndex = 1;
		while (rotationIndex <= s) {
			int minimumRow = r - rotationIndex;
			int minimumColumn = c - rotationIndex;
			int maximumRow = r + rotationIndex;
			int maximumColumn = c + rotationIndex;

			rotatedRight(minimumRow, minimumColumn, maximumColumn, rotationArray, changedArray);
			rotatedDown(minimumRow, maximumColumn, maximumRow, rotationArray, changedArray);
			rotatedLeft(maximumRow, maximumColumn, minimumColumn, rotationArray, changedArray);
			rotatedUp(maximumRow, minimumColumn, minimumRow, rotationArray, changedArray);

			rotationIndex += 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				changedArray[i][j] = rotationArray[i][j];
			}
		}
	}

	private static void rotatedRight(int minimumRow, int minimumColumn, int maximumColumn, int[][] rotationArray,
			int[][] changedArray) {
		int startRow = minimumRow;
		int startColumn = minimumColumn;

		int previousNumber = changedArray[startRow][startColumn];
		int nextNumber = changedArray[startRow][startColumn + 1];
		while (true) {
			rotationArray[startRow][++startColumn] = previousNumber;
			if (startColumn == maximumColumn) {
				break;
			}
			previousNumber = nextNumber;
			nextNumber = rotationArray[startRow][startColumn + 1];
		}
	}

	private static void rotatedDown(int minimumRow, int maximumColumn, int maximumRow, int[][] rotationArray,
			int[][] changedArray) {
		int startRow = minimumRow;
		int startColumn = maximumColumn;

		int previousNumber = changedArray[startRow][startColumn];
		int nextNumber = changedArray[startRow + 1][startColumn];

		while (true) {
			rotationArray[++startRow][startColumn] = previousNumber;
			if (startRow == maximumRow) {
				break;
			}
			previousNumber = nextNumber;
			nextNumber = rotationArray[startRow + 1][startColumn];
		}
	}

	private static void rotatedLeft(int maximumRow, int maximumColumn, int minimumColumn, int[][] rotationArray,
			int[][] changedArray) {
		int startRow = maximumRow;
		int startColumn = maximumColumn;

		int previousNumber = changedArray[startRow][startColumn];
		int nextNumber = changedArray[startRow][startColumn - 1];
		while (true) {
			rotationArray[startRow][--startColumn] = previousNumber;
			if (startColumn == minimumColumn) {
				break;
			}
			previousNumber = nextNumber;
			nextNumber = rotationArray[startRow][startColumn - 1];
		}

	}

	private static void rotatedUp(int maximumRow, int minimumColumn, int minimumRow, int[][] rotationArray,
			int[][] changedArray) {
		int startRow = maximumRow;
		int startColumn = minimumColumn;

		int previousNumber = changedArray[startRow][startColumn];
		int nextNumber = changedArray[startRow - 1][startColumn];
		while (true) {
			rotationArray[--startRow][startColumn] = previousNumber;
			if (startRow == minimumRow) {
				break;
			}
			previousNumber = nextNumber;
			nextNumber = rotationArray[startRow - 1][startColumn];
		}
	}
}
