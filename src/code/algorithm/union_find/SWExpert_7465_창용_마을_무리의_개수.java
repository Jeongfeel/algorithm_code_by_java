package code.algorithm.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class SWExpert_7465_창용_마을_무리의_개수 {
    private static final int MAXIMUM_PEOPLE = 100 + 1;
    private static int peopleParent[] = new int[MAXIMUM_PEOPLE];
    private static boolean isVistedPeople[] = new boolean[MAXIMUM_PEOPLE];
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int testcase = Integer.parseInt(br.readLine());
 
        for (int i = 1; i <= testcase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            int countOfPeople = Integer.parseInt(st.nextToken());
            int countOfRelation = Integer.parseInt(st.nextToken());
 
            initPeopleParent(countOfPeople);
            makeRelation(br, countOfRelation);
            int countOfPeopleUnion = getCountOfPeopleUnion(countOfPeople);
 
            System.out.println("#"+ i+" "+countOfPeopleUnion);
        }
    }
 
    private static void initPeopleParent(int countOfPeople) {
        for (int i = 1; i <= countOfPeople; i++) {
            peopleParent[i] = i;
            isVistedPeople[i] = false;
        }
    }
 
    private static void makeRelation(BufferedReader br, int countOfRelation) throws IOException {
        for (int i = 0; i < countOfRelation; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            int firstPeople = Integer.parseInt(st.nextToken());
            int secondPeople = Integer.parseInt(st.nextToken());
 
            unionPeople(firstPeople, secondPeople);
        }
    }
 
    private static void unionPeople(int firstPeople, int secondPeople) {
        int firstPeopleParent = findParent(firstPeople);
        int secondPeopleParent = findParent(secondPeople);
 
        if (firstPeopleParent == secondPeopleParent) {
            return;
        }
 
        peopleParent[firstPeopleParent] = secondPeopleParent;
    }
 
    private static int findParent(int people) {
        Stack<Integer> peopleStack = new Stack<Integer>();
        while (people != peopleParent[people]) {
            peopleStack.add(people);
            people = peopleParent[people];
        }
 
        while (!peopleStack.isEmpty()) {
            peopleParent[peopleStack.pop()] = people;
        }
 
        return people;
    }
 
    private static int getCountOfPeopleUnion(int countOfPeople) {
        int countOfPeopleUnion = 0;
 
        for (int i = 1; i <= countOfPeople; i++) {
            int peopleUnionIndex = findParent(i);
 
            if (isVistedPeople[peopleUnionIndex] == true) {
                continue;
            }
 
            isVistedPeople[peopleUnionIndex] = true;
            countOfPeopleUnion += 1;
        }
 
        return countOfPeopleUnion;
    }
 
}
