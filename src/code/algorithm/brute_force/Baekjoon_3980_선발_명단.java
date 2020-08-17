package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_3980_선발_명단 {
	private static final int COUNT_OF_PLAYER = 11;

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static boolean isUsedPosition[] = new boolean[COUNT_OF_PLAYER];
	private static int maximumAbility;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int countOfTestcase = Integer.parseInt(br.readLine());

		for (int testcase = 0; testcase < countOfTestcase; testcase++) {
			ArrayList<ArrayList<int[]>> playersAbilityList = makePlayerList();
			initIsUsedPosition();
			makeMaximumAbility(playersAbilityList, 0, 0);

			System.out.println(maximumAbility);
		}
	}

	private static void initIsUsedPosition() {
		maximumAbility = 0;
		for (int player = 0; player < COUNT_OF_PLAYER; player++) {
			isUsedPosition[player] = false;
		}
	}

	private static ArrayList<ArrayList<int[]>> makePlayerList() throws IOException {
		ArrayList<ArrayList<int[]>> playersAbilityList = new ArrayList<ArrayList<int[]>>();

		for (int player = 0; player < COUNT_OF_PLAYER; player++) {
			playersAbilityList.add(new ArrayList<int[]>());
		}
		for (int player = 0; player < COUNT_OF_PLAYER; player++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int position = 0; position < COUNT_OF_PLAYER; position++) {
				int ability = Integer.parseInt(st.nextToken());

				if (ability == 0) {
					continue;
				}

				playersAbilityList.get(player).add(new int[] { position, ability });
			}
		}

		return playersAbilityList;
	}

	private static void makeMaximumAbility(ArrayList<ArrayList<int[]>> playersAbilityList, int playerIndex,
			int sumOfAbility) {
		if (playerIndex == COUNT_OF_PLAYER) {
			if (sumOfAbility > maximumAbility) {
				maximumAbility = sumOfAbility;
			}

			return;
		}

		for (int index = 0; index < playersAbilityList.get(playerIndex).size(); index++) {
			int position = playersAbilityList.get(playerIndex).get(index)[0];
			int ability = playersAbilityList.get(playerIndex).get(index)[1];

			if (isUsedPosition[position] == true) {
				continue;
			}

			isUsedPosition[position] = true;
			makeMaximumAbility(playersAbilityList, playerIndex + 1, sumOfAbility + ability);
			isUsedPosition[position] = false;
		}

	}

}
