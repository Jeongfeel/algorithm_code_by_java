package code.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_1753_최단경로 {
	static class NodeInformation implements Comparable<NodeInformation> {
		int linkedNodeNumber;
		int weight;

		public NodeInformation(int linkedNodeNumber, int weight) {
			super();
			this.linkedNodeNumber = linkedNodeNumber;
			this.weight = weight;
		}

		@Override
		public int compareTo(NodeInformation o) {
			return this.weight - o.weight;
		}

	}

	private static final int INFINITY_NUMBER = Integer.MAX_VALUE;

	private static int V, E;
	private static int startNode;
	private static List<List<NodeInformation>> adjacentList = new ArrayList<List<NodeInformation>>();
	private static int distance[];
	private static boolean isVisitedNode[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		inputGraphData();
		makeDistance();
		printDistanceFromStartNode();
	}

	private static void inputGraphData() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startNode = Integer.parseInt(br.readLine());
		initDistanceArray();

		for (int i = 0; i <= V; i++) {
			adjacentList.add(new ArrayList<NodeInformation>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int startNode = Integer.parseInt(st.nextToken());
			int endNode = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjacentList.get(startNode).add(new NodeInformation(endNode, weight));
		}
	}

	private static void initDistanceArray() {
		distance = new int[V + 1];
		isVisitedNode = new boolean[V + 1];

		for (int i = 1; i <= V; i++) {
			distance[i] = INFINITY_NUMBER;
		}
	}

	private static void makeDistance() {
		PriorityQueue<NodeInformation> nodeInfoPQ = new PriorityQueue<NodeInformation>();
		nodeInfoPQ.add(new NodeInformation(startNode, 0));

		while (!nodeInfoPQ.isEmpty()) {
			NodeInformation nodeInformation = nodeInfoPQ.poll();
			int startNodeNumber = nodeInformation.linkedNodeNumber;
			int weight = nodeInformation.weight;

			if (isVisitedNode[startNodeNumber] == true) {
				continue;
			}
			if (distance[startNodeNumber] > weight) {
				distance[startNodeNumber] = weight;
			}
			isVisitedNode[startNodeNumber] = true;

			for (int i = 0; i < adjacentList.get(startNodeNumber).size(); i++) {
				int nextNodeNumber = adjacentList.get(startNodeNumber).get(i).linkedNodeNumber;
				int nextWeight = adjacentList.get(startNodeNumber).get(i).weight + weight;

				if (isVisitedNode[nextNodeNumber] == true) {
					continue;
				}
				if (distance[nextNodeNumber] < nextWeight) {
					continue;
				}

				nodeInfoPQ.add(new NodeInformation(nextNodeNumber, nextWeight));
			}
		}
	}

	private static void printDistanceFromStartNode() {
		for (int i = 1; i <= V; i++) {
			if (distance[i] == INFINITY_NUMBER) {
				System.out.println("INF");
			} else {
				System.out.println(distance[i]);
			}
		}
	}
}
