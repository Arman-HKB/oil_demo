package com.orca.joueur;

import org.jetbrains.annotations.NotNull;

public class IA {

    public static double isBrelan(int[] state, int depth) {
        if (depth == 0) {
            return 0.0;  // Aucun lancer restant, donc la probabilité est nulle
        }

        int count = 0;
        int[] freq = new int[7];  // Tableau de fréquences pour chaque face de dé (de 1 à 6)

        for (int i = 0; i < state.length; i++) {
            freq[state[i]]++;  // Calcul des fréquences des faces de dé actuelles
            if (freq[state[i]] == 3) {
                return 1.0;  // Si on a déjà un brelan, la probabilité est de 100%
            }
        }

        for (int i = 1; i <= 6; i++) {
            if (freq[i] >= 2) {
                count++;
            }
        }

        // Calcul de la probabilité en utilisant la formule : (6 * count) / (6^depth)
        double probability = (6.0 * count) / Math.pow(6.0, depth);
        return probability;
    }


    public static double isCarre(int[] state, int depth){
        if (depth == 0) {
            return 0.0;  // Aucun lancer restant, donc la probabilité est nulle
        }

        int count = 0;
        int[] freq = new int[7];  // Tableau de fréquences pour chaque face de dé (de 1 à 6)

        for (int i = 0; i < state.length; i++) {
            freq[state[i]]++;  // Calcul des fréquences des faces de dé actuelles
            if (freq[state[i]] == 4) {
                return 1.0;  // Si on a déjà un carré, la probabilité est de 100%
            }
        }
        for (int i = 1; i <= 6; i++) {
            if (freq[i] >= 3) {
                count++;
            }
        }
        // Calcul de la probabilité en utilisant la formule : (6 * count) / (6^depth)
        //double probability = (double) count / Math.pow(6.0, depth - 1);
        double probability = (6.0 * count) / Math.pow(6.0, depth);
        return probability;
    }

    //possiblité d'avoir un full
    public static double isFull(int[] state, int depth) {
        if (depth == 0) {
            return 0.0;  // Aucun lancer restant, donc la probabilité est nulle
        }

        int[] freq = new int[7];  // Tableau de fréquences pour chaque face de dé (de 1 à 6)

        for (int i = 0; i < state.length; i++) {
            freq[state[i]]++;  // Calcul des fréquences des faces de dé actuelles
            if (freq[state[i]] == 3) {
                // Si on a déjà un brelan, la probabilité d'obtenir un full dépend uniquement du dé différent
                int count = 0;
                for (int j = 1; j <= 6; j++) {
                    if (freq[j] >= 2 && j != state[i]) {
                        count++;
                    }
                }

                // Calcul de la probabilité en utilisant la formule : (count) / (6^depth)
                double probability = (double) count / Math.pow(6.0, depth);
                return probability;
            }
        }
        return 0.0;  // Aucun brelan trouvé, la probabilité d'obtenir un full est nulle
    }

    public static double isSmallSuite(int[] state, int depth) {
        if (depth == 0) {
            return 0.0;  // Aucun lancer restant, donc la probabilité est nulle
        }

        int[] freq = new int[7];  // Tableau de fréquences pour chaque face de dé (de 1 à 6)

        for (int i = 0; i < state.length; i++) {
            freq[state[i]]++;  // Calcul des fréquences des faces de dé actuelles
        }

        int count = 0;
        for (int i = 1; i <= 3; i++) {
            // Si une face de dé a une fréquence nulle, la petite suite n'est pas possible
            if (freq[i] == 0 || freq[i+1] == 0) {
                return 0.0;
            }
            count++;
        }

        // Calcul de la probabilité en utilisant la formule : (count) / (6^depth)
        double probability = (double) count / Math.pow(6.0, depth);
        return probability;
    }

    public static double isBigSuite(int[] state, int depth) {
        if (depth == 0) {
            return 0.0;  // Aucun lancer restant, donc la probabilité est nulle
        }

        int[] freq = new int[7];  // Tableau de fréquences pour chaque face de dé (de 1 à 6)

        for (int i = 0; i < state.length; i++) {
            freq[state[i]]++;  // Calcul des fréquences des faces de dé actuelles
        }

        int count = 0;
        for (int i = 1; i <= 2; i++) {
            // Si une face de dé a une fréquence nulle, la grande suite n'est pas possible
            if (freq[i] == 0 || freq[i+1] == 0 || freq[i+2] == 0) {
                return 0.0;
            }
            count++;
        }

        // Calcul de la probabilité en utilisant la formule : (count) / (6^depth)
        double probability = (double) count / Math.pow(6.0, depth);
        return probability;
    }

    public static double isYams(int[] state, int depth) {
        if (depth == 0) {
            return 0.0;  // Aucun lancer restant, donc la probabilité est nulle
        }

        int[] freq = new int[7];  // Tableau de fréquences pour chaque face de dé (de 1 à 6)

        for (int i = 0; i < state.length; i++) {
            freq[state[i]]++;  // Calcul des fréquences des faces de dé actuelles
            if (freq[state[i]] == 5) {
                return 1.0;  // Si on a déjà un Yams, la probabilité est de 100%
            }
        }

        // Recherche de la probabilité pour obtenir un Yams avec les lancers restants
        int count = 0;
        for (int i = 1; i <= 6; i++) {
            if (freq[i] >= 4) {
                count++;
            }
        }

        // Calcul de la probabilité en utilisant la formule : (count) / (6^(depth-1))
        double probability = (double) count / Math.pow(6.0, depth - 1);
        return probability;
    }

    public static int minimax(int[] state, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        if (depth == 0) {
            return evaluateStateSup(state);
        }
        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < state.length; i++) {
                if (state[i] == 0) {
                    state[i] = 1;
                    int eval = minimax(state, depth - 1, alpha, beta, false);
                    state[i] = 0;
                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, eval);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int i = 0; i < state.length; i++) {
                if (state[i] == 0) {
                    state[i] = -1;
                    int eval = minimax(state, depth - 1, alpha, beta, true);
                    state[i] = 0;
                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, eval);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return minEval;
        }
    }

    @NotNull
    public static int evaluateStateSup(int @NotNull [] state) {
        int score = 0;
        int bonus = 0;
        int[] freq = new int[7]; // compter la fréquence de chaque nombre
        for (int i = 0; i < state.length; i++) {
            freq[state[i]]++;
            score += state[i];
        }
        // Vérifier si le bonus a été obtenu
        boolean bonusObtenu = true;
        for (int i = 1; i <= 6; i++) {
            if (freq[i] < 1) {
                bonusObtenu = false;
                break;
            }
        }
        if (bonusObtenu) {
            bonus = 35;
        }
        // Ajouter le bonus et renvoyer le score total
        return score + bonus;
    }
    public static double calculateBrelanProbability(int[] state, int n) {
        int totalDice = 0;
        int totalPossibleBrelans = 0;

        for (int i = 0; i < state.length; i++) {
            totalDice += state[i];

            if (state[i] >= 3) {
                totalPossibleBrelans += state[i] * (state[i] - 1) * (state[i] - 2) / 6;
            }
        }

        double probability = 0;

        if (totalPossibleBrelans > 0) {
            double remainingDiceProbability = 0;

            // Calcul de la probabilité d'obtenir un brelan avec les dés restants
            for (int i = 0; i < state.length; i++) {
                if (state[i] > 0) {
                    state[i]--; // Retire un dé de cette valeur

                    int remainingBrelanCount = 0;

                    for (int j = 0; j < state.length; j++) {
                        if (state[j] >= 3) {
                            remainingBrelanCount++;
                        }
                    }

                    remainingDiceProbability += (double) remainingBrelanCount / totalDice;

                    state[i]++; // Remet le dé retiré
                }
            }

            probability = 1 - Math.pow((1 - (totalPossibleBrelans / (Math.pow(totalDice, 3) / 6))), n) * (1 - remainingDiceProbability);
        }

        return probability;
    }

    public static double calculateCarreProbability(int[] state, int n) {
        int totalDice = 0;
        int totalPossibleCarres = 0;

        for (int i = 0; i < state.length; i++) {
            totalDice += state[i];

            if (state[i] >= 4) {
                totalPossibleCarres += state[i] * (state[i] - 1) * (state[i] - 2) * (state[i] - 3) / 24;
            }
        }

        double probability = 0;

        if (totalPossibleCarres > 0) {
            double remainingDiceProbability = 0;

            // Calcul de la probabilité d'obtenir un carré avec les dés restants
            for (int i = 0; i < state.length; i++) {
                if (state[i] > 0) {
                    state[i]--; // Retire un dé de cette valeur

                    int remainingCarreCount = 0;

                    for (int j = 0; j < state.length; j++) {
                        if (state[j] >= 4) {
                            remainingCarreCount++;
                        }
                    }

                    remainingDiceProbability += (double) remainingCarreCount / totalDice;

                    state[i]++; // Remet le dé retiré
                }
            }

            probability = 1 - Math.pow((1 - (totalPossibleCarres / (Math.pow(totalDice, 4) / 24))), n) * (1 - remainingDiceProbability);
        }

        return probability;
    }

    public static double probabilityOfFull(int[] state, int depth) {
        if (depth == 0) {
            // Si on a atteint la profondeur 0, c'est-à-dire qu'on a terminé tous les lancers disponibles
            // On vérifie si on a un full ou non
            return isFull(state) ? 1.0 : 0.0;
        } else {
            double totalProb = 0.0;
            for (int i = 1; i <= 6; i++) {
                // Pour chaque valeur possible d'un dé (de 1 à 6)
                // On simule le fait de garder tous les dés de cette valeur et de relancer les autres
                int[] newState = state.clone(); // Création d'une copie du tableau d'état
                int count = newState[i - 1]; // Nombre de dés avec la valeur i dans le tableau d'état

                if (count > 0) {
                    newState[i - 1] -= 1; // On retire un dé avec la valeur i du tableau d'état
                    double prob = probabilityOfFull(newState, depth - 1); // Appel récursif pour les lancers restants
                    totalProb += prob * count / sum(state); // Mise à jour de la probabilité totale en tenant compte du nombre de dés de la valeur i
                }
            }
            return totalProb;
        }
    }

    public static boolean isFull(int[] state) {
        // Vérifie si le tableau d'état représente un full
        boolean threeOfAKind = false;
        boolean twoOfAKind = false;

        for (int count : state) {
            if (count >= 3) {
                threeOfAKind = true;
            }
            if (count >= 2) {
                twoOfAKind = true;
            }
        }
        return threeOfAKind && twoOfAKind;
    }

    public static int sum(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] state = {1, 1, 2, 2, 6};
        int depth = 3;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        boolean isMaximizingPlayer = true;
        System.out.println("Brelan : " + calculateBrelanProbability(state, depth));
        System.out.println("Carre : " + calculateCarreProbability(state, depth));
        System.out.println("full "+probabilityOfFull(state, depth));
        System.out.println("isSmallSuite : " + isSmallSuite(state, depth));
        System.out.println("isBigSuite : " + isBigSuite(state, depth));
        System.out.println("isYams : " + isYams(state, depth));
        System.out.println("isFull : " + isFull(state, depth));
        System.out.println("isBrelan : " + isBrelan(state, depth));
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                state[i] = 1;
                int score = minimax(state, depth - 1, alpha, beta, false);
                state[i] = 0;
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
                alpha = Math.max(alpha, score);
            }
        }

        System.out.println("Meilleur coup : " + bestMove);
    }
}
