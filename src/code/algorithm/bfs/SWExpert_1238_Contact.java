package code.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWExpert_1238_Contact {
	private static final int TEST_CASE = 10;
	private static final int MAXIMUM_NUMBER = 100 + 1;
	private static boolean isVistedNumber[] = new boolean[MAXIMUM_NUMBER];
	private static ArrayList<ArrayList<Integer>> numberArrayList = new ArrayList<ArrayList<Integer>>();
	private static int numberDepth[] = new int[MAXIMUM_NUMBER];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < MAXIMUM_NUMBER; i++) {
			numberArrayList.add(new ArrayList<Integer>());
		}

		for (int testcase = 1; testcase <= TEST_CASE; testcase++) {
			initNumberArrayList(numberArrayList);

			StringTokenizer st = new StringTokenizer(br.readLine());

			int lengthOfData = Integer.parseInt(st.nextToken());
			int startNumber = Integer.parseInt(st.nextToken());

			setNumberArrayList(br, lengthOfData, numberArrayList);

			Queue<Integer> numberQueue = new LinkedList<Integer>();
			numberQueue.add(startNumber);
			isVistedNumber[startNumber] = true;
			numberDepth[startNumber] = 1;

			callAdjacentNumber(numberQueue);
			int maximumNumber = findMaximumNumber();

			System.out.println("#" + testcase + " " + maximumNumber);
		}
	}

	private static void initNumberArrayList(ArrayList<ArrayList<Integer>> numberArrayList) {
		for (int i = 0; i < MAXIMUM_NUMBER; i++) {
			numberArrayList.get(i).clear();
			isVistedNumber[i] = false;
			numberDepth[i] = 0;
		}
	}

	private static void setNumberArrayList(BufferedReader br, int lengthOfData,
			ArrayList<ArrayList<Integer>> numberArrayList) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int index = 0; index < lengthOfData / 2; index++) {
			int fromNumber = Integer.parseInt(st.nextToken());
			int toNumber = Integer.parseInt(st.nextToken());

			numberArrayList.get(fromNumber).add(toNumber);
		}
	}

	private static void callAdjacentNumber(Queue<Integer> numberQueue) {
		while (!numberQueue.isEmpty()) {
			int number = numberQueue.poll();

			for (int index = 0; index < numberArrayList.get(number).size(); index++) {
				int nextNumber = numberArrayList.get(number).get(index);

				if (isVistedNumber[nextNumber] == true) {
					continue;
				}

				isVistedNumber[nextNumber] = true;
				numberDepth[nextNumber] = numberDepth[number] + 1;
				numberQueue.add(nextNumber);
			}
		}
	}

	private static int findMaximumNumber() {
		int maximumNumber = 0;
		int maximumDepth = 0;
		for (int i = 1; i < MAXIMUM_NUMBER; i++) {
			if (numberDepth[i] < maximumDepth) {
				continue;
			}
			if (numberDepth[i] > maximumDepth) {
				maximumDepth = numberDepth[i];
				maximumNumber = i;
				continue;
			}
			if (numberDepth[i] == maximumDepth) {
				maximumNumber = i;
				continue;
			}
		}

		return maximumNumber;
	}

}
