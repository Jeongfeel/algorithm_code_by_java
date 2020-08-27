package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_1247_최적_경로 {
	static class Coordinate {
		int x;
		int y;

		public Coordinate(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static int N;
	private static Coordinate company;
	private static Coordinate house;
	private static Coordinate customer[];
	private static Coordinate orderOfCustomer[];
	private static boolean isVistedCustomer[];

	private static int minimumDistance;

	private static BufferedReader br;
	private static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int countOfTestcase = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= countOfTestcase; testcase++) {
			inputData();
			assignCustomers(0);
			System.out.println("#" + testcase + " " + minimumDistance);
		}
	}

	private static void inputData() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		customer = new Coordinate[N];
		orderOfCustomer = new Coordinate[N];
		isVistedCustomer = new boolean[N];

		st = new StringTokenizer(br.readLine());
		company = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		house = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		for (int i = 0; i < N; i++) {
			customer[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		minimumDistance = Integer.MAX_VALUE;
	}

	private static void assignCustomers(int countOfCustomers) {
		if (countOfCustomers == N) {
			makeMinimumDistance();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isVistedCustomer[i] == true) {
				continue;
			}

			isVistedCustomer[i] = true;
			orderOfCustomer[countOfCustomers] = customer[i];
			assignCustomers(countOfCustomers + 1);
			isVistedCustomer[i] = false;
		}
	}

	private static void makeMinimumDistance() {
		int distance = 0;
		int x = company.x;
		int y = company.y;
		int nextX = -1;
		int nextY = -1;

		for (int i = 0; i < N; i++) {
			nextX = orderOfCustomer[i].x;
			nextY = orderOfCustomer[i].y;

			distance += Math.abs(x - nextX);
			distance += Math.abs(y - nextY);

			x = nextX;
			y = nextY;
		}

		distance += Math.abs(x - house.x);
		distance += Math.abs(y - house.y);

		if (minimumDistance > distance) {
			minimumDistance = distance;
		}
	}

}
