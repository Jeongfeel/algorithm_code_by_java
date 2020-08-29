package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_3234_준환이의_양팔저울 {
	private static Integer weights[];
	private static boolean isWeightedWeight[];
	private static int countOfWeightMethod;

	private static int N;
	private static int sumOfWeights;

	private static BufferedReader br;
	private static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int countOfTestcase = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= countOfTestcase; testcase++) {
			inputData();
			makeCountOfWeightMehtod(0, 0, 0);
			System.out.println("#" + testcase + " " + countOfWeightMethod);
		}
	}

	private static void inputData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		weights = new Integer[N];
		isWeightedWeight = new boolean[N];

		sumOfWeights = 0;
		countOfWeightMethod = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
			sumOfWeights += weights[i];
		}
	}

	private static void makeCountOfWeightMehtod(int countOfWeight, int leftSumOfWeight, int rightSumOfWeight) {
		if (leftSumOfWeight < rightSumOfWeight) {
			return;
		}
		if (leftSumOfWeight >= sumOfWeights - leftSumOfWeight) {
			int lastCount = getLastCount(countOfWeight);
			countOfWeightMethod += lastCount;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isWeightedWeight[i] == true) {
				continue;
			}

			isWeightedWeight[i] = true;
			makeCountOfWeightMehtod(countOfWeight + 1, leftSumOfWeight + weights[i], rightSumOfWeight);
			makeCountOfWeightMehtod(countOfWeight + 1, leftSumOfWeight, rightSumOfWeight + weights[i]);
			isWeightedWeight[i] = false;
		}
	}

	private static int getLastCount(int countOfWeight) {
		int lastCount = 1;
		for (int i = 1; i <= N - countOfWeight; i++) {
			lastCount *= i;
			lastCount *= 2;
		}

		return lastCount;
	}
}
