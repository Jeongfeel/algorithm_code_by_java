package code.algorithm.divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_1992_쿼드트리 {
	private static int blackAndWhiteVideo[][];
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		inputData();
		printCompressionResult(0, 0, N);

	}

	private static void inputData() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		blackAndWhiteVideo = new int[N][N];

		for (int i = 0; i < N; i++) {
			String videoData = br.readLine();
			for (int j = 0; j < N; j++) {
				blackAndWhiteVideo[i][j] = videoData.charAt(j) - '0';
			}
		}
	}

	private static void printCompressionResult(int startY, int startX, int squareNumber) {
		int startNumber = blackAndWhiteVideo[startY][startX];
		boolean isCompressed = true;
		for (int i = startY; i < startY + squareNumber; i++) {
			for (int j = startX; j < startX + squareNumber; j++) {
				if (blackAndWhiteVideo[i][j] == startNumber) {
					continue;
				}

				isCompressed = false;
				break;
			}

			if (isCompressed == false) {
				break;
			}
		}

		if (isCompressed == true) {
			System.out.print(startNumber);
		} else if (isCompressed == false) {
			System.out.print("(");
			squareNumber /= 2;
			printCompressionResult(startY, startX, squareNumber);
			printCompressionResult(startY, startX + squareNumber, squareNumber);
			printCompressionResult(startY + squareNumber, startX, squareNumber);
			printCompressionResult(startY + squareNumber, startX + squareNumber, squareNumber);
			System.out.print(")");
		}
	}

}
