# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.

## Answer

### Utilisation de Mockito

J'ai réécrit les tests de `TLSSocketFactory` en utilisant Mockito pour simplifier la création de mocks et stubs pour les objets `SSLSocket`. Voici ce que j'ai fait :

1. **Création des mocks** : J'ai utilisé `Mockito.mock(SSLSocket.class)` pour créer des mocks de `SSLSocket`.
2. **Définition des comportements** : J'ai utilisé `when(...).thenReturn(...)` pour définir les comportements des méthodes `getSupportedProtocols` et `getEnabledProtocols`.
3. **Vérification des interactions** : J'ai utilisé `verify(...)` pour vérifier que `setEnabledProtocols` est appelé avec les bons arguments.

### Résultats des tests

Les nouveaux tests basés sur Mockito ont permis de mieux couvrir la méthode `prepareSocket` et de détecter les erreurs potentielles. Voici un exemple de test avec Mockito :

```java
@Test
public void testIfNoChangeInPrepareSocket() {
    TLSSocketFactory f = new TLSSocketFactory();
    SSLSocket socket = mock(SSLSocket.class);

    when(socket.getSupportedProtocols()).thenReturn(new String[]{"TLSv1", "TLSv1.1"});
    when(socket.getEnabledProtocols()).thenReturn(new String[]{"TLSv1"});

    f.prepareSocket(socket);

    verify(socket).setEnabledProtocols(new String[]{"TLSv1.1", "TLSv1"});
}
```

Ce test vérifie que `setEnabledProtocols` est appelé avec les bons protocoles, ce qui garantit que la méthode `prepareSocket` fonctionne correctement.

### Résultats des tests et de la couverture de mutation

Voici les résultats des tests et de l'analyse de couverture de mutation :

```
diallo@diallo-Latitude-5520:~/VsCodeProjet/VV-ISTIC-TP3/code/tp3-ssl$ mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------< fr.istic.vv:tp3-ssl >-------------------------
[INFO] Building tp3-ssl 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ tp3-ssl ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/diallo/VsCodeProjet/VV-ISTIC-TP3/code/tp3-ssl/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ tp3-ssl ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ tp3-ssl ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/diallo/VsCodeProjet/VV-ISTIC-TP3/code/tp3-ssl/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ tp3-ssl ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:3.0.0-M5:test (default-test) @ tp3-ssl ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running fr.istic.vv.TLSSocketFactoryTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.163 s - in fr.istic.vv.TLSSocketFactoryTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.142 s
[INFO] Finished at: 2024-12-20T20:22:04+01:00
[INFO] ------------------------------------------------------------------------
diallo@diallo-Latitude-5520:~/VsCodeProjet/VV-ISTIC-TP3/code/tp3-ssl$ mvn org.pitest:pitest-maven:mutationCoverage
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------< fr.istic.vv:tp3-ssl >-------------------------
[INFO] Building tp3-ssl 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- pitest-maven:1.5.2:mutationCoverage (default-cli) @ tp3-ssl ---
[INFO] Found plugin : Default csv report plugin
[INFO] Found plugin : Default xml report plugin
[INFO] Found plugin : Default html report plugin
[INFO] Found plugin : Static initializer code detector plugin
[INFO] Found plugin : Static initializer filter plugin
[INFO] Found plugin : Excluded annotations plugin
[INFO] Found plugin : Try with resources filter
[INFO] Found plugin : Inlined finally block filter plugin
[INFO] Found plugin : Implicit null check filter
[INFO] Found plugin : Method reference null check filter
[INFO] Found plugin : For each loop filter
[INFO] Found plugin : Enum constructor filter
[INFO] Found plugin : Logging calls filter
[INFO] Found plugin : Infinite for loop filter
[INFO] Found plugin : Long running iterator loop filter
[INFO] Found plugin : For loop counter filter
[INFO] Found plugin : Kotlin junk mutations filter
[INFO] Found plugin : Max mutations per class limit
[INFO] Found plugin : Equals shortcut equivalent mutant filter
[INFO] Found plugin : Trivial return vals equivalence filter
[INFO] Found plugin : Mutant export plugin
[INFO] Found shared classpath plugin : Default mutation engine
[INFO] Found shared classpath plugin : JUnit 5 test framework support
[INFO] Found shared classpath plugin : JUnit plugin
[INFO] Found shared classpath plugin : TestNG plugin
[INFO] Adding org.pitest:pitest-junit5-plugin to SUT classpath
[INFO] Adding org.pitest:pitest to SUT classpath
[INFO] Mutating from /home/diallo/VsCodeProjet/VV-ISTIC-TP3/code/tp3-ssl/target/classes
[INFO] Defaulting target classes to match packages in build directory
[INFO] Defaulting target tests to match packages in test build directory
8:22:11 PM PIT >> INFO : Verbose logging is disabled. If you encounter a problem, please enable it before reporting an issue.
8:22:11 PM PIT >> INFO : Sending 6 test classes to minion
8:22:11 PM PIT >> INFO : Sent tests to minion
8:22:11 PM PIT >> INFO : MINION : 8:22:11 PM PIT >> INFO : Checking environment

8:22:12 PM PIT >> INFO : MINION : 8:22:12 PM PIT >> INFO : Found  5 tests

8:22:12 PM PIT >> INFO : MINION : 8:22:12 PM PIT >> INFO : Dependency analysis reduced number of potential tests by 0
8:22:12 PM PIT >> INFO : 5 tests received

/8:22:13 PM PIT >> INFO : Calculated coverage in 1 seconds.
8:22:13 PM PIT >> INFO : Incremental analysis reduced number of mutations by 0
8:22:13 PM PIT >> INFO : Created  2 mutation test units
-8:22:17 PM PIT >> INFO : Completed in 6 seconds
================================================================================
- Mutators
================================================================================
> org.pitest.mutationtest.engine.gregor.mutators.BooleanTrueReturnValsMutator
>> Generated 1 Killed 1 (100%)
> KILLED 1 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.EmptyObjectReturnValsMutator
>> Generated 1 Killed 1 (100%)
> KILLED 1 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.ConditionalsBoundaryMutator
>> Generated 2 Killed 2 (100%)
> KILLED 2 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.VoidMethodCallMutator
>> Generated 1 Killed 1 (100%)
> KILLED 1 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.BooleanFalseReturnValsMutator
>> Generated 1 Killed 1 (100%)
> KILLED 1 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator
>> Generated 7 Killed 7 (100%)
> KILLED 7 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 0 
--------------------------------------------------------------------------------
================================================================================
- Timings
================================================================================
> scan classpath : < 1 second
> coverage and dependency analysis : 1 seconds
> build mutation tests : < 1 second
> run mutation analysis : 3 seconds
--------------------------------------------------------------------------------
> Total  : 5 seconds
--------------------------------------------------------------------------------
================================================================================
- Statistics
================================================================================
>> Generated 13 mutations Killed 13 (100%)
>> Ran 17 tests (1.31 tests per mutation)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  7.902 s
[INFO] Finished at: 2024-12-20T20:22:17+01:00
[INFO] ------------------------------------------------------------------------
```

J'ai obtenu un score de mutation de 100%, avec 13 mutations tuées sur 13 générées. Cela montre que les tests couvrent bien le code et détectent les erreurs potentielles.
