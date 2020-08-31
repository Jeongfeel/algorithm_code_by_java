package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1357_뒤집힌_덧셈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int firstNumber = reverseNumber(st.nextToken());
		int secondNumber = reverseNumber(st.nextToken());

		System.out.println(reverseNumber(String.valueOf(firstNumber + secondNumber)));
	}

	private static int reverseNumber(String number) {
		String reverseNumber = "";
		for (int i = number.length() - 1; i >= 0; i--) {
			reverseNumber += number.charAt(i);
		}

		return Integer.parseInt(reverseNumber);
	}
}
