package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2961_도영이가_만든_맛있는_음식 {
	private static final int MAXIMUM_COUNT_OF_INGREDIENT = 10;
	private static int minimumDifference = Integer.MAX_VALUE;

	private static int sourTasteList[] = new int[MAXIMUM_COUNT_OF_INGREDIENT];
	private static int bitterTasteList[] = new int[MAXIMUM_COUNT_OF_INGREDIENT];
	private static int countOfIngredient;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		countOfIngredient = Integer.parseInt(br.readLine());
		for (int ingredient = 0; ingredient < countOfIngredient; ingredient++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int sourTaste = Integer.parseInt(st.nextToken());
			int bitterTaste = Integer.parseInt(st.nextToken());
			sourTasteList[ingredient] = sourTaste;
			bitterTasteList[ingredient] = bitterTaste;
		}

		checkMinimumDifference(0, 0, 1, 0);
		System.out.println(minimumDifference);
	}

	private static void checkMinimumDifference(int startIndex, int countOfUsedIngredient, int sumOfSour,
			int sumOfbitter) {
		if (countOfUsedIngredient != 0) {
			int difference = Math.abs(sumOfSour - sumOfbitter);

			if (minimumDifference > difference) {
				minimumDifference = difference;
			}
		}

		for (int index = startIndex; index < countOfIngredient; index++) {
			checkMinimumDifference(index + 1, countOfUsedIngredient + 1, sumOfSour * sourTasteList[index],
					sumOfbitter + bitterTasteList[index]);
			checkMinimumDifference(index + 1, countOfUsedIngredient, sumOfSour, sumOfbitter);
		}
	}
}
