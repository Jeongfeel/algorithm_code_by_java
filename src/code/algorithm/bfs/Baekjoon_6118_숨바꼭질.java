package code.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_6118_숨바꼭질 {
	private static ArrayList<ArrayList<Integer>> barnAdjacentList = new ArrayList<ArrayList<Integer>>();
	private static int countOfBarn;
	private static int countOfRelation;
	private static boolean isVisitedBarn[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		countOfBarn = Integer.parseInt(st.nextToken());
		countOfRelation = Integer.parseInt(st.nextToken());

		makeBarnAdjacentList(br);
		checkBarnToHide();
	}

	private static void makeBarnAdjacentList(BufferedReader br) throws IOException {
		for (int i = 0; i <= countOfBarn; i++) {
			barnAdjacentList.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < countOfRelation; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int firstBarn = Integer.parseInt(st.nextToken());
			int secondBarn = Integer.parseInt(st.nextToken());

			barnAdjacentList.get(firstBarn).add(secondBarn);
			barnAdjacentList.get(secondBarn).add(firstBarn);
		}

		isVisitedBarn = new boolean[countOfBarn + 1];
	}

	private static void checkBarnToHide() {
		Queue<int[]> barnQueue = new LinkedList<int[]>();
		barnQueue.add(new int[] { 1, 0 });
		isVisitedBarn[1] = true;

		int distanceOfBarn[] = new int[countOfBarn + 1];
		while (!barnQueue.isEmpty()) {
			int barnInformation[] = barnQueue.poll();
			int barnIndex = barnInformation[0];
			int barnDistance = barnInformation[1];
			distanceOfBarn[barnIndex] = barnDistance;

			for (int i = 0; i < barnAdjacentList.get(barnIndex).size(); i++) {
				int nextBarnIndex = barnAdjacentList.get(barnIndex).get(i);

				if (isVisitedBarn[nextBarnIndex] == true) {
					continue;
				}

				barnQueue.add(new int[] { nextBarnIndex, barnDistance + 1 });
				isVisitedBarn[nextBarnIndex] = true;
			}
		}

		int longestBarnIndex = 1;
		int longestBarnDistance = 0;
		int countOfSameDistanceBarn = 1;

		for (int i = 1; i <= countOfBarn; i++) {
			if (longestBarnDistance < distanceOfBarn[i]) {
				longestBarnDistance = distanceOfBarn[i];
				longestBarnIndex = i;
				countOfSameDistanceBarn = 1;
				continue;
			}
			if (longestBarnDistance == distanceOfBarn[i]) {
				countOfSameDistanceBarn += 1;
				if (longestBarnIndex > i) {
					longestBarnIndex = i;
				}
			}
		}

		System.out.println(longestBarnIndex + " " + longestBarnDistance + " " + countOfSameDistanceBarn);
	}

}