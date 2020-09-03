package code.algorithm.graph;

import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWExpert_1251_하나로 {
	private static class IslandInfo implements Comparable<IslandInfo> {
		int islandNumber;
		double lengthOfTunnel;

		public IslandInfo(int islandNumber, double lengthOfTunnel) {
			super();
			this.islandNumber = islandNumber;
			this.lengthOfTunnel = lengthOfTunnel;
		}

		@Override
		public int compareTo(IslandInfo o) {
			if (this.lengthOfTunnel - o.lengthOfTunnel > 0) {
				return 1;
			} else if (this.lengthOfTunnel - o.lengthOfTunnel == 0) {
				return 0;
			} else {
				return -1;
			}
		}

	}

	private static BufferedReader br;
	private static StringTokenizer st;

	private static int N;
	private static int islandXCoordinate[];
	private static int islandYCoordinate[];
	private static boolean isVisitedIsland[];
	private static double powerLengthOfTunnel[][];
	private static double E;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int countOfTestacse = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= countOfTestacse; testcase++) {
			inputIslandData();
			makePowerLengthOfTunnel();
			printMinimumLengthOfTunnels(testcase);
		}
	}

	private static void inputIslandData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		islandXCoordinate = new int[N];
		islandYCoordinate = new int[N];
		powerLengthOfTunnel = new double[N][N];
		isVisitedIsland = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			islandXCoordinate[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			islandYCoordinate[i] = Integer.parseInt(st.nextToken());
		}
		E = Double.parseDouble(br.readLine());
	}

	private static void makePowerLengthOfTunnel() {
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int x1 = islandXCoordinate[i];
				int y1 = islandYCoordinate[i];
				int x2 = islandXCoordinate[j];
				int y2 = islandYCoordinate[j];

				double powerLength = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
				powerLengthOfTunnel[i][j] = powerLength;
				powerLengthOfTunnel[j][i] = powerLength;
			}
		}
	}

	private static void printMinimumLengthOfTunnels(int testcase) {
		PriorityQueue<IslandInfo> islandInfoPQ = new PriorityQueue<IslandInfo>();
		islandInfoPQ.add(new IslandInfo(0, 0));

		int countOfLinkedIsland = 0;
		double totalLengthOfTunnels = 0;
		while (!islandInfoPQ.isEmpty()) {
			IslandInfo islandInfo = islandInfoPQ.poll();
			int islandNumber = islandInfo.islandNumber;
			double lengthOfTunnel = islandInfo.lengthOfTunnel;

			if (isVisitedIsland[islandNumber] == true) {
				continue;
			}

			isVisitedIsland[islandNumber] = true;
			totalLengthOfTunnels += lengthOfTunnel;
			countOfLinkedIsland += 1;

			if (countOfLinkedIsland == N) {
				break;
			}

			for (int i = 0; i < N; i++) {
				if (isVisitedIsland[i] == true) {
					continue;
				}

				islandInfoPQ.add(new IslandInfo(i, powerLengthOfTunnel[islandNumber][i]));
			}
		}

		System.out.println("#" + testcase + " " + Math.round(totalLengthOfTunnels * E));
	}
}
