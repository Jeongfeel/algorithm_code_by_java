package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_16235_나무_재테크 {
	static class Tree implements Comparable<Tree> {
		int r;
		int c;
		int age;

		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}

	private static final int INIT_FERTILIZER = 5;
	private static final int COUNT_OF_DIRECTION = 8;
	private static final int NEAR_R_POINT[] = { 1, 1, 0, -1, -1, -1, 0, 1 };
	private static final int NEAR_C_POINT[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	private static int N, M, K;
	private static int ground[][];
	private static int fertilizer[][];
	private static PriorityQueue<Tree> treePQ = new PriorityQueue<Tree>();

	public static void main(String[] args) throws IOException {
		inputGroundData();
		checkSurvivedTree();
		System.out.println(treePQ.size());
	}

	private static void inputGroundData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ground = new int[N + 1][N + 1];
		fertilizer = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				ground[i][j] = INIT_FERTILIZER;
				fertilizer[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			treePQ.add(new Tree(x, y, z));
		}
	}

	private static void checkSurvivedTree() {
		for (int i = 0; i < K; i++) {
			treePQ = makeOneYearLaterTreePQ();
		}
	}

	private static PriorityQueue<Tree> makeOneYearLaterTreePQ() {
		PriorityQueue<Tree> oneYearLaterTreePQ = new PriorityQueue<Tree>();
		int summerAndWinterFertilizer[][] = getOriginalFertilizer();
		Queue<Tree> fallTreeQueue = new LinkedList<Tree>();

		while (!treePQ.isEmpty()) {
			Tree treeInformation = treePQ.poll();
			int r = treeInformation.r;
			int c = treeInformation.c;
			int age = treeInformation.age;

			if (spring(r, c, age)) {
				ground[r][c] -= age;

				int nextAge = age + 1;
				oneYearLaterTreePQ.add(new Tree(r, c, nextAge));

				fall(r, c, nextAge, fallTreeQueue);
			} else {
				summerAndWinterFertilizer[r][c] += (age / 2);
			}
		}

		addNewTree(oneYearLaterTreePQ, fallTreeQueue);
		summerAndWinter(summerAndWinterFertilizer);

		return oneYearLaterTreePQ;
	}

	private static int[][] getOriginalFertilizer() {
		int originalFertilizer[][] = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				originalFertilizer[i][j] = fertilizer[i][j];
			}
		}

		return originalFertilizer;
	}

	private static boolean spring(int r, int c, int age) {
		if (ground[r][c] - age >= 0) {
			return true;
		}
		return false;
	}

	private static void fall(int r, int c, int nextAge, Queue<Tree> fallTreeQueue) {
		if (nextAge % 5 == 0) {
			for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
				int nextR = r + NEAR_R_POINT[direction];
				int nextC = c + NEAR_C_POINT[direction];

				if (nextR == 0 || nextR > N || nextC == 0 || nextC > N) {
					continue;
				}

				fallTreeQueue.add(new Tree(nextR, nextC, 1));
			}
		}
	}

	private static void addNewTree(Queue<Tree> oneYearLaterTreePQ, Queue<Tree> fallTreeQueue) {
		while (!fallTreeQueue.isEmpty()) {
			oneYearLaterTreePQ.add(fallTreeQueue.poll());
		}
	}

	private static void summerAndWinter(int[][] summerAndWinterFertilizer) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				ground[i][j] += summerAndWinterFertilizer[i][j];
			}
		}
	}
}
