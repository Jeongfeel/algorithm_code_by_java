package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_1074_Z {
	private static final int MULTIPLE_NUMBER = 4;
	private static int startRowNumber = 2;
	private static int startColumnNumber = 1;

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int N, r, c;

	public static void main(String[] args) throws IOException {
		inputData();
		List<Integer> rowBinaryNumber = makeBinaryNumber(r);
		List<Integer> columnBinaryNumber = makeBinaryNumber(c);

		printAnswerNumber(rowBinaryNumber, columnBinaryNumber);
	}

	private static void inputData() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
	}

	private static List<Integer> makeBinaryNumber(int number) {
		List<Integer> binaryNumber = new ArrayList<Integer>();

		while (number != 0) {
			binaryNumber.add(number % 2);
			number /= 2;
		}

		return binaryNumber;
	}

	private static void printAnswerNumber(List<Integer> rowBinaryNumber, List<Integer> columnBinaryNumber) {
		int answerNumber = 0;

		for (int number : rowBinaryNumber) {
			answerNumber += startRowNumber * number;
			startRowNumber *= MULTIPLE_NUMBER;
		}

		for (int number : columnBinaryNumber) {
			answerNumber += startColumnNumber * number;
			startColumnNumber *= 4;
		}

		System.out.println(answerNumber);
	}
}
