package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_1463_1로_만들기 {
	private static final int MAXIMUM_INPUT_NUMBER = 1000000 + 1;
	private static int countOfOperation[] = new int[MAXIMUM_INPUT_NUMBER];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(br.readLine());

		Queue<Integer> numberQueue = new LinkedList<Integer>();
		numberQueue.add(number);
		while (!numberQueue.isEmpty()) {
			number = numberQueue.poll();

			if (number == 1) {
				break;
			}

			if (number % 3 == 0) {
				if (countOfOperation[number / 3] == 0) {
					countOfOperation[number / 3] = countOfOperation[number] + 1;
					numberQueue.add(number / 3);
				}
			}
			if (number % 2 == 0) {
				if (countOfOperation[number / 2] == 0) {
					countOfOperation[number / 2] = countOfOperation[number] + 1;
					numberQueue.add(number / 2);
				}
			}
			if (number - 1 > 0) {
				if (countOfOperation[number - 1] == 0) {
					countOfOperation[number - 1] = countOfOperation[number] + 1;
					numberQueue.add(number - 1);
				}
			}
		}

		System.out.println(countOfOperation[1]);
	}
}
