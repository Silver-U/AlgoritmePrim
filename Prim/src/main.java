import java.util.Scanner;

public class main
{
    public static void main(String[] args)
    {
        Labyrinthe monLabyr;
        int m, n;
        char reponse;
        Scanner scan = new Scanner(System.in);

        System.out.println("***************************");
        System.out.println("***************************");
        System.out.println("Bienvene dans notre programme : AlgorithmePrim");
        System.out.println("Nous allons nous charger de generer un labirynthe aleatoire");
        System.out.println("Labirynthe dans lequel vous aller nous donner les dimensions du labirynthe dont vous voulez");
        System.out.println("Nous allons nous charger de generer un labirynthe aleatoire");
        System.out.println("M ici represente le nombre de ligne du labirynthe");
        System.out.println("et N ici represente le nombre de colonne");

        while (true)
        {
            System.out.println("Entrer la valeur de M : ");
            m = scan.nextInt();
            System.out.println("Entrer la valeur de N : ");
            n = scan.nextInt();

            monLabyr = new Labyrinthe(m, n);

            System.out.println("initialisation et ponderation du graphe correspondant.................");
            monLabyr.generGraphe();

            System.out.println();
            System.out.println("Affichage de la matrice adjacente du grapher.................");
            System.out.println(monLabyr.toStringGraphe());

            System.out.println();
            System.out.println("Generation du labyrinthe.................");
            monLabyr.algorithmPrim();
            System.out.println("Affichage du labyrinthe.................");
            System.out.println(monLabyr);
            System.out.println("Nombre d’opérations élémentaires de l’initialisation de la matrice d’adjacence : " + monLabyr.getCompteurInit());
            System.out.println("Nombre d’opérations élémentaires de La génération du labyrinthe : " + monLabyr.getCompteurLabyr());
            System.out.println("Nombre d’opérations élémentaires de La lecture/l’affichage du labyrinthe : " + monLabyr.getCompteurAffichage());
            System.out.println("Nombre d’opérations élémentaires total : " + monLabyr.getCompteurTotal());

            System.out.println("Voulez Vous sortir ou contineur en generant d'autre matrice ?");
            System.out.println("Entrer S pour sortir OU Entrer C pour continuer");
            reponse = scan.next().charAt(0);

            if (reponse == 'S' || reponse == 's')
                break;
        }

        System.out.println("Bye bye");


    }
}
