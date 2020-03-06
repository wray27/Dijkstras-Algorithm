
import java.util.ArrayList;
import java.lang.String;
import java.util.List;

import GraphPack.*;

public class Main {
  
    public static void main(String[] args){

        UndirectedGraph<String, Integer> graph = simpleGraph();

        /* Argument passing -g option is used to select a graph
           The default graph is the simpleGraph*/
        if (args[0] == "-g"){
            switch (args[1]) {
                
                case "1":
                    System.out.println("Example 1: A simple graph has been chosen.");
                    break;

                case "2":
                    graph = mediumGraph();
                    System.out.println("Example 2: A medium level graph has been chosen.");
                    break;

                case "3":
                    graph = hardGraph();
                    System.out.println("Example 3: A hard level graph has been chosen.");
                    break;

                default:
                    System.out.println("Example 1: A simple graph has been chosen.");
                    break;
            }

        }else{
            System.out.println("Example 1: Simple Graph has been chosen.");
        }
        
        // store the two nodes inputted by the user and then display the answer
        ArrayList<String> nodes = getUserInput();
        displayAnswer(nodes, graph);

    }

    // function to retrive the user input of two strings from the terminal stored in a list
    private static ArrayList<String> getUserInput(){
        
        // retrieves the name of the nodes from the user 
        System.out.println("Please enter the name of the source node:");
        String sourceName = System.console().readLine();
        
        System.out.println("Please enter the name of the destination node:");
        String destinationName = System.console().readLine();

        // adding the names to a list
        ArrayList<String> nodes = new ArrayList<String>();
        nodes.add(sourceName);
        nodes.add(destinationName);
        
        return nodes;
    }

    private static void displayAnswer(ArrayList<String> nodes, UndirectedGraph<String, Integer> graph){
        
        // finds the node objects in the graph from the user input
        Node<String> source = graph.getNode(nodes.get(0));
        Node<String> destination = graph.getNode(nodes.get(1));

        // runs the dijkstras algorithm code to find shortest route and its cost
        int cost = Dijkstras.shortestDistBetweenTwoNodes(graph, source, destination);
        List<Node<String>> route = Dijkstras.shortestRouteBetweenTwoNodes(graph, source, destination);

        // Creating the route in a printable format
        String printRoute = "";
        for (Node<String> temp : route){
        printRoute =  printRoute + temp.value() + " → ";
        }
        printRoute = printRoute + destination.value();

        // printing the shortest route and its distance to the console
        System.out.println("");
        System.out.println("The shortest route from " +  nodes.get(0) + " to " + nodes.get(1) + " is " + printRoute);
        System.out.println("The cost of this route is " + Integer.toString(cost));
    
        
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
        Edge<String, Integer> aToB = new Edge<String, Integer>(A, B, 1);
        graph.addEdge(aToB);
        Edge<String, Integer> bToD = new Edge<String, Integer>(B, D, 3);
        graph.addEdge(bToD);
        Edge<String, Integer> dToC = new Edge<String, Integer>(D, C, 1);
        graph.addEdge(dToC);
        Edge<String, Integer> cToA = new Edge<String, Integer>(C, A, 2);
        graph.addEdge(cToA);
        Edge<String, Integer> aToD = new Edge<String, Integer>(A, D, 9);
        graph.addEdge(aToD);

        return graph;
    }

    // Example 2:
    // A medium level graph used to show the algorithm 
    private static UndirectedGraph<String, Integer> mediumGraph(){
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
        Node<String> E = new Node<String>("E");
        graph.addNode(E);
        Node<String> F = new Node<String>("F");
        graph.addNode(F);

        /* instatiating edges between nodes and adding them to the undirected graph */
        Edge<String, Integer> aToB = new Edge<String, Integer>(A, B, 7);
        graph.addEdge(aToB);
        Edge<String, Integer> bToD = new Edge<String, Integer>(B, D, 2);
        graph.addEdge(bToD);
        Edge<String, Integer> dToC = new Edge<String, Integer>(D, C, 11);
        graph.addEdge(dToC);
        Edge<String, Integer> cToA = new Edge<String, Integer>(C, A, 6);
        graph.addEdge(cToA);
        Edge<String, Integer> eToD = new Edge<String, Integer>(E, D, 9);
        graph.addEdge(eToD);
        Edge<String, Integer> eToF = new Edge<String, Integer>(E, F, 7);
        graph.addEdge(eToF);
        Edge<String, Integer> dToF = new Edge<String, Integer>(D, F, 10);
        graph.addEdge(dToF);

        return graph;
    }

    // Example 3:
    // A hard level graph used to show the algorithm 
    private static UndirectedGraph<String, Integer> hardGraph(){
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
        Node<String> E = new Node<String>("E");
        graph.addNode(E);
        Node<String> F = new Node<String>("F");
        graph.addNode(F);
        Node<String> G = new Node<String>("G");
        graph.addNode(G);
        Node<String> H = new Node<String>("H");
        graph.addNode(H);
        Node<String> I = new Node<String>("I");
        graph.addNode(I);

        /* instatiating edges between nodes and adding them to the undirected graph */
        Edge<String, Integer> aToB = new Edge<String, Integer>(A, B, 7);
        graph.addEdge(aToB);
        Edge<String, Integer> bToD = new Edge<String, Integer>(B, D, 3);
        graph.addEdge(bToD);
        Edge<String, Integer> bToG = new Edge<String, Integer>(B, G, 7);
        graph.addEdge(bToG);
        Edge<String, Integer> dToG = new Edge<String, Integer>(D, G, 3);
        graph.addEdge(dToG);
        Edge<String, Integer> cToA = new Edge<String, Integer>(C, A, 6);
        graph.addEdge(cToA);
        Edge<String, Integer> eToC = new Edge<String, Integer>(E, C, 9);
        graph.addEdge(eToC);
        Edge<String, Integer> eToH = new Edge<String, Integer>(E, H, 5);
        graph.addEdge(eToH);
        Edge<String, Integer> cToH = new Edge<String, Integer>(C, H, 11);
        graph.addEdge(cToH);
        Edge<String, Integer> cToF = new Edge<String, Integer>(C, F, 9);
        graph.addEdge(cToF);
        Edge<String, Integer> fToH = new Edge<String, Integer>(F, H, 10);
        graph.addEdge(fToH);
        Edge<String, Integer> hToI = new Edge<String, Integer>(H, I, 2);
        graph.addEdge(hToI);
        Edge<String, Integer> gToI = new Edge<String, Integer>(G, I, 4);
        graph.addEdge(gToI);
        Edge<String, Integer> gToH = new Edge<String, Integer>(G, H, 8);
        graph.addEdge(gToH);

        return graph;
    }




}   