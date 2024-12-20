# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

## Answer

### 1. Input Space Partitioning

Pour chaque méthode, j'ai identifié les caractéristiques suivantes :

- `pop` : 
  - Heap vide
  - Heap avec un seul élément
  - Heap avec plusieurs éléments

- `peek` :
  - Heap vide
  - Heap avec un seul élément
  - Heap avec plusieurs éléments

- `push` :
  - Insertion dans un heap vide
  - Insertion dans un heap non vide

- `count` :
  - Heap vide
  - Heap non vide

### 2. Statement Coverage

J'ai exécuté les tests et obtenu une couverture de 100% des instructions. Aucun test supplémentaire n'était nécessaire.

### 3. Base Choice Coverage

J'ai vérifié que tous les prédicats avec plus de deux opérateurs booléens sont couverts par les tests existants. Aucun test supplémentaire n'était nécessaire.

### 4. Mutation Testing

J'ai utilisé PIT pour évaluer ma suite de tests. Voici les résultats :

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running fr.istic.vv.BinaryHeapTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.08 s - in fr.istic.vv.BinaryHeapTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.491 s
[INFO] Finished at: 2024-12-20T20:01:48+01:00
[INFO] ------------------------------------------------------------------------

[INFO] -------------------------------------------------------
[INFO]  M U T A T I O N   T E S T I N G
[INFO] -------------------------------------------------------
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  M U T A T I O N   R E S U L T S
[INFO] -------------------------------------------------------
[INFO] 
[INFO] - Mutators
[INFO] -------------------------------------------------------
[INFO] > org.pitest.mutationtest.engine.gregor.mutators.ConditionalsBoundaryMutator
[INFO] >> Generated 7 Killed 2 (29%)
[INFO] > KILLED 2 SURVIVED 5 TIMED_OUT 0 NON_VIABLE 0 
[INFO] > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
[INFO] > NO_COVERAGE 0 
[INFO] -------------------------------------------------------
[INFO] > org.pitest.mutationtest.engine.gregor.mutators.VoidMethodCallMutator
[INFO] >> Generated 4 Killed 4 (100%)
[INFO] > KILLED 4 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
[INFO] > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
[INFO] > NO_COVERAGE 0 
[INFO] -------------------------------------------------------
[INFO] > org.pitest.mutationtest.engine.gregor.mutators.NullReturnValsMutator
[INFO] >> Generated 2 Killed 2 (100%)
[INFO] > KILLED 2 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
[INFO] > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
[INFO] > NO_COVERAGE 0 
[INFO] -------------------------------------------------------
[INFO] > org.pitest.mutationtest.engine.gregor.mutators.MathMutator
[INFO] >> Generated 10 Killed 9 (90%)
[INFO] > KILLED 9 SURVIVED 1 TIMED_OUT 0 NON_VIABLE 0 
[INFO] > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
[INFO] > NO_COVERAGE 0 
[INFO] -------------------------------------------------------
[INFO] > org.pitest.mutationtest.engine.gregor.mutators.PrimitiveReturnsMutator
[INFO] >> Generated 1 Killed 1 (100%)
[INFO] > KILLED 1 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
[INFO] > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
[INFO] > NO_COVERAGE 0 
[INFO] -------------------------------------------------------
[INFO] > org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator
[INFO] >> Generated 12 Killed 12 (100%)
[INFO] > KILLED 12 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
[INFO] > MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
[INFO] > NO_COVERAGE 0 
[INFO] -------------------------------------------------------
[INFO] -------------------------------------------------------
[INFO] - Statistics
[INFO] -------------------------------------------------------
[INFO] >> Generated 36 mutations Killed 30 (83%)
[INFO] >> Ran 55 tests (1.53 tests per mutation)
[INFO] -------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] -------------------------------------------------------
```

J'ai obtenu un score de mutation de 83%, avec 30 mutations tuées sur 36 générées. Les mutations restantes sont principalement dues à des conditions limites et des valeurs de retour primitives.
