package com.graphInterface;

import org.graphstream.graph.Graph;

public interface GraphManipulator {
	public Graph initiateGraph(Graph graph, RandomGen randomNumGen, int min_node, int max_node);
	public Graph graphCal(Graph graph);
}
