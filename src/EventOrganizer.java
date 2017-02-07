import java.util.ArrayList;
import java.util.Arrays;

/**
 * Recursive DFS concept taken from
 * https://algocoding.wordpress.com/2014/08/25/depth-first-search-java-and-
 * python-implementation Concept for Edge Creation & Adjacency List Taken From
 * http://web.stanford.edu/class/cs97si/06-basic-graph-algorithms.pdf
 * 
 * @author Nitish Gupta
 *
 */
public class EventOrganizer {
	int ccsize;
	boolean[] visited;
	Edge[] edgeArray;

	/**
	 * 
	 * @author NitishGupta
	 *
	 */
	class Edge {
		int to;
		Edge nextID;

		Edge(int from, int to) {
			this.to = to;
			this.nextID = edgeArray[from];
			edgeArray[from] = this;
		}

		@Override
		public String toString() {
			return "Edge [to=" + to + "]";
		}

	}

	/**
	 * 
	 * @param vertice
	 */
	void depthFirst(int vertice) {
		++ccsize;
		visited[vertice] = true;
		for (Edge edge = edgeArray[vertice]; edge != null; edge = edge.nextID) {
			if (!visited[edge.to]) {
				depthFirst(edge.to);
			}
		}
	}

	/**
	 * 
	 * @param n
	 * @param k
	 * @param x
	 * @param y
	 * @return
	 */
	public String organizeEvent(int n, int k, int[] x, int[] y) {
		ArrayList<Integer> sizeAvailable = new ArrayList<Integer>();
		if (k > n) {
			return "Impossible";
		}
		edgeArray = new Edge[n];
		visited = new boolean[n];
		// create an edge array with current edge to next edge and
		// fromEdge(L.H.S)->toEdge(R.H.S)=>EDGE(current)
		for (int i = 0; i < x.length; i++) {
			new Edge(x[i], y[i]);
			new Edge(y[i], x[i]);
		}
		// for (int i = 0; i < edgeArray.length; i++) {
		// if (edgeArray[i] != null) {
		// System.out.println(i + "\tto\t" + edgeArray[i].to + "\tnextId\t" +
		// edgeArray[i].nextID);
		//
		// }
		// }

		// call dfs algorithm
		for (int i = 0; i < n; ++i) {
			if (!visited[i]) {
				ccsize = 0;
				depthFirst(i);
				sizeAvailable.add(ccsize);
			}
		}
		// System.out.println(list);
		boolean[] result = new boolean[n + 1];
		result[0] = true;
		int i = 0;
		// apply dynamic programming
		for (i = 0; i < sizeAvailable.size(); i++) {
			for (int j = n; j >= 0; --j) {
				if (result[j] && j + sizeAvailable.get(i) <= n) {
					result[j + sizeAvailable.get(i)] = true;
				}
			}

		}
		if (result[k] == true)
			return "Possible";
		else
			return "Impossible";

	}

	/**
	 * 
	 * @param o
	 *            debug to test the objects created in Memory
	 */
	public static void debug(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

}
