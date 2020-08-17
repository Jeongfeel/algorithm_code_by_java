package code.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_11403_경로_찾기 {
	private static final int MAXIMUM_LENGTH_OF_GRAPH = 100;
	private static final int LINKED = 1;
	private static final int UNLINKED = 0;

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int adjacentMatrix[][] = new int[MAXIMUM_LENGTH_OF_GRAPH][MAXIMUM_LENGTH_OF_GRAPH];
	private static int resultMatrix[][] = new int[MAXIMUM_LENGTH_OF_GRAPH][MAXIMUM_LENGTH_OF_GRAPH];
	private static int graphLength;

	public static void main(String[] args) throws NumberFormatException, IOException {
		makeAdjacentMatrix();
		makeResultMatrix();

		for (int i = 0; i < graphLength; i++) {
			for (int j = 0; j < graphLength; j++) {
				System.out.print(resultMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void makeAdjacentMatrix() throws NumberFormatException, IOException {
		graphLength = Integer.parseInt(br.readLine());
		for (int i = 0; i < graphLength; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < graphLength; j++) {
				adjacentMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void makeResultMatrix() {
		for (int startIndex = 0; startIndex < graphLength; startIndex++) {
			makeResultMatrix(startIndex, startIndex);
		}
	}

	private static void makeResultMatrix(int startIndex, int nextIndex) {
		for (int index = 0; index < graphLength; index++) {
			if (adjacentMatrix[nextIndex][index] == UNLINKED) {
				continue;
			}

			if (resultMatrix[startIndex][index] == LINKED) {
				continue;
			}

			resultMatrix[startIndex][index] = LINKED;
			makeResultMatrix(startIndex, index);
		}
	}

}
