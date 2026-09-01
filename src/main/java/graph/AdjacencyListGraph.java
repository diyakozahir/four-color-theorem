package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adjacency List representation of Graph, using HashSet to represent the set of adjacent vectors.
 */
public class AdjacencyListGraph<T, S> extends Graph<T, S> {
    private final Map<T, AdjacencyListVertex<T, S>> vertices;

    public AdjacencyListGraph() {
        vertices = new HashMap<T, AdjacencyListVertex<T, S>>();
    }

    @Override
    boolean areAdjacent(T item1, T item2) throws GraphException {
        if (!vertices.containsKey(item1)) {
            throw new VertexNotExistException("Vertex " + item1 + " does not exist.");
        } else if (!vertices.containsKey(item2)) {
            throw new VertexNotExistException("Vertex " + item2 + " does not exist.");
        }
        return vertices.get(item1).isAdjacent(item2);
    }

    @Override
    List<T> getNeighbors(T item) throws GraphException {
        if (!vertices.containsKey(item)) {
            throw new VertexNotExistException("Vertex " + item + " does not exist.");
        }
        return vertices.get(item).getNeighbors();
    }

    @Override
    void addVertex(T item) throws GraphException {
        if (vertices.containsKey(item)) {
            throw new VertexAlreadyExistException("Vertex " + item + " already exists.");
        }
        vertices.put(item, new AdjacencyListVertex<T, S>(item));
    }

    @Override
    void addEdge(T item1, T item2) throws GraphException {
        if (!vertices.containsKey(item1)) {
            throw new VertexNotExistException("Vertex " + item1 + " does not exist.");
        } else if (!vertices.containsKey(item2)) {
            throw new VertexNotExistException("Vertex " + item2 + " does not exist.");
        }
        vertices.get(item1).addNeighbor(vertices.get(item2));
        vertices.get(item2).addNeighbor(vertices.get(item1));
    }

    @Override
    S getVertexValue(T item) throws GraphException {
        if (!vertices.containsKey(item)) {
            throw new VertexNotExistException("Vertex " + item + " does not exist.");
        }
        return vertices.get(item).getValue();
    }

    @Override
    S setVertexValue(T item, S value) throws GraphException{
        if (!vertices.containsKey(item)) {
            throw new VertexNotExistException("Vertex " + item + " does not exist.");
        }
        return vertices.get(item).setValue(value);
    }

    /**
     * Vertex class for AdjacencyListGraph.
     */
    private static class AdjacencyListVertex<T, S> {
        private final T item;
        private S value;
        private final List<AdjacencyListVertex<T, S>> neighbors;

        public AdjacencyListVertex(T item, S value, List<AdjacencyListVertex<T, S>> neighbours) {
            this.item = item;
            this.value = value;
            this.neighbors = neighbours;
        }

        public AdjacencyListVertex(T item, S value) {
            this(item, value, new ArrayList<AdjacencyListVertex<T, S>>());
        }

        public AdjacencyListVertex(T item) {
            this(item, null, new ArrayList<AdjacencyListVertex<T, S>>());
        }

        /**
         * Return the unique identifier of this vertex
         * @return the unique identifier of this vertex
         */
        public T getItem() {
            return item;
        }

        /**
         * Add the given vertex, neighbor, to the list of neighbors of this vertex if it is not already adjacent.
         * @param neighbor the vertex we want to add as a neighbor of this vertex
         * @throws GraphException if neighbor is already adjacent to this vertex
         */
        public void addNeighbor(AdjacencyListVertex<T, S> neighbor) throws GraphException {
            for (AdjacencyListVertex<T, S> vertex : neighbors) {
                if (vertex.getItem().equals(neighbor.getItem())) {
                    throw new GraphException("Vertex " + neighbor.getItem() + " already exists.");
                }
            }
            neighbors.add(neighbor);
        }

        /**
         * Return whether the given vertex identifier, item, is adjacent to this vertex.
         * @param item identifier for vertex
         * @return whether the given vertex identifier is adjacent to this vertex
         */
        public boolean isAdjacent(T item) {
            for (AdjacencyListVertex<T, S> vertex : neighbors) {
                if (vertex.getItem().equals(item)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Return list of vertex identifiers for vertices that are adjacent to this vertex.
         * @return list of vertex identifiers for vertices that are adjacent to this vertex.
         */
        public List<T> getNeighbors() {
            List<T> neighborItems = new ArrayList<>();
            for (AdjacencyListVertex<T, S> neighbor : neighbors) {
                neighborItems.add(neighbor.item);
            }
            return neighborItems;
        }

        /**
         * Return the value stored at this vertex, or null if no value is assigned.
         * @return the value stored at this vertex, or null if no value is assigned.
         */
        public S getValue() {
            return value;
        }

        /**
         * Set the stored value of this vertex to be the parameter value and return the previously stored value.
         * @param value the value to be stored at this vertex
         * @return the previously stored value, or null if no value was previously stored
         */
        public S setValue(S value) {
            S previousValue = this.value;
            this.value = value;
            return previousValue;
        }
    }

    /**
     * Exception for when an operation attempts to use a vertex that is not in the graph.
     */
    public static class VertexNotExistException extends GraphException {
        public VertexNotExistException() {
            super();
        }

        public VertexNotExistException(String message) {
            super(message);
        }
    }

    /**
     * Exception for an operation attempts to create/add a new vertex which is already in the graph.
     */
    public static class VertexAlreadyExistException extends GraphException {
        public VertexAlreadyExistException() {
            super();
        }

        public VertexAlreadyExistException(String message) {
            super(message);
        }
    }
}