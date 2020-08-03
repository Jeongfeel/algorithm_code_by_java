package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWExpert_1861_정사각형_방 {
	private static final int MAX_COUNT_OF_ROOM = 1000;
	private static final int SIZE_OF_DIRECTION = 4;
	private static final int MAX_ROOM_NUMBER = 1024 * 1024 * 1024 * 2 - 1;
	private static final int MOVE_X_COORDINATE[] = { 1, -1, 0, 0 };
	private static final int MOVE_Y_COORDINATE[] = { 0, 0, 1, -1 };
	private static int[][] room = new int[MAX_COUNT_OF_ROOM][MAX_COUNT_OF_ROOM];
	private static int[][] moveCount = new int[MAX_COUNT_OF_ROOM][MAX_COUNT_OF_ROOM];
	private static boolean[][] isVistedRoom = new boolean[MAX_COUNT_OF_ROOM][MAX_COUNT_OF_ROOM];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testcase; tc++) {
			int sizeOfRoom = Integer.parseInt(br.readLine());

			makeRoom(sizeOfRoom, br);

			int maxCountOfMove = 0;
			int roomNumber = MAX_ROOM_NUMBER;

			for (int i = 0; i < sizeOfRoom; i++) {
				for (int j = 0; j < sizeOfRoom; j++) {
					if (isVistedRoom[i][j] == true) {
						if (maxCountOfMove == moveCount[i][j]) {
							if (roomNumber > room[i][j]) {
								roomNumber = room[i][j];
							}
						}

						continue;
					}

					int countOfMove = getCountOfMove(i, j, sizeOfRoom);

					if (maxCountOfMove < countOfMove) {
						maxCountOfMove = countOfMove;
						roomNumber = room[i][j];
					}

					if (maxCountOfMove == moveCount[i][j]) {
						if (roomNumber > room[i][j]) {
							roomNumber = room[i][j];
						}
					}
				}
			}

			System.out.println("#" + tc + " " + roomNumber + " " + maxCountOfMove);
		}
	}

	private static void makeRoom(int sizeOfRoom, BufferedReader br) throws IOException {
		for (int i = 0; i < sizeOfRoom; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < sizeOfRoom; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				isVistedRoom[i][j] = false;
			}
		}
	}

	private static int getCountOfMove(int xCoordinate, int yCoordinate, int sizeOfRoom) {
		int countOfMove = 0;

		Queue<int[]> roomQueue = new LinkedList<int[]>();
		Queue<int[]> adjacentRoomQueue = new LinkedList<int[]>();
		roomQueue.add(new int[] { xCoordinate, yCoordinate });

		while (!roomQueue.isEmpty()) {
			int roomCoordinate[] = roomQueue.poll();
			int roomXCoordinate = roomCoordinate[0];
			int roomYCoordinate = roomCoordinate[1];
			isVistedRoom[roomXCoordinate][roomYCoordinate] = true;
			countOfMove += 1;
			adjacentRoomQueue.add(new int[] { roomXCoordinate, roomYCoordinate });

			for (int i = 0; i < SIZE_OF_DIRECTION; i++) {
				int nextXCoordinate = roomXCoordinate + MOVE_X_COORDINATE[i];
				int nextYCoordinate = roomYCoordinate + MOVE_Y_COORDINATE[i];

				if (nextXCoordinate < 0 || nextXCoordinate == sizeOfRoom || nextYCoordinate < 0
						|| nextYCoordinate == sizeOfRoom) {
					continue;
				}
				if (isVistedRoom[nextXCoordinate][nextYCoordinate]) {
					continue;
				}
				if (Math.abs(room[roomXCoordinate][roomYCoordinate] - room[nextXCoordinate][nextYCoordinate]) != 1) {
					continue;
				}

				roomQueue.add(new int[] { nextXCoordinate, nextYCoordinate });
			}
		}

		while (!adjacentRoomQueue.isEmpty()) {
			int roomCoordinate[] = adjacentRoomQueue.poll();
			int roomXCoordinate = roomCoordinate[0];
			int roomYCoordinate = roomCoordinate[1];

			moveCount[roomXCoordinate][roomYCoordinate] = countOfMove;
		}

		return countOfMove;
	}
}