package code.algorithm.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon_1786_찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String firstString = br.readLine();
		String secondString = br.readLine();

		int failArray[] = makeFailArray(secondString);

		int countOfSameString = 0;
		List<Integer> sameStringIndexList = new ArrayList<Integer>();

		int prefixIndex, suffixIndex = 0;
		for (prefixIndex = 0; prefixIndex < firstString.length(); prefixIndex++) {
			while (suffixIndex > 0 && firstString.charAt(prefixIndex) != secondString.charAt(suffixIndex)) {
				suffixIndex = failArray[suffixIndex - 1];
			}

			if (firstString.charAt(prefixIndex) == secondString.charAt(suffixIndex)) {
				if (suffixIndex == secondString.length() - 1) {
					countOfSameString += 1;
					sameStringIndexList.add(prefixIndex + 1 - secondString.length() + 1);
					suffixIndex = failArray[suffixIndex];
				} else {
					suffixIndex += 1;
				}
			}
		}

		System.out.println(countOfSameString);
		for (int index : sameStringIndexList) {
			System.out.print(index + " ");
		}
	}

	private static int[] makeFailArray(String secondString) {
		int failArray[] = new int[secondString.length()];
		int prefixIndex, suffixIndex = 0;

		for (prefixIndex = 1; prefixIndex < secondString.length(); prefixIndex++) {
			while (suffixIndex > 0 && secondString.charAt(prefixIndex) != secondString.charAt(suffixIndex)) {
				suffixIndex = failArray[suffixIndex - 1];
			}

			if (secondString.charAt(prefixIndex) == secondString.charAt(suffixIndex)) {
				suffixIndex += 1;
				failArray[prefixIndex] = suffixIndex;
			}
		}

		return failArray;
	}
}
