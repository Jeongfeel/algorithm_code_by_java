package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_9229_한빈이와_Spot_Mart {
	private static final int MAXIMUM_SNACK_COUNT = 1000;
	private static final int SNACK[] = new int[MAXIMUM_SNACK_COUNT];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testcase; tc++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int countOfSnack = Integer.parseInt(st1.nextToken());
			int hanbinSnackWeight = Integer.parseInt(st1.nextToken());

			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int snackIndex = 0; snackIndex < countOfSnack; snackIndex++) {
				SNACK[snackIndex] = Integer.parseInt(st2.nextToken());
			}

			int maximumSnackWeight = -1;
			for (int firstSnackIndex = 0; firstSnackIndex < countOfSnack; firstSnackIndex++) {
				for (int secondSnackIndex = firstSnackIndex + 1; secondSnackIndex < countOfSnack; secondSnackIndex++) {
					int snackWeight = SNACK[firstSnackIndex] + SNACK[secondSnackIndex];
					if (snackWeight > hanbinSnackWeight) {
						continue;
					}

					if (snackWeight > maximumSnackWeight) {
						maximumSnackWeight = snackWeight;
					}
				}
			}

			System.out.println("#" + tc + " " + maximumSnackWeight);
		}
	}
}
