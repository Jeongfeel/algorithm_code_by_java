package code.algorithm.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_15686_치킨_배달 {
	private static final int BLANK = 0;
	private static final int HOUSE = 1;
	private static final int CHICKEN = 2;

	private static List<int[]> houseList = new ArrayList<int[]>();
	private static List<int[]> chickenList = new ArrayList<int[]>();

	private static int sizeOfCity;
	private static int countOfOpenChickenRestaurant;
	private static int minimumChickenDistance = Integer.MAX_VALUE;

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		inputCityData();
		chooseNotClosedChickenRestaurant(0, 0, new ArrayList<int[]>());

		System.out.println(minimumChickenDistance);
	}

	private static void inputCityData() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		sizeOfCity = Integer.parseInt(st.nextToken());
		countOfOpenChickenRestaurant = Integer.parseInt(st.nextToken());

		for (int ySize = 0; ySize < sizeOfCity; ySize++) {
			st = new StringTokenizer(br.readLine());
			for (int xSize = 0; xSize < sizeOfCity; xSize++) {
				int positionState = Integer.parseInt(st.nextToken());

				if (positionState == BLANK) {
					continue;
				}

				if (positionState == HOUSE) {
					houseList.add(new int[] { ySize, xSize });
				} else if (positionState == CHICKEN) {
					chickenList.add(new int[] { ySize, xSize });
				}
			}
		}
	}

	private static void chooseNotClosedChickenRestaurant(int currentIndex, int countOfRestaurant,
			ArrayList<int[]> choosedChickenList) {
		if (countOfRestaurant == countOfOpenChickenRestaurant) {
			int minimumDistance = getMinimumDistance(choosedChickenList);
			checkMinimumChickenDistance(minimumDistance);

			return;
		}

		for (int index = currentIndex; index < chickenList.size(); index++) {
			ArrayList<int[]> nextChoosedChickenList = new ArrayList<int[]>();
			nextChoosedChickenList.addAll(choosedChickenList);
			nextChoosedChickenList.add(chickenList.get(index));
			chooseNotClosedChickenRestaurant(index + 1, countOfRestaurant + 1, nextChoosedChickenList);
		}
	}

	private static int getMinimumDistance(ArrayList<int[]> choosedChickenList) {
		int minimumDistance = 0;
		for (int housePosition[] : houseList) {
			int distanceBetweenHouseAndChicken = Integer.MAX_VALUE;
			for (int chickenPosition[] : choosedChickenList) {
				int distance = Math.abs(housePosition[0] - chickenPosition[0])
						+ Math.abs(housePosition[1] - chickenPosition[1]);
				if (distance < distanceBetweenHouseAndChicken) {
					distanceBetweenHouseAndChicken = distance;
				}
			}

			minimumDistance += distanceBetweenHouseAndChicken;
		}

		return minimumDistance;
	}

	private static void checkMinimumChickenDistance(int minimumDistance) {
		if (minimumDistance < minimumChickenDistance) {
			minimumChickenDistance = minimumDistance;
		}
	}

}
