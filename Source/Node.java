package uk.ac.bris.cs.gamekit.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A node in a {@link Graph}
 * 
 * @param <V> the type of the value this node holds
 */

@SuppressWarnings("serial")
public final class Node<V> implements Serializable{

	private final V value;
	
	private List<Node<V>> routeOfNodes = new ArrayList<>();
	
	//distance travelled from this node
	private int distanceTravelled = 0;
	
	
	
	public int getDistanceTravelled(){
		return distanceTravelled;
	}
	public void setDistanceTravelled(int distance){
		distanceTravelled = distanceTravelled + distance;
		
	}
	public List<Node<V>> getRouteOfNodes(){
		return Collections.unmodifiableList(routeOfNodes);
	}
	public void  addToRouteOfNodes(Node<V> node){
		routeOfNodes.add(node);
		
		
	}
	public void  clearRouteOfNodes(){
		routeOfNodes.clear();
		
		
	}
	
	
	public Node(V value) {
		this.value = Objects.requireNonNull(value);
	}

	/**
	 * @return the value this node holds, never null
	 */
	public V value() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node<?> node = (Node<?>) o;
		return Objects.equals(value, node.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return "Node(" + value + ")";
	}
}
