# Dinnerbone Entities

## Présentation
Dinnerbone Entities est un plugin Spigot destiné à renommer automatiquement les entités de votre serveur en "Dinnerbone", afin de les retourner visuellement. Il cible l'API Spigot 1.16.5 et se compile avec Maven.

## Fonctionnement
- À l'activation du plugin, si l'option `automatic` est vraie, toutes les entités non joueurs reçoivent le nom **Dinnerbone**, sauf si elles sont déjà renommées et que `RenameAlreadyRenamedEntities` est désactivé.
- Les nouvelles entités sont également renommées à leur apparition si leur type est actif dans `entities.yml`.
- Un bâton spécial peut être obtenu via la commande et permet de contrôler le mode automatique ou de faire sauter les entités selon les paramètres.

## Fichiers de configuration
### config.yml
- `automatic` *(booléen)* : active le renommage automatique au démarrage du serveur.
- `RenameAlreadyRenamedEntities` *(booléen)* : renomme aussi les entités possédant déjà un nom personnalisé.
- `global_command_action` *(booléen)* : applique immédiatement le changement d'état automatique à toutes les entités existantes lors de l'exécution de la commande.
- `ToggleStick_Name` *(chaîne)* : nom du bâton de contrôle remis au joueur.
- `LeftClickMakeEntitiesJump` *(booléen)* : si vrai, un clic gauche avec le bâton fait sauter les entités.
- `JumpHeight` *(nombre)* : hauteur du saut provoqué.

### entities.yml
Contient une liste de toutes les entités et un booléen associé. Si `true`, l'entité sera renommée lorsqu'elle apparaît. Exemple :
```yml
ELDER_GUARDIAN: true
WITHER_SKELETON: true
...```

### globalentities.yml
Fonctionne sur le même principe qu'`entities.yml` mais sert aux actions globales lors du changement d'état automatique ou avec le bâton.

### messages.yml
Personnalise tous les messages envoyés par le plugin :
- `WrongCommand` – message d’erreur en cas de mauvaise commande.
- `PluginReloaded` – confirmation du rechargement des fichiers.
- `AutomaticToggle` – texte affiché après un changement de statut automatique.
- `HelpCommand` – aide détaillant les commandes disponibles.
- `PluginOn` et `PluginOff` – messages de démarrage et d’arrêt.
- `Boolean_true` et `Boolean_false` – valeurs textuelles utilisées dans `AutomaticToggle`.

## Commandes
Commande principale : `/dinnerboneentities` (alias `de`, `dinnerbonee`, `dentities`) [permission `dinnerboneentities.admin`].

Sous-commandes :
- `/dinnerboneentities reload` : recharge toutes les configurations.
- `/dinnerboneentities help` : affiche l’aide.
- `/dinnerboneentities auto` (alias `automatic`, `automatique`) : active/désactive le mode automatique.
- `/dinnerboneentities stick` (alias `wand`) : donne au joueur le bâton défini dans `ToggleStick_Name`.

Le bâton obtenu :
- **Clic droit** : exécute la sous-commande `auto`.
- **Clic gauche** (si `LeftClickMakeEntitiesJump` est vrai) : fait sauter les entités concernées de `globalentities.yml`.

## Compilation / Installation
1. Assurez-vous d’avoir Java 8 et Maven installés.
2. Clonez le dépôt puis compilez le projet :
```bash
mvn package
```
3. Le fichier JAR généré se trouve dans `target/DinnerboneEntities-1.1.jar`.
4. Placez-le dans le dossier `plugins/` de votre serveur Spigot 1.16.5 puis redémarrez.

## Fichiers importants
- `DinnerboneEntities.java` : classe principale du plugin.
- `CommandDinnerboneEntities.java` : gestion des commandes administrateur.
- `Events.java` : événements de spawn d’entité et interactions avec le bâton.
- `plugin.yml` : déclaration du plugin et de sa commande.
- Dossier `src/main/resources/` : contient toutes les configurations par défaut.

## Utilisation rapide
1. Lancez votre serveur avec le plugin dans `plugins/`.
2. Utilisez `/dinnerboneentities stick` pour recevoir le bâton spécial.
3. Cliquez droit avec ce bâton pour basculer le mode automatique ou gauche pour faire sauter les entités (selon configuration).

## Licence et contributions
Aucune licence ou consigne de contribution n’a été trouvée dans ce dépôt. Vous êtes invité à contacter l’auteur si vous souhaitez contribuer ou redistribuer ce plugin.


