import GraphPack.*;

import java.util.ArrayList;
import java.lang.String;
import java.util.Collection.*;
import java.util.List;

public class Main {
  public static void main(String[] args){
    
  }

  // function to retrive the user input of two strings from the terminal stored in a list
  private static ArrayList<String> getUserInput(){
    
    // retrieves the name of the nodes from the user 
    System.out.println("Please enter the name of the starting node:");
    String sourceName = System.console().readLine();
    
    System.out.println("Please enter the name of the destination node:");
    String destinationName = System.console().readLine();

    // adding the names to a list
    ArrayList<String> nodes = new ArrayList<String>();
    nodes.add(sourceName);
    nodes.add(destinationName);
    
    return nodes;
  }
  private static void displayAnswer(List<String> nodes, UndirectedGraph<String, Integer> graph){
    
    // finds the node objects in the graph from the user input
    Node<String> source = graph.getNode(nodes.get(0));
    Node<String> destination = graph.getNode(nodes.get(1));

    // runs the dijkstras algortihm code to find shortest route and its cost 
    int cost = -1;
    cost = Dijkstras.shortestDistanceBetweenTwoNodes(graph, source, destination);
    List<<Node<String>> routes = Dijkstras.shortestRouteBetweenTwoNodes(graph, source, destination);
   
    
  }

  // Example 1:
  // A simple graph used to show the algorithm 
  private static UndirectedGraph<String, Integer> simpleGraph(){
    
    // instantiating a new undirected graph
    UndirectedGraph<String, Integer> graph = new UndirectedGraph<String, Integer>();
  
    // instantiating nodes labelled A, B, C, D and adds them to the graph
    Node<String> A = new Node<String>("A");
    graph.addNode(A);
    
    Node<String> B = new Node<String>("B");
    graph.addNode(B);
    
    Node<String> C = new Node<String>("C");
    graph.addNode(C);
    
    Node<String> D = new Node<String>("D");
    graph.addNode(D);

    /* instatiating edges between nodes and adding them to the undirected graph */
    Edge<String, Integer> aToB = new Edge<String, Integer>(A, B, 5);
    graph.addEdge(aToB);
    
    Edge<String, Integer> bToD = new Edge<String, Integer>(B, D, 4);
    graph.addEdge(bToD);
    
    Edge<String, Integer> dToC = new Edge<String, Integer>(D, C, 8);
    graph.addEdge(dToC);
    
    Edge<String, Integer> cToA = new Edge<String, Integer>(C, A, 1);
    graph.addEdge(cToA);
    
    Edge<String, Integer> aToD = new Edge<String, Integer>(A, D, 9);
    graph.addEdge(aToD);

    return graph;
  }




}