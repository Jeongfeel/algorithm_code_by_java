package code.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JungOl_1681_해밀턴_순환회로 {
	private static int N;
	private static int deliveryPlace[][];
	private static boolean isVistedPlace[];
	private static int order[] = new int[12];
	private static int minimumDeliveryCost = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		inputData();
		isVistedPlace[0] = true;
		makeMinimumDeliveryCost(0, 0, 1);
		System.out.println(minimumDeliveryCost);
	}

	private static void inputData() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		deliveryPlace = new int[N][N];
		isVistedPlace = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				deliveryPlace[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void makeMinimumDeliveryCost(int previousPlace, int weight, int countOfPlace) {
		if (minimumDeliveryCost < weight) {
			return;
		}
		if (countOfPlace == N) {
			if (deliveryPlace[previousPlace][0] == 0) {
				return;
			}
			weight += deliveryPlace[previousPlace][0];
			if (minimumDeliveryCost > weight) {
				minimumDeliveryCost = weight;
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isVistedPlace[i] == true) {
				continue;
			}
			if (deliveryPlace[previousPlace][i] == 0) {
				continue;
			}

			isVistedPlace[i] = true;
			order[countOfPlace] = i;
			makeMinimumDeliveryCost(i, weight + deliveryPlace[previousPlace][i], countOfPlace + 1);
			isVistedPlace[i] = false;
		}
	}
}
