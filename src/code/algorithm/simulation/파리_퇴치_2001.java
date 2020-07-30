package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파리_퇴치_2001 {
	private static final int MAX_SIZE_OF_MAP = 15;
	private static int flyMap[][] = new int[MAX_SIZE_OF_MAP][MAX_SIZE_OF_MAP];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testcase; tc++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int sizeOfMap = Integer.parseInt(st1.nextToken());
			int sizeOfFlySwatter = Integer.parseInt(st1.nextToken());

			makeFlyMap(br, sizeOfMap);
			int maximumDeadFlies = snapFlySwatter(sizeOfMap, sizeOfFlySwatter);

			System.out.println("#" + tc + " " + maximumDeadFlies);
		}
	}

	private static void makeFlyMap(BufferedReader br, int sizeOfMap) throws IOException {
		for (int i = 0; i < sizeOfMap; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int j = 0; j < sizeOfMap; j++) {
				flyMap[i][j] = Integer.parseInt(st2.nextToken());
			}
		}
	}

	private static int snapFlySwatter(int sizeOfMap, int sizeOfFlySwatter) {
		int maximumDeadFlies = 0;
		for (int i = 0; i <= sizeOfMap - sizeOfFlySwatter; i++) {
			for (int j = 0; j <= sizeOfMap - sizeOfFlySwatter; j++) {
				int deadFlies = getDeadFlies(i, j, sizeOfFlySwatter);
				if (deadFlies > maximumDeadFlies) {
					maximumDeadFlies = deadFlies;
				}
			}
		}

		return maximumDeadFlies;
	}

	private static int getDeadFlies(int xCoordinate, int yCoordinate, int sizeOfFlySwatter) {
		int deadFlies = 0;

		for (int i = xCoordinate; i < xCoordinate + sizeOfFlySwatter; i++) {
			for (int j = yCoordinate; j < yCoordinate + sizeOfFlySwatter; j++) {
				deadFlies += flyMap[i][j];
			}
		}

		return deadFlies;
	}
}