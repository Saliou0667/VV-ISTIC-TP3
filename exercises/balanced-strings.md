# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

### Étape 1 : Partitionnement de l'espace des entrées

**Caractéristiques et blocs de partitionnement :**

1. **Chaîne vide :**
   - Entrée : `""`
   - Résultat attendu : `true`

2. **Un seul type de symboles de regroupement :**
   - Équilibré : `"{}"`, `"[]"`, `"()"`
   - Déséquilibré : `"{"`, `"}"`, `"["`, `"]"`, `"("`, `")"`

3. **Plusieurs types de symboles de regroupement :**
   - Équilibré : `"{[][]}({})"`
   - Déséquilibré : `"{(}{})"`, `"([)]"`

4. **Symboles de regroupement imbriqués :**
   - Équilibré : `"{[()()]}"`, `"({[]})"`
   - Déséquilibré : `"{[(])}"`, `"({[})]"`

5. **Chaîne nulle :**
   - Entrée : `null`
   - Résultat attendu : `false`

### Étape 2 : Couverture des instructions

**Description :**

Pour évaluer la couverture des instructions, j'ai exécuté les cas de test initiaux que j'avais conçus à l'étape précédente. J'ai utilisé JaCoCo, un outil de couverture de code, pour mesurer quelles lignes de code étaient exécutées par les tests. J'ai constaté que la majorité des lignes de code étaient couvertes, mais il restait quelques lignes non couvertes. Par exemple, la ligne `private StringUtils() {}` n'était pas couverte car elle n'est jamais appelée directement dans les tests.

Pour augmenter la couverture, j'ai ajouté des cas de test supplémentaires pour les scénarios non couverts. Par exemple, j'ai ajouté un test pour vérifier une chaîne avec des symboles imbriqués comme `"{[()()]}"`. Cela a permis d'atteindre une couverture de ligne de 92%.

### Étape 3 : Couverture de choix de base

**Évaluation et cas de test supplémentaires :**

Les prédicats dans la méthode `isBalanced` n'utilisent pas plus de deux opérateurs booléens, donc les cas de test existants satisfont déjà la couverture de choix de base. Par exemple, le prédicat `if (ch == '{' || ch == '[' || ch == '(')` utilise deux opérateurs booléens. Les tests existants couvrent tous les cas possibles pour ce prédicat, donc aucune modification supplémentaire n'était nécessaire à cette étape.

### Étape 4 : Tests de mutation avec PIT

**Score de mutation et mutants vivants :**

J'ai exécuté PIT pour évaluer la suite de tests. Le score de mutation était de 100%, ce qui signifie que tous les mutants ont été tués par les tests. Par exemple, un mutant qui remplaçait `return false;` par `return true;` dans la méthode `isBalanced` a été tué par les tests existants.

Pour analyser les mutants vivants, j'ai examiné le rapport PIT et identifié les lignes de code où les mutants ont survécu. Cependant, dans ce cas, il n'y avait pas de mutants vivants, ce qui indique que les tests sont bons et couvrent bien le code.

### Résultats

- **Couverture des lignes :** 92%
- **Couverture des mutations :** 100%

Les résultats indiquent que les tests sont efficaces pour couvrir le code et détecter les problèmes potentiels. La couverture des mutations de 100% montre que tous les mutants ont été tués, ce qui indique des cas de test robustes. Mais, il reste encore de la place pour améliorer la couverture des lignes afin de s'assurer que toutes les lignes de code sont testées.

### Résultats des tests PIT

- **Mutateurs utilisés :**
  - `BooleanTrueReturnValsMutator`
  - `ConditionalsBoundaryMutator`
  - `IncrementsMutator`
  - `BooleanFalseReturnValsMutator`
  - `NegateConditionalsMutator`

- **Statistiques :**
  - Mutations générées : 24
  - Mutations tuées : 24 (100%)
  - Tests exécutés : 31 (1.29 tests par mutation)


