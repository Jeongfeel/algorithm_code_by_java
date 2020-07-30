package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWExpert_1225_암호생성기 {
	private static final int TEST_CASE = 10;
	private static final int PASSWORD_DATA = 8;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> passwordQueue = new LinkedList<>();

		for (int tc = 1; tc <= TEST_CASE; tc++) {
			int testcaseIndex = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < PASSWORD_DATA; i++) {
				passwordQueue.add(Integer.parseInt(st.nextToken()));
			}

			makePassword(passwordQueue);

			System.out.println("#" + testcaseIndex + " " + getPassword(passwordQueue));
		}
	}

	private static void makePassword(Queue<Integer> passwordQueue) {
		int minusNumber = 1;

		while (true) {
			int frontNumber = passwordQueue.poll();
			frontNumber -= minusNumber;

			if (frontNumber <= 0) {
				break;
			}

			passwordQueue.add(frontNumber);
			minusNumber += 1;

			if (minusNumber > 5) {
				minusNumber = 1;
			}
		}

		passwordQueue.add(0);
	}

	private static String getPassword(Queue<Integer> passwordQueue) {
		String password = "";
		while (!passwordQueue.isEmpty()) {
			password += passwordQueue.poll();
			password += " ";
		}

		return password;
	}
}
