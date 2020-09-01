package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14889_스타트와_링크 {
	private static int countOfPeople;
	private static int ability[][];
	private static boolean isAssignedPeople[];
	private static int minimumDifferenceOfAbility = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		inputStartAndLinkData();
		assignTeam(0, 0);
		System.out.println(minimumDifferenceOfAbility);
	}

	private static void inputStartAndLinkData() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		countOfPeople = Integer.parseInt(br.readLine());
		ability = new int[countOfPeople][countOfPeople];
		isAssignedPeople = new boolean[countOfPeople];

		for (int i = 0; i < countOfPeople; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < countOfPeople; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void assignTeam(int peopleIndex, int countOfAssignPeople) {
		if (countOfAssignPeople == countOfPeople / 2) {
			int differencOfAbility = getDifferenceOfAbility();
			if (minimumDifferenceOfAbility > differencOfAbility) {
				minimumDifferenceOfAbility = differencOfAbility;
			}
			return;
		}

		for (int i = peopleIndex; i < countOfPeople; i++) {
			isAssignedPeople[i] = true;
			assignTeam(i + 1, countOfAssignPeople + 1);
			isAssignedPeople[i] = false;
		}
	}

	private static int getDifferenceOfAbility() {
		int firstTeamMember[] = new int[countOfPeople / 2];
		int secondTeamMember[] = new int[countOfPeople / 2];
		int firstTeamMemberIndex = 0;
		int secondTeamMemberIndex = 0;
		for (int i = 0; i < countOfPeople; i++) {
			if (isAssignedPeople[i] == true) {
				firstTeamMember[firstTeamMemberIndex++] = i;
			} else {
				secondTeamMember[secondTeamMemberIndex++] = i;
			}
		}

		int firstTeamAbility = getTeamAbility(firstTeamMember);
		int secondTeamAbility = getTeamAbility(secondTeamMember);

		return Math.abs(firstTeamAbility - secondTeamAbility);
	}

	private static int getTeamAbility(int[] teamMember) {
		int teamAbility = 0;
		for (int i = 0; i < countOfPeople / 2; i++) {
			for (int j = i + 1; j < countOfPeople / 2; j++) {
				teamAbility += ability[teamMember[i]][teamMember[j]];
				teamAbility += ability[teamMember[j]][teamMember[i]];
			}
		}
		return teamAbility;
	}
}
