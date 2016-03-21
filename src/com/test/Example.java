package com.test;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import com.graphImpl.GraphManipulatorImpl;
import com.graphImpl.RandomGenImpl;
import com.graphInterface.GraphManipulator;
import com.graphInterface.RandomGen;


public class Example {

	
	public static void main(String args[]) {
		
		//Configuration Parameters***********************
		int min_node = 20;
		int max_node = 60;
		String graphName = "reThink";
		//***********************************************
		
		RandomGen randomNumGen = new RandomGenImpl();
		GraphManipulator graphMan = new GraphManipulatorImpl();
		/* creating graph */
		Graph graph = new SingleGraph(graphName);
		/* Generating Nodes */
		graph = graphMan.initiateGraph(graph, randomNumGen, min_node, max_node);
		/* creating new edges based on interests */
		graph = graphMan.graphCal(graph);
		graph.display();
	}
}
