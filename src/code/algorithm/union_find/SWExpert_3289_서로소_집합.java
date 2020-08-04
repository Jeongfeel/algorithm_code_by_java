package code.algorithm.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWExpert_3289_서로소_집합 {
	private static final int MAXIMUM_NUMBER = 1000000 + 1;
	private static final int UNION_OPERATION = 0;
	private static final int CONFIRM_OPERATION = 1;

	private static int parentNumber[] = new int[MAXIMUM_NUMBER];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testcase; tc++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int countOfSet = Integer.parseInt(st1.nextToken());
			int countOfOperation = Integer.parseInt(st1.nextToken());

			initParentNumber(countOfSet);
			StringBuilder answer = getAnswer(br, countOfOperation);

			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void initParentNumber(int countOfSet) {
		for (int count = 1; count <= countOfSet; count++) {
			parentNumber[count] = count;
		}
	}

	private static StringBuilder getAnswer(BufferedReader br, int countOfOperation) throws IOException {
		StringBuilder answer = new StringBuilder();

		for (int count = 0; count < countOfOperation; count++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int operation = Integer.parseInt(st2.nextToken());
			int firstNumber = Integer.parseInt(st2.nextToken());
			int secondNumber = Integer.parseInt(st2.nextToken());

			if (operation == UNION_OPERATION) {
				unionOperation(firstNumber, secondNumber);
			} else if (operation == CONFIRM_OPERATION) {
				answer.append(confirmOperation(firstNumber, secondNumber));
			}
		}
		return answer;
	}

	private static void unionOperation(int firstNumber, int secondNumber) {
		int firstNumberParent = findParent(firstNumber);
		int secondNumberParent = findParent(secondNumber);

		int tmpNumber = 0;
		if (firstNumberParent < secondNumberParent) {
			tmpNumber = firstNumberParent;
			firstNumberParent = secondNumberParent;
			secondNumberParent = tmpNumber;
		}

		parentNumber[secondNumberParent] = firstNumberParent;
	}

	private static int findParent(int number) {
		Queue<Integer> numberQueue = new LinkedList<Integer>();
		while (number != parentNumber[number]) {
			numberQueue.add(number);
			number = parentNumber[number];
		}

		while (!numberQueue.isEmpty()) {
			parentNumber[numberQueue.poll()] = number;
		}

		return number;
	}

	private static String confirmOperation(int firstNumber, int secondNumber) {
		int firstNumberParent = findParent(firstNumber);
		int secondNumberParent = findParent(secondNumber);

		if (firstNumberParent == secondNumberParent) {
			return "1";
		} else {
			return "0";
		}
	}

}