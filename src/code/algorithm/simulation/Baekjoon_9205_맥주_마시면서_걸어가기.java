package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_9205_맥주_마시면서_걸어가기 {
	private static BufferedReader br;
	private static StringTokenizer st;

	private static int countOfConvenienceStore;
	private static int sangeunHouse[];
	private static int convenienceStore[][];
	private static int pentaport[];
	private static boolean isVisitedConvenienceStore[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int countOfTestcase = Integer.parseInt(br.readLine());

		for (int i = 0; i < countOfTestcase; i++) {
			inputData();
			printHappyOrSad();
		}
	}

	private static void inputData() throws NumberFormatException, IOException {
		countOfConvenienceStore = Integer.parseInt(br.readLine());

		sangeunHouse = new int[2];
		convenienceStore = new int[countOfConvenienceStore][2];
		pentaport = new int[2];
		isVisitedConvenienceStore = new boolean[countOfConvenienceStore];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2; i++) {
			sangeunHouse[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < countOfConvenienceStore; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				convenienceStore[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2; i++) {
			pentaport[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void printHappyOrSad() {
		Queue<int[]> positionQueue = new LinkedList<int[]>();

		positionQueue.add(new int[] { sangeunHouse[0], sangeunHouse[1] });

		while (!positionQueue.isEmpty()) {
			int position[] = positionQueue.poll();
			int x = position[0];
			int y = position[1];

			if (Math.abs(pentaport[0] - x) + Math.abs(pentaport[1] - y) <= 1000) {
				System.out.println("happy");
				return;
			}

			for (int i = 0; i < countOfConvenienceStore; i++) {
				if (isVisitedConvenienceStore[i] == true) {
					continue;
				}

				int convenienceX = convenienceStore[i][0];
				int convenienceY = convenienceStore[i][1];

				if (Math.abs(convenienceX - x) + Math.abs(convenienceY - y) <= 1000) {
					positionQueue.add(new int[] { convenienceX, convenienceY });
					isVisitedConvenienceStore[i] = true;
				}
			}
		}

		System.out.println("sad");
	}
}
