package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWExpert_2382_미생물_격리 {
	private static class MicroOrganismInfo {
		int r;
		int c;
		int countOfMicroOrganism;
		int direction;
		int accumulateCount;

		public MicroOrganismInfo() {
			super();
		}

		public MicroOrganismInfo(int r, int c, int countOfMicroOrganism, int direction) {
			super();
			this.r = r;
			this.c = c;
			this.countOfMicroOrganism = countOfMicroOrganism;
			this.direction = direction;
			this.accumulateCount = countOfMicroOrganism;
		}

	}

	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;

	private static final int COUNT_OF_DIRECTION = 4;
	private static final int MOVE_ROW_POSITION[] = { 0, -1, 1, 0, 0 };
	private static final int MOVE_COLUMN_POSITION[] = { 0, 0, 0, -1, 1 };

	private static BufferedReader br;
	private static StringTokenizer st;

	private static int N, M, K;
	private static MicroOrganismInfo microOrganismArea[][];
	private static Stack<MicroOrganismInfo> microOrganismStack;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int countOfTestcase = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= countOfTestcase; testcase++) {
			inputData();
			printCountOfMicroOrganism(testcase);
		}
	}

	private static void inputData() throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		microOrganismArea = new MicroOrganismInfo[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				microOrganismArea[i][j] = new MicroOrganismInfo();
			}
		}

		microOrganismStack = new Stack<MicroOrganismInfo>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int countOfMicroOrganism = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			microOrganismStack.add(new MicroOrganismInfo(r, c, countOfMicroOrganism, direction));
		}
	}

	private static void printCountOfMicroOrganism(int testcase) {
		for (int time = 0; time < M; time++) {
			while (!microOrganismStack.isEmpty()) {
				MicroOrganismInfo microOrganismInfo = microOrganismStack.pop();
				int r = microOrganismInfo.r;
				int c = microOrganismInfo.c;
				int countOfMicroOrganism = microOrganismInfo.countOfMicroOrganism;
				int direction = microOrganismInfo.direction;

				int nextR = r + MOVE_ROW_POSITION[direction];
				int nextC = c + MOVE_COLUMN_POSITION[direction];

				if (nextR == 0) {
					countOfMicroOrganism /= 2;
					direction = DOWN;
				} else if (nextR == N - 1) {
					countOfMicroOrganism /= 2;
					direction = UP;
				} else if (nextC == 0) {
					countOfMicroOrganism /= 2;
					direction = RIGHT;
				} else if (nextC == N - 1) {
					countOfMicroOrganism /= 2;
					direction = LEFT;
				}

				if (microOrganismArea[nextR][nextC].countOfMicroOrganism == 0) {
					microOrganismArea[nextR][nextC] = new MicroOrganismInfo(nextR, nextC, countOfMicroOrganism,
							direction);
				} else if (microOrganismArea[nextR][nextC].countOfMicroOrganism != 0) {
					if (microOrganismArea[nextR][nextC].countOfMicroOrganism > countOfMicroOrganism) {
						microOrganismArea[nextR][nextC].accumulateCount += countOfMicroOrganism;
					} else {
						microOrganismArea[nextR][nextC].countOfMicroOrganism = countOfMicroOrganism;
						microOrganismArea[nextR][nextC].direction = direction;
						microOrganismArea[nextR][nextC].accumulateCount += countOfMicroOrganism;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (microOrganismArea[i][j].countOfMicroOrganism == 0) {
						continue;
					}

					MicroOrganismInfo microOrganismInfo = microOrganismArea[i][j];
					microOrganismStack.add(new MicroOrganismInfo(microOrganismInfo.r, microOrganismInfo.c,
							microOrganismInfo.accumulateCount, microOrganismInfo.direction));
					microOrganismArea[i][j] = new MicroOrganismInfo();
				}
			}
		}

		int count = 0;
		while (!microOrganismStack.isEmpty()) {
			count += microOrganismStack.pop().countOfMicroOrganism;
		}

		System.out.println("#" + testcase + " " + count);
	}
}
