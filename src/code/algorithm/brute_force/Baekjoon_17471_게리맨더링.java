package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_17471_게리맨더링 {
	private static final boolean FIRST_SECTION = true;
	private static final boolean SECOND_SECTION = false;
	private static final int INFINITY_NUMBER = Integer.MAX_VALUE;
	private static final int INVALID_NUMBER = -1;

	private static int N;
	private static int areaPopulation[];
	private static int linkedArea[][];
	private static boolean assignedAreaSection[];
	private static int minimumDifferenceOfTwoSection = INFINITY_NUMBER;

	public static void main(String[] args) throws NumberFormatException, IOException {
		inputAreaData();
		assignArea(1);
		printMinimumDifferenceOfTwoSection();
	}

	private static void inputAreaData() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		areaPopulation = new int[N + 1];
		linkedArea = new int[N + 1][];
		assignedAreaSection = new boolean[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			areaPopulation[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int countOfLinkedArea = Integer.parseInt(st.nextToken());
			linkedArea[i] = new int[countOfLinkedArea];
			for (int j = 0; j < countOfLinkedArea; j++) {
				linkedArea[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void assignArea(int areaNumber) {
		if (areaNumber > N) {
			if (isPossibleTwoSection() == true) {
				int firstAreaPopulation = getAreaPopulation(FIRST_SECTION);
				int secondAreaPopulation = getAreaPopulation(SECOND_SECTION);

				int differenceOfTwoSection = Math.abs(firstAreaPopulation - secondAreaPopulation);
				if (minimumDifferenceOfTwoSection > differenceOfTwoSection) {
					minimumDifferenceOfTwoSection = differenceOfTwoSection;
				}
			}

			return;
		}

		assignedAreaSection[areaNumber] = FIRST_SECTION;
		assignArea(areaNumber + 1);
		assignedAreaSection[areaNumber] = SECOND_SECTION;
		assignArea(areaNumber + 1);
	}

	private static boolean isPossibleTwoSection() {
		if (isPossibleSection(FIRST_SECTION) == false) {
			return false;
		}
		if (isPossibleSection(SECOND_SECTION) == false) {
			return false;
		}

		return true;
	}

	private static boolean isPossibleSection(boolean section) {
		boolean isVisitedArea[] = new boolean[N + 1];

		int countOfSameAreaState = 0;
		int firstAreaNumber = INVALID_NUMBER;
		for (int i = 1; i <= N; i++) {
			if (assignedAreaSection[i] == section) {
				countOfSameAreaState += 1;
				firstAreaNumber = i;
			}
		}
		
		if (countOfSameAreaState == N || firstAreaNumber == INVALID_NUMBER) {
			return false;
		}

		Queue<Integer> areaNumberQueue = new LinkedList<Integer>();
		areaNumberQueue.add(firstAreaNumber);
		isVisitedArea[firstAreaNumber] = true;

		while (!areaNumberQueue.isEmpty()) {
			int areaNumber = areaNumberQueue.poll();
			countOfSameAreaState -= 1;

			for (int i = 0; i < linkedArea[areaNumber].length; i++) {
				int nextAreaNumber = linkedArea[areaNumber][i];

				if (assignedAreaSection[nextAreaNumber] != section) {
					continue;
				}
				if (isVisitedArea[nextAreaNumber] == true) {
					continue;
				}

				areaNumberQueue.add(nextAreaNumber);
				isVisitedArea[nextAreaNumber] = true;
			}
		}

		if (countOfSameAreaState != 0) {
			return false;
		}

		return true;
	}

	private static int getAreaPopulation(boolean areaState) {
		int sumOfAreaPopulation = 0;
		for (int i = 1; i <= N; i++) {
			if (assignedAreaSection[i] == areaState) {
				sumOfAreaPopulation += areaPopulation[i];
			}
		}

		return sumOfAreaPopulation;
	}

	private static void printMinimumDifferenceOfTwoSection() {
		if (minimumDifferenceOfTwoSection == Integer.MAX_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(minimumDifferenceOfTwoSection);
		}
	}
}
