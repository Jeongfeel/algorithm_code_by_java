package code.algorithm.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWExpert_3124_최소_스패닝_트리 {
	static class NodeInformation implements Comparable<NodeInformation> {
		int nodeNumber;
		int weight;

		public NodeInformation(int nodeNumber, int weight) {
			super();
			this.nodeNumber = nodeNumber;
			this.weight = weight;
		}

		@Override
		public int compareTo(NodeInformation o) {
			return this.weight - o.weight;
		}
	}

	private static PriorityQueue<NodeInformation> nodePQ;
	private static List<List<NodeInformation>> adjacentList;
	private static boolean isVisitedNode[];
	private static int V, E;

	private static BufferedReader br;
	private static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int countOfTestcase = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= countOfTestcase; testcase++) {
			inputGraphData();
			long minimumSumOfWeight = makeMinimumSumOfWeight();
			System.out.println("#" + testcase + " " + minimumSumOfWeight);
		}
	}

	private static void inputGraphData() throws IOException {
		nodePQ = new PriorityQueue<NodeInformation>();

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjacentList = new ArrayList<List<NodeInformation>>();
		for (int i = 0; i <= V; i++) {
			adjacentList.add(new ArrayList<NodeInformation>());
		}
		isVisitedNode = new boolean[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int startNode = Integer.parseInt(st.nextToken());
			int endNode = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjacentList.get(startNode).add(new NodeInformation(endNode, weight));
			adjacentList.get(endNode).add(new NodeInformation(startNode, weight));
		}
	}

	private static long makeMinimumSumOfWeight() {
		long sumOfWeight = 0;
		int countOfNode = 0;
		nodePQ.add(new NodeInformation(1, 0));
		while (!nodePQ.isEmpty()) {
			NodeInformation nodeInformation = nodePQ.poll();
			int nodeNumber = nodeInformation.nodeNumber;
			int weight = nodeInformation.weight;

			if (isVisitedNode[nodeNumber] == true) {
				continue;
			}

			countOfNode += 1;
			sumOfWeight += weight;
			isVisitedNode[nodeNumber] = true;
			if (countOfNode == V) {
				break;
			}

			for (int i = 0; i < adjacentList.get(nodeNumber).size(); i++) {
				int nextNode = adjacentList.get(nodeNumber).get(i).nodeNumber;
				int nextWeight = adjacentList.get(nodeNumber).get(i).weight;

				if (isVisitedNode[nextNode] == true) {
					continue;
				}

				nodePQ.add(new NodeInformation(nextNode, nextWeight));
			}
		}

		return sumOfWeight;
	}

}
