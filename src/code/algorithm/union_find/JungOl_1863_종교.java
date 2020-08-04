package code.algorithm.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class JungOl_1863_종교 {
	private static final int MAXIMUM_STUDENT = 50000 + 1;

	private static final int parentStudent[] = new int[MAXIMUM_STUDENT];
	private static final boolean isVisitedStudent[] = new boolean[MAXIMUM_STUDENT];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int countOfStudent = Integer.parseInt(st.nextToken());
		int relationOfStudent = Integer.parseInt(st.nextToken());

		initParentStudent(countOfStudent);
		makeParentStudent(br, relationOfStudent);
		optimizeParentStudent(countOfStudent);

		System.out.println(getCountOfReligion(countOfStudent));
	}

	private static int getCountOfReligion(int countOfStudent) {
		int countOfReligion = 0;

		for (int i = 1; i <= countOfStudent; i++) {
			int parentStudentIndex = findParentStudent(i);

			if (isVisitedStudent[parentStudentIndex] == true) {
				continue;
			}

			isVisitedStudent[parentStudentIndex] = true;
			countOfReligion += 1;
		}

		return countOfReligion;
	}

	private static void initParentStudent(int countOfStudent) {
		for (int i = 1; i <= countOfStudent; i++) {
			parentStudent[i] = i;
		}
	}

	private static void makeParentStudent(BufferedReader br, int relationOfStudent) throws IOException {
		for (int i = 0; i < relationOfStudent; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int firstStudent = Integer.parseInt(st.nextToken());
			int secondStudent = Integer.parseInt(st.nextToken());

			unionStudent(firstStudent, secondStudent);
		}
	}

	private static void unionStudent(int firstStudent, int secondStudent) {
		int firstParentStudent = findParentStudent(firstStudent);
		int secondParentStudent = findParentStudent(secondStudent);

		int tmpStudent = 0;
		if (firstParentStudent < secondParentStudent) {
			tmpStudent = firstParentStudent;
			firstParentStudent = secondParentStudent;
			secondParentStudent = tmpStudent;
		}

		parentStudent[firstParentStudent] = secondParentStudent;
	}

	private static int findParentStudent(int student) {
		Stack<Integer> studentStack = new Stack<Integer>();

		while (parentStudent[student] != student) {
			studentStack.add(student);
			student = parentStudent[student];
		}

		while (!studentStack.isEmpty()) {
			int childStudent = studentStack.pop();
			parentStudent[childStudent] = student;
		}

		return student;
	}

	private static void optimizeParentStudent(int countOfStudent) {
		for (int i = 1; i <= countOfStudent; i++) {
			findParentStudent(i);
		}
	}

}
