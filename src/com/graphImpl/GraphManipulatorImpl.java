package com.graphImpl;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import com.graphInterface.GraphManipulator;
import com.graphInterface.RandomGen;



public class GraphManipulatorImpl implements GraphManipulator{
	public Graph initiateGraph(Graph graph, RandomGen randomNumGen, int min_node, int max_node){
		//graph.addAttribute("ui.stylesheet", " node {fill-color: blue; }");
		graph.addAttribute("ui.stylesheet", " node:clicked {fill-color: red;}");
		int node_random = randomNumGen.generateRandomRange(min_node,max_node);
		System.out.println("Number of Nodes " + node_random);
		//Adding attributes to the nodes
		for(int node_i=1; node_i<node_random; node_i++){
			String user_name = "user"+node_i;
			List<String> interests = new ArrayList<String>();
			if(node_i%17 ==0){
				interests.add("football");
				interests.add("Anger Management");
			}else if(node_i%23 == 0){
				interests.add("tennis");
				interests.add("The Sopranos");
				interests.add("Hello");
			}else if(node_i%11 == 0){
				interests.add("tennis");
				interests.add("Serendipity");
				interests.add("Hello");
			}else{
				interests.add("Game of Thrones");
			}
			graph.addNode(user_name).addAttribute("ui.label", user_name);
			graph.getNode(user_name).setAttribute("id", node_i);
			graph.getNode(user_name).setAttribute("interests", interests );
			//graph.getNode(user_name).addAttribute("ui.style", "node:clicked {fill-color: red;}");
		}

		//Adding random edges
		int edge_random = randomNumGen.generateRandomRange(20,40);
		int edge_count = 0;
		for (int edge_i=1;edge_i<edge_random; edge_i++){
			int node1 = randomNumGen.generateRandomRange(1,node_random-1);
			int node2 = randomNumGen.generateRandomRange(1,node_random-1);
			String edgeName = "user"+node1+"user"+node2;
			String edgeDupName = "user"+node2+"user"+node1;

			if(node1!=node2){
				Edge e = graph.getEdge(edgeName);
				Edge eDup = graph.getEdge(edgeDupName);
				if(e == null && eDup == null){

					edge_count++;
					graph.addEdge(edgeName, "user"+node1, "user"+node2);
				}else{

					System.out.println("****Edge Not Created");
				}	
			}
			else{
				System.out.println("****Two random numers are same");
			}
		}
		System.out.println("Number of edges "+edge_count);
		return graph;
	}
	//Adding new edges based on the common interests
	public Graph graphCal(Graph graph){
		for(Node n1:graph) {
			List<String> node1Interest = new ArrayList<String>(n1.getAttribute("interests"));
			for(Node n2:graph){

					List<String> commonList = new ArrayList<String>(node1Interest);
					List<String> node2Interest = new ArrayList<String>(n2.getAttribute("interests"));

					commonList.retainAll(node2Interest);					
					if(commonList.size()>=2){
						String new_edge = (String)n1.getAttribute("ui.label")+""+(String)n2.getAttribute("ui.label");
						String new_edge_Dup = (String)n2.getAttribute("ui.label")+""+(String)n1.getAttribute("ui.label");
						
						
						Edge e = graph.getEdge(new_edge);
						Edge eNewDup = graph.getEdge(new_edge_Dup);
						if(e==null && eNewDup==null && !new_edge.equals(new_edge_Dup)){
							String id = graph.addEdge(new_edge, (String)n1.getAttribute("ui.label"), (String)n2.getAttribute("ui.label")).getId();
							graph.getEdge(id).addAttribute("ui.style", "fill-color: red;");
							System.out.println("New edge is created between "+n1.getId()+" and "+n2.getId());
						}
					}
			}
		}
		return graph;
	}
}
