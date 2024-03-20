# Frontend

## Prérequis

Avant de pouvoir développer sur le frontend il faudra que vous installez [nodejs](https://nodejs.org/en). La version LTS est suffisante.

Pour éditer le code, je vous recommande fortement VSCode (ou VSCodium) avec les extensions svelte (svelte.svelte-vscode) et eslint (dbaeumer.vscode-eslint).

Pour que eslint fonctionne dans les fichiers `.svelte`, vous devez ajouter les lignes suivantes dans votre settings.json de VSCode:

```json
"eslint.validate": [
    "javascript",
    "typescript",
    "svelte"
]
```

## Installation

Ouvrez un terminal dans le répertoire `frontend/` du projet et tapez les commandes suivantes pour installer les dépendances puis faire rouler le projet en mode développement.

```sh
npm install
npm run dev 
```

## Références

- https://svelte.dev/docs/introduction
- https://kit.svelte.dev/docs/introduction

## Tutoriels

Un [tutoriel](https://learn.svelte.dev/tutorial/welcome-to-svelte) vous est offert pour Svelte et SvelteKit. Je vous recommande au moins de survoler la partie 1 (Basic Svelte) et la partie 3 (Basic SvelteKit) pour vous initier aux concepts de base du framework.

## Eslint

Pour valider le style du projet avec eslint dans le terminal (ceci sera utile pour la personne qui s'occupe du pipeline) vous pouvez utiliser la commande:

```sh
npm run lint # ne devrait pas donner d'erreur ou de warnings
```
