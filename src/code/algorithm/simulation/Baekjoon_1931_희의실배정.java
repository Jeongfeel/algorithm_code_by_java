package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_1931_희의실배정 {
	private static class Conference implements Comparable<Conference> {
		int startTime;
		int endTime;

		public Conference(int startTime, int endTime) {
			super();
			this.startTime = startTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Conference o) {
			if(this.endTime==o.endTime) {
				return this.startTime-o.startTime;
			}
			return this.endTime - o.endTime;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int countOfConference = Integer.parseInt(br.readLine());

		Conference conferenceList[] = new Conference[countOfConference];

		for (int i = 0; i < countOfConference; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());

			conferenceList[i] = new Conference(startTime, endTime);
		}

		Arrays.sort(conferenceList);

		int maximumConference = 0;
		int conferenceEndTime = 0;
		for (Conference conference : conferenceList) {
			if (conference.startTime >= conferenceEndTime) {
				conferenceEndTime = conference.endTime;
				maximumConference += 1;
			}
		}

		System.out.println(maximumConference);
	}
}
