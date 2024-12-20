# Détection des mauvaises odeurs de test avec PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Réponse

J'ai utilisé la règle PMD `JUnitTestsShouldIncludeAssert` pour détecter une mauvaise odeur de test dans le projet [Apache Commons Lang](https://github.com/apache/commons-lang). Cette règle identifie les méthodes de test qui ne contiennent aucune assertion, ce qui est une mauvaise odeur de test courante indiquant que le test pourrait ne pas vérifier de comportement.

### Mauvaise Odeur Détectée

Dans la classe `StringUtilsTest`, la méthode de test suivante a été détectée sans aucune assertion :

```java
public void testIsEmpty() {
    StringUtils.isEmpty(null);
    StringUtils.isEmpty("");
    StringUtils.isEmpty(" ");
    StringUtils.isEmpty("text");
}
```

### Amélioration Proposée

Pour améliorer ce test, des assertions doivent être ajoutées pour vérifier le comportement attendu de la méthode `isEmpty` :

```java
public void testIsEmpty() {
    assertTrue(StringUtils.isEmpty(null));
    assertTrue(StringUtils.isEmpty(""));
    assertFalse(StringUtils.isEmpty(" "));
    assertFalse(StringUtils.isEmpty("text"));
}
```

