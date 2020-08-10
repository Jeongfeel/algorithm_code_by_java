package code.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_10814_나이순_정렬 {
	private static class Member implements Comparable<Member> {
		int index;
		int age;
		String name;

		public Member(int index, int age, String name) {
			super();
			this.index = index;
			this.age = age;
			this.name = name;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(age);
			builder.append(" ");
			builder.append(name);
			return builder.toString();
		}

		@Override
		public int compareTo(Member o) {
			if (this.age != o.age) {
				return this.age - o.age;
			} else {
				return this.index - o.index;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int countOfMember = Integer.parseInt(br.readLine());
		Member memberList[] = new Member[countOfMember];

		for (int i = 0; i < countOfMember; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();

			memberList[i] = new Member(i, age, name);
		}

		Arrays.sort(memberList);
		for (Member member : memberList) {
			System.out.println(member.toString());
		}
	}
}
