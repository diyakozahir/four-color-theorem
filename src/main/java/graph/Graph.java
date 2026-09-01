package graph;

import java.util.List;

/**
 * Abstract class for undirected Graphs.
 * @param <T> the type used as the unique identifier of vertices
 * @param <S> the type used for storing a secondary value at a vertex
 */
public abstract class Graph<T, S> {
    /**
     * Return whether item1 and item2 are adjacent.
     * @param item1 a unique identifier for a vertex
     * @param item2 a unique identifier for a vertex
     * @return true if item1 and item2 are adjacent and false otherwise
     * @throws GraphException if item1 or item2 is not an identifier for any vertex in the graph
     */
    abstract boolean areAdjacent(T item1, T item2) throws GraphException;

    /**
     * Return all the neighbors of item (a neighbor is an adjacent vertex).
     * @param item a unique identifier for a vertex
     * @return the unique identifiers of all vertices that are adjacent to item
     * @throws GraphException if item is not an identifier for any vertex in the graph
     */
    abstract List<T> getNeighbors(T item) throws GraphException;

    /**
     * Adds a vertex with the given unique identifier, item, to the graph.
     * @param item the unique identifier of the new vertex
     * @throws GraphException if a vertex with the identifier item already exists in the graph
     */
    abstract void addVertex(T item) throws GraphException;


    abstract void addEdge(T item, T value) throws GraphException;

    /**
     * Returns the value stored at vertex item, or null if no value is assigned to this vertex.
     * @param item the unique identifier of the vertex
     * @return the value stored at vertex item, or null if no value is assigned to this vertex
     * @throws GraphException if item is not an identifier for any vertex in the graph
     */
    abstract S getVertexValue(T item) throws GraphException;

    /**
     * Sets the value stored at vertex item.
     * @param item the unique identifier of the vertex
     * @param value the value to store at the vertex
     * @return the previous value stored at vertex item, or null if no previous value was assigned
     * @throws GraphException if item is not an identifier for any vertex in the graph
     */
    abstract S setVertexValue(T item, S value) throws GraphException;

    /**
     * Checked exception for missuses of the Graph class.
     */
    public static class GraphException extends Exception {
        public GraphException() {
            super();
        }

        public GraphException(String message) {
            super(message);
        }

        public GraphException(String message, Throwable cause) {
            super(message, cause);
        }

        public GraphException(Throwable cause) {
            super(cause);
        }
    }
}