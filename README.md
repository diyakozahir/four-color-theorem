# Four Color Theorem

The [four colour theorem](https://en.wikipedia.org/wiki/Four_color_theorem) states that at most four colours are needed to colour the regions of any given map such that no adjacent regions have the same colour.
It is a famous problem, known for being the first computer verified theorem.

## Formal Statement

A more precise formulation follows, using graph theory.
The theorem is equivalent to stating that for any *loopless* *planar* graph, $G= (V, E)$,
its *chromatic number* (denoted by the function $\chi$) has the property $\chi(G) \leq 4$.
- a graph is loopless if there is no vertex $v \in V$ such that $\{ v \} \in E$.
- a graph is planar if it can be drawn such that its edges only intersect at their endpoints.
- the chromatic number is the smallest number of colours needed to colour a graph such that for all vertices $v_{1}, v_{2} \in V$, there does not exist an edge $e\in E$ such that $e=\{ v_{1}, v_{2} \}$.
