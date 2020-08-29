package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_4012_요리사 {
	private static int synergyOfIngredient[][];
	private static boolean isFirstCook[];

	private static int N;
	private static int minimumDifference;

	private static BufferedReader br;
	private static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int countOfTestcase = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= countOfTestcase; testcase++) {
			inputData();
			makeMinimumDifference(0, 0);

			System.out.println("#" + testcase + " " + minimumDifference);
		}
	}

	private static void inputData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		synergyOfIngredient = new int[N][N];
		isFirstCook = new boolean[N];
		minimumDifference = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				synergyOfIngredient[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void makeMinimumDifference(int index, int countOfIngredient) {
		if (countOfIngredient == N / 2) {
			checkMinimumDifference();
			return;
		}

		for (int i = index; i < N; i++) {
			isFirstCook[i] = true;
			makeMinimumDifference(i + 1, countOfIngredient + 1);
			isFirstCook[i] = false;
		}
	}

	private static void checkMinimumDifference() {
		int firstCookIngredient[] = new int[N / 2];
		int secondCookIngredient[] = new int[N / 2];

		int firstIndex = 0;
		int secondIndex = 0;
		for (int i = 0; i < N; i++) {
			if (isFirstCook[i] == true) {
				firstCookIngredient[firstIndex++] = i;
			} else {
				secondCookIngredient[secondIndex++] = i;
			}
		}

		int firstCookSynergy = getSynergy(firstCookIngredient);
		int secondCookSynergy = getSynergy(secondCookIngredient);

		int differnceOfSynergy = Math.abs(firstCookSynergy - secondCookSynergy);
		if (minimumDifference > differnceOfSynergy) {
			minimumDifference = differnceOfSynergy;
		}
	}

	private static int getSynergy(int[] cookIngredient) {
		int synergy = 0;
		for (int i = 0; i < N / 2; i++) {
			for (int j = i + 1; j < N / 2; j++) {
				synergy += synergyOfIngredient[cookIngredient[i]][cookIngredient[j]];
				synergy += synergyOfIngredient[cookIngredient[j]][cookIngredient[i]];
			}
		}

		return synergy;
	}
}
