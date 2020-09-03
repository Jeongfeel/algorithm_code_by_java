package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWExpert_1767_프로세서_연결하기 {
	private static final int POSSIBLE = 0;
	private static final int CORE = 1;
	private static final int IMPOSSIBLE = 2;
	private static final int COUNT_OF_DIRECTION = 4;
	private static int MOVE_X_POSITION[] = { 1, -1, 0, 0 };
	private static int MOVE_Y_POSITION[] = { 0, 0, 1, -1 };

	private static BufferedReader br;
	private static StringTokenizer st;
	private static int N, countOfUsedCore, countOfTotalCore, minimumDistance, mobileProcessor[][];
	private static ArrayList<int[]> coreList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int countOfTesetcase = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= countOfTesetcase; testcase++) {
			inputMobileProcessorData();
			usedCore(0, 0);
			System.out.println("#" + testcase + " " + minimumDistance);
		}
	}

	private static void inputMobileProcessorData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		mobileProcessor = new int[N][N];
		coreList = new ArrayList<int[]>();
		countOfUsedCore = 0;
		countOfTotalCore = 0;
		minimumDistance = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mobileProcessor[i][j] = Integer.parseInt(st.nextToken());
				if ((i == 0 || j == 0 || i == N - 1 || j == N - 1) && mobileProcessor[i][j] == 1)
					continue;
				if (mobileProcessor[i][j] == 1) {
					coreList.add(new int[] { i, j });
					countOfTotalCore++;
				}
			}
		}
	}

	private static void usedCore(int coreIndex, int countOfCurrentUsedCore) {
		if (countOfTotalCore - coreIndex + countOfCurrentUsedCore < countOfUsedCore) {
			return;
		}

		if (coreIndex == countOfTotalCore) {
			int distanceOfUsedCore = getDistanceOfUsedCore();
			if (countOfUsedCore < countOfCurrentUsedCore) {
				countOfUsedCore = countOfCurrentUsedCore;
				minimumDistance = distanceOfUsedCore;
			} else if (countOfUsedCore == countOfCurrentUsedCore) {
				if (minimumDistance > distanceOfUsedCore) {
					minimumDistance = distanceOfUsedCore;
				}
			}

			return;
		}

		int core[] = coreList.get(coreIndex);
		int x = core[0];
		int y = core[1];
		for (int direction = 0; direction < COUNT_OF_DIRECTION; direction++) {
			if (isAvailableCore(x, y, direction)) {
				setStatus(x, y, direction, IMPOSSIBLE);
				usedCore(coreIndex + 1, countOfCurrentUsedCore + 1);
				setStatus(x, y, direction, POSSIBLE);
			}
		}

		usedCore(coreIndex + 1, countOfCurrentUsedCore);
	}

	private static boolean isAvailableCore(int x, int y, int direction) {
		int nextX = x, nextY = y;
		while (true) {
			nextX += MOVE_X_POSITION[direction];
			nextY += MOVE_Y_POSITION[direction];
			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
				break;
			}
			if (mobileProcessor[nextX][nextY] >= CORE) {
				return false;
			}
		}

		return true;
	}

	private static void setStatus(int x, int y, int direction, int status) {
		int nextX = x, nextY = y;
		while (true) {
			nextX += MOVE_X_POSITION[direction];
			nextY += MOVE_Y_POSITION[direction];
			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
				break;
			}

			mobileProcessor[nextX][nextY] = status;
		}
	}

	private static int getDistanceOfUsedCore() {
		int distanceOfUsedCore = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (mobileProcessor[i][j] == 2)
					++distanceOfUsedCore;
			}
		}
		return distanceOfUsedCore;
	}
}