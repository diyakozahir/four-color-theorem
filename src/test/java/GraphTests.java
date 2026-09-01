import graph.Graph;
import graph.AdjacencyListGraph;
import graph.Graph.GraphException;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTests {
    @Test
    @DisplayName("Basic Adjacency List Graph Tests")
    void basicAdjacencyListGraphTest() {
        final Graph<Integer, Color> graph = new AdjacencyListGraph<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertAll("adjacency",
                () -> assertTrue(graph.areAdjacent(1, 2)),
                () -> assertTrue(graph.areAdjacent(2, 1)),
                () -> assertTrue(graph.areAdjacent(2, 3)),
                () -> assertTrue(graph.areAdjacent(3, 2)),
                () -> assertFalse(graph.areAdjacent(1, 3)),
                () -> assertFalse(graph.areAdjacent(3, 1)),
                () -> assertFalse(graph.areAdjacent(1, 1)),
                () -> assertFalse(graph.areAdjacent(2, 2)),
                () -> assertFalse(graph.areAdjacent(3, 3)));

        Set<Integer> neighborsOfOne = new HashSet<>(graph.getNeighbors(1));
        Set<Integer> neighborsOfTwo = new HashSet<>(graph.getNeighbors(2));
        Set<Integer> neighborsOfThree = new HashSet<>(graph.getNeighbors(3));

        Set<Integer> expectedNeighborsOne = Set.of(2);
        Set<Integer> expectedNeighborsTwo = Set.of(1, 3);
        Set<Integer> expectedNeighborsThree = Set.of(2);

        assertAll("neighbors",
                () -> assertEquals(neighborsOfOne, expectedNeighborsOne),
                () -> assertEquals(neighborsOfTwo, expectedNeighborsTwo),
                () -> assertEquals(neighborsOfThree, expectedNeighborsThree));

        graph.setVertexValue(1, Color.RED);
        graph.setVertexValue(2, Color.BLUE);
        assertAll("values",
                () -> assertEquals(Color.RED, graph.getVertexValue(1)),
                () -> assertEquals(Color.BLUE, graph.getVertexValue(2)),
                () -> assertNull(graph.getVertexValue(3)));
    }

    @Test
    @DisplayName("Adjacency List Graph Exceptions Tests")
    void adjacencyListGraphExceptionsTest() {
        final Graph<Integer, Color> graph = new AdjacencyListGraph<>();

        graph.addVertex(1);
        graph.addVertex(2);

        assertAll(
                () -> assertThrows(GraphException.class, () -> graph.addVertex(1)),
                () -> assertThrows(GraphException.class, () -> graph.addEdge(1, 3)),
                () -> assertThrows(GraphException.class, () -> graph.areAdjacent(1, 3)),
                () -> assertThrows(GraphException.class, () -> graph.getNeighbors(3)),
                () -> assertThrows(GraphException.class, () -> graph.getVertexValue(3)),
                () -> assertThrows(GraphException.class, () -> graph.setVertexValue(3, Color.RED)));
    }
}
