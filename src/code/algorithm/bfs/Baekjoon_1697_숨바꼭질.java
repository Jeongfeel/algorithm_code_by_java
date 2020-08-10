package code.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_1697_숨바꼭질 {
	private static final int MINIMUM_POSITION = 0;
	private static final int MAXIMUM_POSITION = 100000 + 1;
	private static boolean isVisitedPosition[] = new boolean[MAXIMUM_POSITION];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int positionOfSubin = Integer.parseInt(st.nextToken());
		int positionOfSister = Integer.parseInt(st.nextToken());

		printMostFastTime(positionOfSubin, positionOfSister);
	}

	private static void printMostFastTime(int positionOfSubin, int positionOfSister) {
		Queue<int[]> subinPositionAndTimeQueue = new LinkedList<int[]>();
		subinPositionAndTimeQueue.add(new int[] { positionOfSubin, 0 });
		isVisitedPosition[positionOfSubin] = true;

		while (!subinPositionAndTimeQueue.isEmpty()) {
			int subinPositionAndTime[] = subinPositionAndTimeQueue.poll();
			int subinPosition = subinPositionAndTime[0];
			int time = subinPositionAndTime[1];

			if (subinPosition == positionOfSister) {
				System.out.println(time);
				return;
			}

			int minusPosition = subinPosition - 1;
			if (minusPosition >= MINIMUM_POSITION && isVisitedPosition[minusPosition] == false) {
				subinPositionAndTimeQueue.add(new int[] { minusPosition, time + 1 });
				isVisitedPosition[minusPosition] = true;
			}

			int plusPosition = subinPosition + 1;
			if (plusPosition < MAXIMUM_POSITION && isVisitedPosition[plusPosition] == false) {
				subinPositionAndTimeQueue.add(new int[] { plusPosition, time + 1 });
				isVisitedPosition[plusPosition] = true;
			}

			int teleportPosition = subinPosition * 2;
			if (teleportPosition < MAXIMUM_POSITION && isVisitedPosition[teleportPosition] == false) {
				subinPositionAndTimeQueue.add(new int[] { teleportPosition, time + 1 });
				isVisitedPosition[teleportPosition] = true;
			}
		}

	}
}
