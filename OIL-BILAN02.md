# Bilan livraison02

Scrum Master ce sprint : Admessiev Ayoub

## Fonctionnalités réalisées

Au cours de ce deuxième sprint, nous avons accompli les tâches suivantes :

1. Mise en place des requêtes WebFlux pour la communication entre les services.
2. Instantiation de cinq parties pour le jeu.
3. Création des différentes fonctions de vérification des figures du jeu Yahtzee.
4. Développement d'une fonction de marquage des figures complétées.
5. Mise en place de tests pour les services Hebergeur et Joueur.

## Fonctionnalités opérationnelles

Les fonctionnalités suivantes ont été implémentées et sont opérationnelles :

1. Un joueur peut demander à l'Appariement de lui trouver une partie.
2. L'Appariement interroge le Hebergeur pour vérifier la disponibilité d'une partie.
3. Le Hebergeur recherche une place aléatoire parmi les parties existantes et disponible (qui n'ont pas atteint le nombre de joueurs maximal).
4. Une fois que le joueur a rejoint la partie, le service Hebergeur affiche dans la console le message :
   "{id_partie} : Un joueur a rejoint cette partie (x/y)", où {id_partie} est l'identifiant de la partie, x est le nombre actuel de joueurs dans la partie et y est le nombre maximal de joueurs de la partie.
5. Le service Joueur affiche dans sa console le message : "Partie trouvée ({id_partie})".
6. Le joueur peut ensuite demander à Proba le meilleur coup à jouer. Actuellement, seule l'IA aléatoire est implémentée, renvoyant des positions de dés aléatoires à relancer.

## Prochaines étapes

Pour la prochaine livraison, nous prévoyons de travailler sur les éléments suivants :

1. Implémentation de l'IA avancée
2. Gestion des tours au cours de la partie
3. Mise en place de travis et docker (très prochainement)
4. Création et lancement d'une partie complète entre différents Joueurs. 

Scrum Master prochain sprint : Sahli Mootez

Ceci conclut le bilan de la deuxième livraison.
