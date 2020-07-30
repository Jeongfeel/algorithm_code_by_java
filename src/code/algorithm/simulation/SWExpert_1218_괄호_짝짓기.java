package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWExpert_1218_괄호_짝짓기 {
	private static final int TEST_CASE = 10;
	private static final int COUNT_OF_BRACKET = 4;
	private static final char OPEN_BRACKET[] = { '(', '[', '{', '<' };
	private static final char CLOSE_BRACKET[] = { ')', ']', '}', '>' };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= TEST_CASE; tc++) {
			int sizeOfTestcase = Integer.parseInt(br.readLine());
			Stack<Character> bracketStack = new Stack<>();
			String testcase = br.readLine();
			boolean isCompleteBracket = true;

			for (int i = 0; i < sizeOfTestcase; i++) {
				char bracket = testcase.charAt(i);
				int bracketIndex = getBracketIndex(bracket);

				if (bracketIndex > 0) {
					bracketStack.push(bracket);
				} else if (bracketIndex < 0) {
					if (bracketStack.isEmpty()) {
						isCompleteBracket = false;
						break;
					}

					char openBracket = bracketStack.pop();
					int openBracketIndex = getBracketIndex(openBracket);

					if (bracketIndex + openBracketIndex != 0) {
						isCompleteBracket = false;
						break;
					}
				}
			}

			if (!bracketStack.isEmpty()) {
				isCompleteBracket = false;
			}

			System.out.println("#" + tc + " " + (isCompleteBracket ? 1 : 0));
		}
	}

	private static int getBracketIndex(char bracket) {
		for (int i = 0; i < COUNT_OF_BRACKET; i++) {
			if (OPEN_BRACKET[i] == bracket) {
				return i;
			}
		}

		for (int i = 0; i < COUNT_OF_BRACKET; i++) {
			if (CLOSE_BRACKET[i] == bracket) {
				return -i;
			}
		}

		return 0;
	}
}
