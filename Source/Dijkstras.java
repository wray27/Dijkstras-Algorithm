package uk.ac.bris.cs.gamekit.graph;


import java.util.ArrayList;
import java.util.Collection;

import java.util.List;


import uk.ac.bris.cs.gamekit.graph.Edge;
import uk.ac.bris.cs.gamekit.graph.Graph;
import uk.ac.bris.cs.gamekit.graph.Node;


import uk.ac.bris.cs.scotlandyard.model.Transport;

public class Dijkstras {
	
	
	/*--IMPORTANT-- Each  Node contains the shortest route/path to that particular node
	 * and the distance of this route. Look at the changes to the Node Class to see
	 * I have kept in line with encapsulation by using getters and setters and making all fields
	 * private.
	 * and immutabillity by returning an unmodifiable list
	 */
	
	
	private static void computeDijkstrasAlgorithm(Graph<Integer,Transport> graph, Node<Integer> sourceNode,Node<Integer> destinationNode){
		
		
		//The list of  all nodes in the graph
		List<Node<Integer>> allNodesToBeVisited = new ArrayList<>();
		
		
		
		
		//The best node to go next 
		// as in the node with smallest weight
		//can be considered the current node
		Node<Integer> bestNode;
		
		
		
		/*--REMEMBER-- Each  Node contains the shortest route/path to that particular node
		 * and the distance of this route
		 */
		//loops through every node in the graph
		// and would traditionally set the distance travelled of the shortest path 
		// to infinity but in this case i will set it to the number of nodes in the graph +1
		// as a shortest path will never exceed this value
		for(Node<Integer> currentNode : graph.getNodes()){
			
			currentNode.setDistanceTravelled(graph.size()+1);
			
			//clears the shortest route for all nodes inside of graph
			currentNode.clearRouteOfNodes();
			
			//adds all of the nodes in the graph into a set
			allNodesToBeVisited.add(currentNode);
		}
		//sets the source Node's distance travelled to 0 
		//essentially so algorithm knows where to start 
		sourceNode.setDistanceTravelled(0);
		
		
		//used to help find the bestNode to travel to next in the graph
		// and then visit it
		while(allNodesToBeVisited.size() != 0){
			//finds the best Node to travel to next
			bestNode = extractNodeWithSmallestDist(allNodesToBeVisited);
			//visits this node
			visit(bestNode, graph);
			//removes it from the list, once this list is empty the algorithm is done
			allNodesToBeVisited.remove(bestNode);
				
		}
		
		//the route e.g the actual list of nodes to get from A To B will be in the 
		//reference to the Destination Nodes Route attribute ( which is just a list)
		// the actual shortest distance will be the  size of the list
		// or the field distance travelled, which is also a field in Destination Node
		
		
		
		
		
				
	}






	
	
	//this function is used to split up the code 
	//finds the node with the smallest distance to travel to next
	private static Node<Integer> extractNodeWithSmallestDist(List<Node<Integer>>  listOfNodes){
		
		Node <Integer> bestNode = new Node<Integer>(0);
		//this part here needs to be changed
		int lowestDist = 100000;
		
		//loops through all the nodes in a given list
		//to find the node with the smallest distance travelled
		for (Node<Integer> currentNode :listOfNodes ){
			if(currentNode.getDistanceTravelled() <lowestDist ){
				lowestDist = currentNode.getDistanceTravelled();
				bestNode = currentNode;
			}
		}
		return bestNode;
	}
	
	private static  void visit(Node<Integer> node, Graph<Integer,Transport> graph){
		
		
		//contains the list of connected nodes to the one passed in 
		List<Node<Integer>> adjacentNodes = getAdjacentNodes(node,graph);
		
		//as graph is undirected the cost is 1 and will always be 1
		final int cost = 1;
		
		/*The method has already got all of the connected nodes from 
		 * the one initially passed in
		 * Then it loops through the list of the connected nodes 
		 * (in no particular order as the graph is unweighted)
		 */
		
		//take your time understanding this probably the most complex part
		for (Node<Integer> tempNode : adjacentNodes){
			
			if(tempNode.getDistanceTravelled() > tempNode.getDistanceTravelled() + cost){
				//if the travelling to this node is shorter than the distance already
				//travelled to get to this node we want to update the distance travelled
				tempNode.setDistanceTravelled(cost);
				
				//and we want to clear the previous shortest path to make a new one
				tempNode.clearRouteOfNodes();
				
				
				//now we take the node we are visiting
				//the one we  have just passed in and we  loop through
				// the shortest path it takes to get to that node
				//adding each node in its list to the current node's shortest path 
				for (int noNodes = 0;noNodes<node.getRouteOfNodes().size(); noNodes ++){
					tempNode.addToRouteOfNodes(node.getRouteOfNodes().get(noNodes));
				}
				
				// we cannot forget to add the node we are actually visiting as well
				tempNode.addToRouteOfNodes(node);
				
			}
		}
		
	}
	
	
	
	
	
	
	private static List<Node<Integer>> getAdjacentNodes(Node<Integer> sourceNode , Graph<Integer,Transport> graph){
		//refers to the all the edges connected to the source node
		Collection<Edge<Integer,Transport>>  adjacentEdges = graph.getEdgesFrom(sourceNode);
				
		//contains the list of connected nodes to the one passed in 
		List<Node<Integer>> adjacentNodes = new ArrayList<>();
		
		//adds all of the nodes attached to the node passed to it
		for (Edge<Integer,Transport> edge : adjacentEdges){
		      adjacentNodes.add(edge.destination());
		}
		
		 
		//removes all boat edges as detectives cannot traverse these
		for (Edge<Integer,Transport> edge : adjacentEdges){
			if (edge.data().equals(Transport.Boat) ){
				adjacentEdges.remove(edge);
			}
		}
		
		return adjacentNodes;
	}
	
	//These last two methods are tidy up functions
	//they are the only ones with public access
	
	//quick function which returns the smallest distance
	public static int shortestDistBetweenTwoNodes(Graph<Integer,Transport> graph  ,Node<Integer> source,Node<Integer> destination){
		computeDijkstrasAlgorithm(graph,source,destination);
		return destination.getDistanceTravelled();
	}
	
	//returns the shortest route between two nodes in the form of a list of nodes
	public static List<Node<Integer>> shortestRouteBetweenTwoNodes(Graph<Integer,Transport> graph  ,Node<Integer> source,Node<Integer> destination){
		computeDijkstrasAlgorithm(graph,source,destination);
		return destination.getRouteOfNodes();
	}

	
}
