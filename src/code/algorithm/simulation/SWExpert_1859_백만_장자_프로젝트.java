package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_1859_백만_장자_프로젝트 {
	private static final int MAX_PREDICTED_DAY = 1000000;

	private static int price[] = new int[MAX_PREDICTED_DAY];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int i = 1; i <= testcase; i++) {
			int predictedDay = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < predictedDay; j++) {
				price[j] = Integer.parseInt(st.nextToken());
			}

			int currentMaxPrice = 0;
			long advantage = 0;
			for (int j = predictedDay - 1; j >= 0; j--) {
				if (currentMaxPrice >= price[j]) {
					advantage += currentMaxPrice - price[j];
					continue;
				}

				currentMaxPrice = price[j];
			}

			System.out.println("#" + i + " " + advantage);
		}
	}
}