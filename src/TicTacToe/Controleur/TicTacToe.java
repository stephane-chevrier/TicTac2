package TicTacToe.Controleur;

import TicTacToe.Model.*;
import TicTacToe.Viewer.*;
import java.io.Serializable;

/**
 * Nom             TicTacToe.Controleur.TicTacToe
 * Description     Contrôleur jeu TicTacToe.Controleur.TicTacToe (MVC)
 * @version v1.0
 * Date            12 décembre 2022
 * @author Stéphane CHEVRIER
 */

public class TicTacToe extends GameControleur implements Serializable {

    /**
     * constructeur
     */
    public TicTacToe() {
        this.size = 2;      /* =n défini un plateau de n+1 * n+1 cellules pour le jeu TicTacToe.Controleur.TicTacToe */
        this.nbreAlignements = (size + 1) * 3;   // on aura besoin de 3 blocs : lignes, colonnes, diagonales
        this.alignementGagnant = 3;
        this.viewer = new Viewer();
        this.damier = new Damier(this.size);                 // this.size permet de spécifier size de l'objet TicTacToe.Controleur.TicTacToe
    }

    /**
     *     méthode de calcul : des sommes de chaque alignement,
     *                         du nombre de coups joués
     * @return ArrayList<Integer>(3) : Somme de chaque alignement, nombre de coups joués
     */
    @Override
    public String[] calculerAlignements() {

        // RAZ du tableau de calcul des sommes et du nombre de coups joués
        int nombreCoupsJoues = 0;
        String[] calcul = new String[nbreAlignements];

        // Copie locale du plateau
        Cell[][] plateau = damier.getPlateau();
        for (int i = 0; i <= calcul.length - 1; i++) {
            calcul[i] = "";
        }
        // Double boucle de calcul des sommes (size lignes, size colonnes, 2 diagonales)
        for (int i = 0; i <= size; i++) {
            calcul[(size*2+2)+1] += plateau[i][i].getRepresentationBrut();      // somme de la diagonale 0.0+1.1+2.2+... dans index size*2 + 1
            calcul[(size*2+2)+2] += plateau[i][size - i].getRepresentationBrut(); // somme de la diagonale 0.size+1.1+... dans index size*2 + 2
            for (int j = 0; j <= size; j++) {
                calcul[j] += plateau[i][j].getRepresentationBrut();   // somme des lignes 0-1-2 dans index 0-1-2
                calcul[j + size + 1] += plateau[j][i].getRepresentationBrut(); // somme des colonnes 0-1-2 dans index 3-4-5
                // Calcul du nombre de coups joués
                if (plateau[i][j].getValue() != Player.CASE_VALUE[0]) {
                    nombreCoupsJoues++;
                }
            }
        }

        // retour du nombre de coups joués
        calcul[nbreAlignements-1] = calcul[(size +1)*3-1].valueOf(nombreCoupsJoues);

        // fin de la fonction
        return calcul;
    }

}