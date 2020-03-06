package GraphPack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Dijkstras {
	
	/*--IMPORTANT-- Each  Node contains the shortest route/path to that particular node
	 * and the distance of this route. Look at the changes to the Node Class to see
	 * I have kept in line with encapsulation by using getters and setters and making all fields
	 * private.
	 * and immutabillity by returning an unmodifiable list
	 */
	
	private static void computeDijkstrasAlgorithm(Graph<String, Integer> graph, Node<String> sourceNode, Node<String> destinationNode){		
		
		//The list of  all nodes in the graph
		List<Node<String>> allNodesToBeVisited = new ArrayList<>();
		
		//The best node to go next 
		// as in the node with smallest weight
		//can be considered the current node
		Node<String> bestNode;
				
		/*--REMEMBER-- Each  Node contains the shortest route/path to that particular node
		 * and the distance of this route
		 */
		//loops through every node in the graph
		// and would traditionally set the distance travelled of the shortest path 
		// to infinity but in this case i will set it to the number of nodes in the graph +1
		// as a shortest path will never exceed this value
		for(Node<String> currentNode : graph.getNodes()){
			
			currentNode.setDistanceTravelled(Integer.MAX_VALUE);
			
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
	private static Node<String> extractNodeWithSmallestDist(List<Node<String>>  listOfNodes){
		
		Node <String> bestNode = null;
		int lowestDist = Integer.MAX_VALUE;
		
		//loops through all the nodes in a given list
		//to find the node with the smallest distance travelled
		for (Node<String> currentNode :listOfNodes ){
			if(currentNode.getDistanceTravelled() < lowestDist ){
				lowestDist = currentNode.getDistanceTravelled();
				bestNode = currentNode;
			}
		}
		return bestNode;
	}
	
	private static  void visit(Node<String> node, Graph<String, Integer> graph){
		
		//contains the list of connected nodes to the one passed in 
		List<Node<String>> adjacentNodes = getAdjacentNodes(node,graph);
		int cost = 0;
		
		/*The method has already got all of the connected nodes from 
		 * the one initially passed in
		 * Then it loops through the list of the connected nodes 
		 * (in the order with the shortest distances)
		 */
		
		//take your time understanding this probably the most complex part
		for (Node<String> tempNode : adjacentNodes){
		
			Edge<String,  Integer> edge = graph.getEdge(node, tempNode);
			cost = edge.Data();

			if(tempNode.getDistanceTravelled() > tempNode.getDistanceTravelled() + cost){
				//if the travelling to this node is shorter than the distance already
				//travelled to get to this node we want to update the distance travelled
				tempNode.setDistanceTravelled(cost);
				
				//and we want to clear the previous shortest path to make a new one
				tempNode.clearRouteOfNodes();
				
				//now we take the node we are visiting
				//the one we have just passed in and we loop through
				//the shortest path it takes to get to that node
				//adding each node in its list to the current node's shortest path 
				for (int noNodes = 0; noNodes<node.getRouteOfNodes().size(); noNodes ++){
					tempNode.addToRouteOfNodes(node.getRouteOfNodes().get(noNodes));
				}
				
				// add the node we are actually visiting as well
				tempNode.addToRouteOfNodes(node);			
			}
		}	
	}
	
	private static List<Node<String>> getAdjacentNodes(Node<String> sourceNode , Graph<String, Integer> graph){
		//refers to the all the edges connected to the source node
		Collection<Edge<String, Integer>>  adjacentEdges = graph.getEdgesFrom(sourceNode);
				
		//contains the list of connected nodes to the one passed in 
		List<Node<String>> adjacentNodes = new ArrayList<>();
		
		//adds all of the nodes attached to the node passed to it
		for (Edge<String, Integer> edge : adjacentEdges){
		      adjacentNodes.add(edge.destination());
		}
		
		return adjacentNodes;
	}

	//quick function which returns the smallest distance
	public static int shortestDistBetweenTwoNodes(Graph<String, Integer> graph  ,Node<String> source,Node<String> destination){
		computeDijkstrasAlgorithm(graph,source,destination);
		return destination.getDistanceTravelled();
	}
	
	//returns the shortest route between two nodes in the form of a list of nodes
	public static List<Node<String>> shortestRouteBetweenTwoNodes(Graph<String, Integer> graph  ,Node<String> source,Node<String> destination){
		computeDijkstrasAlgorithm(graph,source,destination);
		return destination.getRouteOfNodes();
	}
	
}
