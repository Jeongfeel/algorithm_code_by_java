package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWExpert_1233_사칙연산_유효성_검사 {
	private static final int TEST_CASE = 10;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int testcase = 1; testcase <= TEST_CASE; testcase++) {
			int countOfNode = Integer.parseInt(br.readLine());
			boolean isValidCalculation = true;

			if (isInValidCountOfNode(countOfNode)) {
				isValidCalculation = false;
				readAllData(br, 0, countOfNode);
				System.out.println("#" + testcase + " " + (isValidCalculation ? 1 : 0));
				continue;
			}

			for (int node = 0; node < countOfNode; node++) {
				if (isValidCalculation == false) {
					readAllData(br, node, countOfNode);
					break;
				}

				String nodeData[] = br.readLine().split(" ");

				if (!isLeafNode(nodeData.length)) {
					if (isOperartor(nodeData[1])) {
						continue;
					}

					isValidCalculation = false;
				}

				if (isLeafNode(nodeData.length)) { // leaf node
					if (isNumber(nodeData[1])) {
						continue;
					}

					isValidCalculation = false;
				}

			}

			System.out.println("#" + testcase + " " + (isValidCalculation ? 1 : 0));
		}

	}

	private static final boolean isInValidCountOfNode(int countOfNode) {
		if (countOfNode % 2 == 0) {
			return true;
		}

		return false;
	}

	private static final void readAllData(BufferedReader br, int startIndex, int countOfNode) throws IOException {
		for (int node = startIndex; node < countOfNode; node++) {
			br.readLine();
		}
	}

	private static final boolean isLeafNode(int lengthOfNodeData) {
		if (lengthOfNodeData == 4) {
			return false;
		}

		return true;
	}

	private static final boolean isOperartor(String operator) {
		if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
			return true;
		}

		return false;
	}

	private static final boolean isNumber(String number) {
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}
}
