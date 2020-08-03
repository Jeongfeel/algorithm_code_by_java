package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWExpert_1223_계산기2 {
	private static final int TEST_CASE = 10;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= TEST_CASE; tc++) {
			int stringLength = Integer.parseInt(br.readLine());
			String calculationString = br.readLine();

			Stack<Integer> numberStack = new Stack<>();
			for (int index = 0; index < stringLength; index++) {
				char character = calculationString.charAt(index);

				if (character == '+') {
					continue;
				} else if (character == '*') {
					int firstNumber = numberStack.pop();
					int secondNumber = calculationString.charAt(++index) - '0';

					numberStack.add(firstNumber * secondNumber);
				} else {
					numberStack.add(character - '0');
				}
			}

			int answer = 0;
			while (!numberStack.isEmpty()) {
				answer += numberStack.pop();
			}

			System.out.println("#" + tc + " " + answer);
		}
	}
}