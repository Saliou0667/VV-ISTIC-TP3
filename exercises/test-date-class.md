# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.

### Answer
Pour la classe `Date`, les caractéristiques et blocs suivants ont été identifiés :

- **Constructeur et méthode `isValidDate`** :
  - Dates valides (par exemple, 1/1/2020, 29/2/2020)
  - Dates invalides (par exemple, 31/11/2020, 29/2/2021)

- **Méthode `isLeapYear`** :
  - Années bissextiles (par exemple, 2020)
  - Années non bissextiles (par exemple, 2019)

- **Méthode `nextDate`** :
  - Fin de mois (par exemple, 31/12/2020)
  - Jour régulier (par exemple, 1/1/2020)

- **Méthode `previousDate`** :
  - Début de mois (par exemple, 1/1/2021)
  - Jour régulier (par exemple, 2/1/2020)

- **Méthode `compareTo`** :
  - Dates dans la même année (par exemple, 1/1/2020 vs 2/1/2020)
  - Dates dans des années différentes (par exemple, 1/1/2020 vs 1/1/2021)
  - Comparaison avec null

2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.

### Answer
Les cas de test initiaux ont fourni une bonne couverture des méthodes de la classe `Date`. Des cas de test supplémentaires ont été ajoutés pour couvrir les cas limites et les entrées invalides. Par exemple, tester les dates invalides et les comparaisons nulles dans la méthode `compareTo`.

Résultats des tests :
```shell
diallo@diallo-Latitude-5520:~/VsCodeProjet/VV-ISTIC-TP3/code/tp3-date$ mvn test
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.

### Answer
Les prédicats dans les méthodes de la classe `Date` n'utilisent pas plus de deux opérateurs booléens. Par conséquent, les cas de test existants satisfont déjà la *Base Choice Coverage*.

4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

### Answer
PIT a été utilisé pour évaluer la suite de tests. Le score de mutation était élevé, ce qui indique que les cas de test étaient efficaces pour détecter la plupart des mutations. Quelques mutants vivants ont été identifiés, et des cas de test supplémentaires ont été ajoutés pour couvrir ces scénarios, tels que les cas limites pour les bornes de date et les entrées invalides.

Résultats de PIT :
```shell
diallo@diallo-Latitude-5520:~/VsCodeProjet/VV-ISTIC-TP3/code/tp3-date$ mvn org.pitest:pitest-maven:mutationCoverage
[INFO] >> Generated 11 mutations Killed 11 (100%)
[INFO] BUILD SUCCESS
```

