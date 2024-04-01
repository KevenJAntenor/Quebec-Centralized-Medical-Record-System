# Partie 2: Introduction des Patrons de Conception

## Équipe 9

- Dominique Elias ELID14019800
- Emil Surkhaev SURE89080008
- Youenn Pierre-Justin PIEY78070308
- Keven Jude Anténor ANTK08129003

### Informations du cours

- **Cours :** INF5153 groupe 30
- **Titre :** Génie logiciel: conception
- **Professeur :** Gnagnely Serge Dogny
- **Date :** 8 Avril 2024

## Table of Contents

1. [Facade-Observer](#facade-observer)
2. [Nom du Patron de Conception 2](#nom-du-patron-de-conception-2)
3. [Nom du Patron de Conception 3](#nom-du-patron-de-conception-3)
4. [Nom du Patron de Conception 4](#nom-du-patron-de-conception-4)
5. [Différences entre l'implémentation et la conception](#différences-entre-limplémentation-et-la-conception)
6. [Problèmes de Conception et Corrections](#problèmes-de-conception-et-corrections)

## Facade-Observer

Par Dominique Elias
<!-- 
Le patron de conception `Observer` est utilisé pour permettre à un objet de la class Modification (Observer) d'être notifié et mis à jour automatiquement lorsqu'un autre objet medicalFile (Subject) change d'état.
Le patron de conception `Facade` est utilisé ici pas seulement pour simplifier l'interface de sous-système MedicalFile, mais aussi pour ne pas rendre le sous-système MedicalFile dépendant de l'interface de l'Observer. -->

### Diagramme de Classe

![Diagramme de classe](./plantUml-partie2/observer-class.png)

### Diagramme de Séquence

![Diagramme de séquence](./plantUml-partie2/observer-seq.png)

### Implémentation

Le patron `Observer` est utilisé pour permettre à un objet de la classe `ModificationObserver` d'être notifié et mis à jour automatiquement lorsqu'un objet `medicalFileFacade` (Subject) dont il dépent change d'état.

Rappel (partie1) : La classe `Modification` sert à sauvgarder les modifications apportées à un `medicalFile` dans la base de données `Archive`.

Donc lorsqu'un objet `MedicalFile` change d'état, la classe `ModificationObserver` est notifiée et mise à jour automatiquement à travers un objet `medicalFileFacade`, et les modifications sont sauvegardées dans la base de données.

Ce Patron offre aussi la flexibilité de pouvoir ajouter d'autres classes Observers qui dépendent de `medicalFileFacade` sans avoir à modifier `MedicalFileFacade`.

Par exemple, si on veut ajouter une classe qui envoie un courriel à un médecin lorsqu'un `medicalFile` est modifié, on peut le faire en ajoutant une classe `EmailObserver` qui réalise Observer, sans avoir à modifier `medicalFileFacade`.

On sert du patron `Facade` ici pour garder la classe `MedicalFile` indépendante de l'interface de l'Observer. Donc `MedicalFileFacade` sert d'interface unifiée pour les classes `Observers`.

### Problèmes de Conception et Corrections

- **Problème de Conception :** D'abord, on a pas utilisé le patron `Facade` pour simplifier l'interface de sous-système `MedicalFile` et découpler de l'interface de l'Observer.
- **Correction :** Donc on a corrigé ce problème en ajoutant la classe `MedicalFileFacade` qui sert d'interface unifiée pour les classes `Observers`.

### Définitions des patrons de conception

- Intention de l'`Observer` : Définir une interdépendance de type un-à-plusieurs, de façon que quand un objet change d'état, tous ceux qui en dépendent en soient notifiés et automatiquement mis à  jour.
- Intention de la `Facade` : Fournit une interface unifiée, à l'ensemble des interfaces d'un sous-système. La façade fournit une interface de plus haut niveau, qui rend le sous-système plus facile à utiliser.

## Nom du Patron de Conception 2

Par Emil Surkhaev

Plant uml code inside plantUml-partie2 directory
### Diagramme de Classe

Plant uml code inside plantUml-partie2 directory
[Insérer le diagramme de classe ici]

### Diagramme de Séquence

Plant uml code inside plantUml-partie2 directory
[Insérer le diagramme de séquence ici]

### Implémentation

[Description de l'implémentation du patron de conception]


## Nom du Patron de Conception 3
Par Youenn Pierre-Justin

### Diagramme de Classe

Plant uml code inside plantUml-partie2 directory
[Insérer le diagramme de classe ici]

### Diagramme de Séquence

Plant uml code inside plantUml-partie2 directory
[Insérer le diagramme de séquence ici]

### Implémentation

[Description de l'implémentation du patron de conception]


## Nom du Patron de Conception 4
Par Keven Jude Anténor

### Diagramme de Classe

Plant uml code inside plantUml-partie2 directory
[Insérer le diagramme de classe ici]

### Diagramme de Séquence

Plant uml code inside plantUml-partie2 directory
[Insérer le diagramme de séquence ici]

### Implémentation

[Description de l'implémentation du patron de conception]

Pour chaque problème de conception que vous allez corriger, vous devrez décrire comment vous avez détecté le problème de conception et comment vous l'avez corrigé.

### Différences entre l'implémentation et la conception

Toute différence entre l'implémentation et la conception doit être documentée dans le rapport que vous allez me remettre.

### Problèmes de Conception et Corrections

Pour chaque problème de conception que vous allez corriger, vous devrez décrire comment vous avez détecté le problème de conception et comment vous l'avez corrigé.
