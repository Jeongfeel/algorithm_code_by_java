package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Baekjoon_15961_회전_초밥 {
	private static int N, d, k, c;
	private static int conveyerSushi[];

	public static void main(String[] args) throws IOException {
		inputSushiData();
		printMaximumCountOfSushi();
	}

	private static void inputSushiData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		conveyerSushi = new int[N];

		for (int i = 0; i < N; i++) {
			conveyerSushi[i] = Integer.parseInt(br.readLine());
		}
	}

	private static void printMaximumCountOfSushi() {
		int currentCountOfSushi = 1;
		Map<Integer, Integer> eatenSushiCount = getEatenSushiCount();

		for (int i = 0; i < k; i++) {
			int countOfSushi = eatenSushiCount.get(conveyerSushi[i]);
			if (countOfSushi == 0) {
				currentCountOfSushi += 1;
			}

			countOfSushi += 1;
			eatenSushiCount.put(conveyerSushi[i], countOfSushi);
		}

		int maximumCountOfSushi = currentCountOfSushi;

		int startIndex = 0;
		int finalIndex = k;

		while (startIndex != N) {
			int countOfStartSushi = eatenSushiCount.get(conveyerSushi[startIndex]);
			if (countOfStartSushi == 1) {
				currentCountOfSushi -= 1;
			}

			countOfStartSushi -= 1;
			eatenSushiCount.put(conveyerSushi[startIndex], countOfStartSushi);
			startIndex += 1;

			int countOfFinalSushi = eatenSushiCount.get(conveyerSushi[finalIndex]);
			if (countOfFinalSushi == 0) {
				currentCountOfSushi += 1;
			}

			countOfFinalSushi += 1;
			eatenSushiCount.put(conveyerSushi[finalIndex], countOfFinalSushi);
			finalIndex += 1;

			if (maximumCountOfSushi < currentCountOfSushi) {
				maximumCountOfSushi = currentCountOfSushi;
			}

			if (finalIndex == N) {
				finalIndex = 0;
			}
		}

		System.out.println(maximumCountOfSushi);
	}

	private static Map<Integer, Integer> getEatenSushiCount() {
		Map<Integer, Integer> eatenSushiCount = new HashMap<Integer, Integer>();
		for (int i = 1; i <= d; i++) {
			eatenSushiCount.put(i, 0);
		}

		eatenSushiCount.put(c, 1);
		return eatenSushiCount;
	}
}
