package code.algorithm.simulation;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baekjoon_2493_탑 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int countOfTop = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Point> topStack = new Stack<Point>();
		for (int i = 1; i <= countOfTop; i++) {
			int topHeight = Integer.parseInt(st.nextToken());

			if (topStack.isEmpty()) {
				System.out.print(0 + " ");
				topStack.add(new Point(topHeight, i));
				continue;
			}

			while (!topStack.isEmpty() && topStack.peek().x < topHeight) {
				topStack.pop();
			}

			if (topStack.isEmpty()) {
				System.out.print(0 + " ");
				topStack.add(new Point(topHeight, i));
			} else {
				System.out.print(topStack.peek().y + " ");
				topStack.add(new Point(topHeight, i));
			}
		}
	}
}
