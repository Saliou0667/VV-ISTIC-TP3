# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

En Java, les nombres en virgule flottante (`double`, `float`) sont représentés en interne par un format binaire (IEEE 754). Cette représentation ne permet pas toujours de représenter exactement certaines fractions décimales, provoquant ainsi de légères erreurs d’arrondi. Dans ce cas précis, `3 * 0.4` peut ne pas être exactement égal à `1.2` en mémoire, mais quelque chose comme `1.19999999999999995559`. De ce fait, la comparaison `==` échoue.

**Comment faire ce type de vérification ?**

Au lieu de comparer l'égalité directe, on utilise une « marge d’erreur » (epsilon) pour vérifier que les deux nombres sont suffisamment proches :

```java
@Test
void testEgaliteNombresFlottants() {
    double expected = 1.2;
    double actual = 3 * 0.4;
    double epsilon = 1e-9; // petite marge d’erreur
    assertTrue(Math.abs(expected - actual) < epsilon);
}
```
Ainsi, on ne teste pas l'égalité stricte, mais on s'assure que la différence entre les deux nombres est négligeable.

---

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

**Différence :**
- `assertEquals(a, b)` : Vérifie l’égalité « logique » ou « par valeur » entre `a` et `b`. Cela signifie que deux objets différents mais ayant un état identique seront considérés égaux s’ils redéfinissent correctement `equals()`. Pour les types primitifs ou pour les objets immuables, `assertEquals` compare la valeur.
- `assertSame(a, b)` : Vérifie que `a` et `b` sont la même référence, c’est-à-dire exactement le même objet en mémoire.

**Scénario où ils produisent le même résultat :**

```java
@Test
void testEqualsEtSame() {
    String a = "test";
    String b = "test"; // Pointe vers la même chaîne littérale dans le pool de chaînes
    assertEquals(a, b); // Passe : les valeurs sont égales
    assertSame(a, b);   // Passe : les références sont identiques
}
```

**Scénario où ils ne produisent pas le même résultat :**

```java
@Test
void testEqualsMaisPasSame() {
    String a = new String("test");
    String b = new String("test");
    assertEquals(a, b); // Passe : les valeurs sont égales
    assertSame(a, b);   // Échoue : les références pointent vers des objets différents
}
```

---

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

**Utilisations possibles :**
- Pour signaler explicitement qu’une condition du test n’a pas été remplie alors qu’elle aurait dû l’être, indépendamment d’une exception.
- Pour marquer une partie du code comme impossible ou non atteignable. Si le code y parvient, c’est qu’un comportement inattendu s’est produit.

**Exemple : Validation d'entrée avec des vérifications exhaustives.**

```java
@Test
void testValidationEntree() {
    String input = "invalide";
    switch (input) {
        case "valide":
            // Traiter les entrées valides
            break;
        case "neutre":
            // Traiter les entrées neutres
            break;
        default:
            fail("Entrée inattendue : " + input);
    }
}
```

Ici, `fail()` indique que le test n’a pas atteint l’état prévu si `input` n’est pas géré.

**Exemple : Placeholder temporaire pour des fonctionnalités non implémentées.**

```java
@Test
void testFonctionnaliteNonImplantee() {
    fail("Cette fonctionnalité n'est pas encore implémentée.");
}
```

Ceci est utile pour suivre l'avancement dans le développement piloté par les tests (TDD).

---

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

**Avantages :**
1. **Clarté et localisation améliorées :** Le test de l’exception se fait directement à l’endroit où l’exception est attendue, au sein d’une expression lambda, rendant le test plus lisible et explicite.
2. **Analyse de l’exception :** Après avoir capturé l’exception via `assertThrows`, on peut vérifier ses propriétés (message, type précis).
3. **Réduction des ambiguïtés :** Dans JUnit 4, spécifier `expected = SomeException.class` dans l’annotation `@Test` couvrait tout le code du test, ce qui pouvait être moins précis. Avec `assertThrows`, seule la portion de code passée en paramètre est censée lever l’exception, limitant les erreurs de test.

**Exemple :**

JUnit 4 :

```java
@Test(expected = IllegalArgumentException.class)
public void testExceptionAttendueJUnit4() {
    throw new IllegalArgumentException("Argument invalide");
}
```

JUnit 5 :

```java
@Test
void testExceptionAttendueJUnit5() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        throw new IllegalArgumentException("Argument invalide");
    });
    assertEquals("Argument invalide", exception.getMessage());
}
```

Avec JUnit 5, `assertThrows` permet de valider non seulement le type de l'exception, mais également son message ou d'autres attributs, offrant ainsi plus de flexibilité et de précision dans vos tests.

