# SantéConnect

## Présentation

SantéConnect est un système de gestion de dossiers médicaux comprenant un portail web et mobile pour les patients, une application installée pour les médecins et les professionnels de la santé et un portail API pour la RAMQ.
L'application est conçue pour fournir un dossier médical centralisé aux patients du Québec.
Les dossiers médicaux sont créés par la RAMQ, et sont accessibles par le patient et le médecin.
La RAMQ envoie une demande au système pour créer un dossier médical pour un patient qui a été enregistré dans le système de la RAMQ.
Les médecins peuvent ajouter des visites médicales, des traitements médicaux et modifier toute information nécessaire dans le dossier médical.
Les patients peuvent consulter leur dossier médical et mettre à jour leurs coordonnées si nécessaire.
La RAMQ peut également consulter les modifications apportées par le médecin et le patient, et reconstituer le dossier médical si nécessaire.

## Table of Contents

1. [Conception Architecturale](#conception-architecturale)
2. [Diagrammes de Cas d'Utilisation](#diagrammes-de-cas-dutilisation)
3. [Diagrammes de Classes](#diagrammes-de-classes)
4. [Diagrammes de Sequence](#diagrammes-de-sequence)
5. [Diagrammes de Paquetage](#diagrammes-de-paquetage)
6. [Diagramme de Composants](#diagramme-de-composants)
7. [Diagramme de Deploiement](#diagramme-de-deploiement)
8. [Justification des Modeles GRASP](#justification-des-modeles-grasp)

## Conception Architecturale

![Conception Architecturale](./misc/images/system-architecture.png)

L'architecture hexagonale divise l'ensemble du programme en quatre espaces :

1. Les systèmes externes, y compris les navigateurs web, les bases de données et les autres services informatiques.
2. Les adaptateurs mettent en œuvre les API spécifiques requises par les systèmes externes, par exemple, un adaptateur Svelte pour le navigateur web et un adaptateur SQLite3 pour la base de données.
3. Les ports sont l'abstraction de ce dont notre application a besoin de la part du système externe.
4. Le modèle de domaine contient la logique de notre application, sans les détails du système externe, la logique est une traduction des cas d'utilisation en code.

L'architecture hexagonale est basée sur le principe de l'inversion de la dépendance, qui stipule que les modules de haut niveau ne doivent pas dépendre des modules de bas niveau, mais doivent dépendre des abstractions. Ce principe garantit que le modèle du domaine est indépendant des systèmes externes et que les systèmes externes sont indépendants du modèle du domaine.
Cette architecture permet également de tester le modèle du domaine indépendamment des systèmes externes et de tester les systèmes externes indépendamment du modèle du domaine.

## Diagrammes de Cas d'Utilisation

![Diagrammes de Cas d'Utilisation](./plantUml/use-case.png)

## Diagrammes de Classes

![Diagrammes de Classes](./plantUml/class-logic.png)

Ce diagramme de classes représente uniquement le modèle de domaine de l'application. Il n'inclut pas les classes utilisées pour interagir avec les systèmes externes, tels que la base de données, le navigateur web et l'API de la RAMQ.

## Diagrammes de Sequence

createMedicalFile
![Diagrammes de Séquence](./plantUml/createMedicalFile.png)

accessMedicalFile
![Diagrammes de Séquence](./plantUml/accessMedicalFile.png)

modifyCoordinates
![Diagrammes de Séquence](./plantUml/modifyCoordinates.png)

modifyMedicalFile and archiveModification
![Diagrammes de Séquence](./plantUml/modifyMedicalFile.png)

HTTP Codes are not included in the reconstruction sequence diagrams

reconstructMedicalFileFromModification
![Diagrammes de Séquence](./plantUml/reconstructMedicalFileFromModification.png)

reconstructMedicalFileFromDate
![Diagrammes de Séquence](./plantUml/reconstructMedicalFileFromDate.png)

getModifications
![Diagrammes de Séquence](./plantUml/getModifications.png)

## Diagrammes de Paquetage

![Diagrammes de Paquetage](./plantUml/package.png)

## Diagramme de Composants

![Diagramme de Composants](./plantUml/component.png)

## Diagramme de Deploiement

A ameliorer

![Diagramme de Déploiement](./plantUml/deployment.png)

## Justification des Modeles GRASP

Patron GRASP | Justification
--- | ---
Faible couplage | Grâce à l'architecture hexagonale, l'implémentaiton de l'application est indépendante des détails de l'interface utilisateur, de la base de données et des autres services externes. Cela permet de changer les détails de l'implémentation sans affecter le reste du système. Cela permet également de tester les composants de l'application indépendamment des services externes.
Contrôleur | Le contrôleur est responsable de la gestion des requêtes HTTP et de la logique métier. Ici, c'est les ports reliés aux intercafes utilisateurs qui sont responsables de la gestion des requêtes HTTP. Les ports sont des interfaces qui définissent les opérations que le contrôleur doit implémenter. Cela permet de séparer la logique métier de la logique de présentation.
Forte Cohésion | Les classes sont conçues pour avoir une seule responsabilité. Par exemple, la classe `Patient` est responsable de la gestion des informations du patient, et la classe `MedicalFile` est responsable de la gestion des informations du dossier médical. Cela permet de faciliter la maintenance et l'évolution du système.
Indirection | Les classes de l'application ne dépendent pas des détails de l'implémentation des services externes. Par exemple, la classe `Patient` ne dépend pas de la manière dont les informations du patient sont stockées dans la base de données. Cela permet de changer les détails de l'implémentation sans affecter le reste du système.
Protégé de variations | Les composants de l'application sont protégés des variations dans les services externes. Par exemple, si le système de gestion de base de données est changé, les classes de l'application n'ont pas besoin d'être modifiées. Cela permet de faciliter la maintenance et l'évolution du système.

## Note

Le code PlantUml est situé dans le répertoire `plantUml`. Le fait de placer le code dans un répertoire séparé permet une meilleure organisation et un accès plus facile aux diagrammes.
L'image du diagramme architecure est située dans le répertoire `misc/images`. plantUml n'a pas été utilisé pour ce diagramme, car il ne permet pas la création de tels diagrammes.
