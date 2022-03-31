import java.util.*;

public class Labyrinthe
{
    private int[] entreeLabyr;
    private int[] sortieLabyr;
    private Graphe grapheLabyr;
    private int nLab;
    private int mLab;
    private int nbNoueds;
//    private Graphe copieGr;
    private Graphe labFInal;
    private ArrayList<Integer> nouedVisiter;
    private int nombreO;
    private int nombreHastag;
    private int compteurInit;
    private int compteurLabyr;
    private int compteurAffichage;

    public int getNouedVisiter()
    {
        return nouedVisiter.size();
    }

    public int getNombreO()
    {
        return nombreO;
    }

    public int getNombreHastag()
    {
        return nombreHastag;
    }

    public Labyrinthe(int m, int n)
    {
        entreeLabyr = new int[2];
        sortieLabyr = new int[2];
        nLab = n;
        mLab = m;
        grapheLabyr = new Graphe(m , n);
        labFInal = new Graphe(m , n);
        nbNoueds = m * n;
        nouedVisiter = new ArrayList<Integer>();
        compteurInit = 0;
        compteurAffichage = 0;
        compteurLabyr = 0;
    }

    public void generGraphe()
    {
        genereEntreeSortie();
        ponderationGraphe();
    }

    //methode generant de maniere aleatoire l'entrer et la sortie du labyrinthe
    private void genereEntreeSortie()
    {
        entreeLabyr[0] = nombreAleatoire(mLab) - 1;
        entreeLabyr[1] = nombreAleatoire(nLab) - 1;

        do
        {
            sortieLabyr[0] = nombreAleatoire(mLab) - 1;
            sortieLabyr[1] = nombreAleatoire(nLab) - 1;
        }
        while ((entreeLabyr[0] == sortieLabyr[0]) && (entreeLabyr[1] == sortieLabyr[1]));
    }

    private void ponderationGraphe()
    {

        int i ;
        int j;

        compteurInit += 2;
        //la ponderation du graphe se fait en ponderant les liaisons de chaque noeuds
        for (int k = 0; k < nbNoueds; k++)
        {
            //on determine l'equivalent des coordonnes de la matrice adjacente dans notre graphe
            i = k / nLab;
            j = k % nLab;
            compteurInit += 2;

            //la ponderation est decide en fonction du type de noeud il en existe 3 :
            //ceux au coin qui n'ont que deux liens
            //ceux de au bord ou de type 3Loin qui n'ont que trois liens
            //Et le reste les noeuds a 4 lien
            //Ainsi grace au coordonne on peut determiner quel type de noeud on a affaire et dans quel sens sonst ses
            //liaisons
            //la methode etablissementDeLiason() permet de ponderer un noeud en lui donnant des coordonnes et le sens de
            //la ponderation
            if (siCoin(i, j))
            {
                switch (typeCoin(i, j))
                {
                    case basgauche -> {
                        etablissementDeLiason(i, j , k, Orientation.nord);
                        etablissementDeLiason(i, j , k, Orientation.est);
                    }
                    case basdroit -> {
                        etablissementDeLiason(i, j , k, Orientation.nord);
                        etablissementDeLiason(i, j , k, Orientation.ouest);
                    }
                    case hautguache -> {
                        etablissementDeLiason(i, j , k, Orientation.sud);
                        etablissementDeLiason(i, j , k, Orientation.est);
                    }
                    case hautdroit -> {
                        etablissementDeLiason(i, j , k, Orientation.sud);
                        etablissementDeLiason(i, j , k, Orientation.ouest);
                    }
                }
                compteurInit += 2;
            }
            else if (si3Lien(i, j))
            {
                switch (type3Loin(i, j))
                {
                    case aucunBas -> {
                        etablissementDeLiason(i, j , k, Orientation.nord);
                        etablissementDeLiason(i, j , k, Orientation.est);
                        etablissementDeLiason(i, j , k, Orientation.ouest);
                    }

                    case aucunHaut -> {
                        etablissementDeLiason(i, j , k, Orientation.sud);
                        etablissementDeLiason(i, j , k, Orientation.est);
                        etablissementDeLiason(i, j , k, Orientation.ouest);
                    }

                    case aucunDroit -> {
                        etablissementDeLiason(i, j , k, Orientation.nord);
                        etablissementDeLiason(i, j , k, Orientation.sud);
                        etablissementDeLiason(i, j , k, Orientation.ouest);
                    }

                    case aucunGuache -> {
                        etablissementDeLiason(i, j , k, Orientation.nord);
                        etablissementDeLiason(i, j , k, Orientation.sud);
                        etablissementDeLiason(i, j , k, Orientation.est);
                    }
                }
                compteurInit += 3;
            }
            else
            {
                etablissementDeLiason(i, j , k, Orientation.nord);
                etablissementDeLiason(i, j , k, Orientation.sud);
                etablissementDeLiason(i, j , k, Orientation.est);
                etablissementDeLiason(i, j , k, Orientation.ouest);
                compteurInit += 4;
            }
        }
    }

    //Ici on pondere chaque un noeud dans un sens
    //on regarde d'abord si la liaison dans sons sens est etabli si c'est le cas on genere le poids alleatiorement
    //si non on pondere la reciproque de la liaison dans la matrice adjacente
    private void etablissementDeLiason(int i, int j, int k, Orientation orientation)
    {
        switch (orientation)
        {
            case sud ->
                    {
                        if (grapheLabyr.getElement(deijAJgraphe(i + 1, j), deijAJgraphe(i, j)) == 0)
                        {
                            grapheLabyr.liason(k, deijAJgraphe(i + 1, j), nombreAleatoire(10));
                        }
                        else
                        {
                            grapheLabyr.liason(k, deijAJgraphe(i + 1, j), grapheLabyr.getElement(deijAJgraphe(i + 1, j), deijAJgraphe(i, j)));

                        }
                    }

            case nord ->
                    {
                        if (grapheLabyr.getElement(deijAJgraphe(i - 1, j), deijAJgraphe(i, j)) == 0)
                        {
                            grapheLabyr.liason(k, deijAJgraphe(i - 1, j), nombreAleatoire(10));
                        }
                        else
                        {
                            grapheLabyr.liason(k, deijAJgraphe(i - 1, j), grapheLabyr.getElement(deijAJgraphe(i - 1, j), deijAJgraphe(i, j)));
                        }
                    }
            case est ->
                    {
                        if (grapheLabyr.getElement(deijAJgraphe(i, j + 1) , deijAJgraphe(i, j)) == 0)
                        {
                            grapheLabyr.liason(k , deijAJgraphe(i, j + 1), nombreAleatoire(10));
                        }
                        else
                        {
                            grapheLabyr.liason(k , deijAJgraphe(i, j + 1), grapheLabyr.getElement(deijAJgraphe(i + 1, j) , deijAJgraphe(i, j)));
                        }
                    }

            case ouest ->
                    {
                        if (grapheLabyr.getElement(deijAJgraphe(i, j - 1), deijAJgraphe(i , j)) == 0)
                        {
                            grapheLabyr.liason(k , deijAJgraphe(i, j- 1), nombreAleatoire(10));
                        }
                        else
                        {
                            grapheLabyr.liason(k , deijAJgraphe(i, j- 1), grapheLabyr.getElement(deijAJgraphe(i, j - 1), deijAJgraphe(i , j)));
                        }
                    }
        }
    }

    public void algorithmPrim()
    {
        int iG = deijAJgraphe(entreeLabyr[0], entreeLabyr[1]);

        nouedVisiter.add(iG);

        ArrayList<Integer[]> lesPlusPetitsElementsdesLignes = new ArrayList<>();
        Integer[] lienMinimal;
//        int o = 0;
        compteurLabyr += 4;

        //Dans notre matrice adjacente chaque ligne reprensete un noeud
        //La maniere dont l'algorithme fonction est que on parcout chaque noeud(ligne dans la matrice adjacente)
        //on regarde la liaison la plus petit du noeud on la conserve et on lui compare a la liaison la plus petite de
        //chaque noeud qui on ete visiter pour selectionner la plus petite liaison parmi tout les candidates
        //cette liaison et donc inscrit dans le graphe finale
        for (int i = 0; i < nbNoueds - 2; i++)
        {
            compteurLabyr++;
            for (Integer ligne: nouedVisiter)
            {
                lesPlusPetitsElementsdesLignes.add(plusPetitDeLigne(grapheLabyr.getRows(ligne), ligne, false));
                compteurLabyr++;
            }

            lienMinimal = plusPetitParmisLesPlusPetits(lesPlusPetitsElementsdesLignes, nouedVisiter);
            grapheLabyr.liason(lienMinimal[2], lienMinimal[0], 0);
            grapheLabyr.liason(lienMinimal[0], lienMinimal[2], 0);

            labFInal.liason(lienMinimal[2], lienMinimal[0], 1);
            compteurLabyr += 4;


            if(labFInal.getElement(lienMinimal[0], lienMinimal[2]) == 0 )
            {
                labFInal.liason(lienMinimal[0], lienMinimal[2], 1);
                compteurLabyr ++;
            }

            if (!nouedVisiter.contains(lienMinimal[0]))
            {
                nouedVisiter.add(lienMinimal[0]);
                compteurLabyr++;
            }

            lesPlusPetitsElementsdesLignes.clear();
            compteurLabyr++;
        }

        Integer[] lienversortie = plusPetitDeLigne(grapheLabyr.getRows(deijAJgraphe(sortieLabyr[0], sortieLabyr[1])), deijAJgraphe(sortieLabyr[0], sortieLabyr[1]), true);
        labFInal.liason(lienversortie[2], lienversortie[0], 1);
        compteurLabyr += 2;

        if(labFInal.getElement(lienversortie[0], lienversortie[2]) == 0 )
        {
            labFInal.liason(lienversortie[0], lienversortie[2], 1);
            compteurLabyr++;
        }
        grapheLabyr = labFInal;
        compteurLabyr++;
    }


//        System.out.println("dasdasdasdasdasdasdasdas : " + nouedVisiter.size());
//        System.out.println("Nombre # : " + nombreHastag);
//        System.out.println("Nombre o : " + o);
    //la methode determine la liaison la plus petite parmi plusieurs candidats
    private Integer[] plusPetitParmisLesPlusPetits(ArrayList<Integer[]> listeDesplusPetits, ArrayList<Integer> nouedV)
    {
        int position = 0;

        if (listeDesplusPetits.size() == 1)
        {
            return listeDesplusPetits.get(0);
        }
        else
        {
            for (int i = 1; i < listeDesplusPetits.size(); i++)
            {
                if(listeDesplusPetits.get(position)[1] > listeDesplusPetits.get(i)[1])
                {
                    position = i;
                }
                else if ((listeDesplusPetits.get(position)[1] == listeDesplusPetits.get(i)[1]) && listeDesplusPetits.get(position)[1] != 11)
                {
                    if (nombreLien(labFInal.getRows(listeDesplusPetits.get(position)[0])) > nombreLien(labFInal.getRows(listeDesplusPetits.get(i)[0])))
                    {
                        position = i;
                    }
                    else
                    {
                        listeDesplusPetits.get(i)[1] = 11;
                    }
                }
            }
        }
        return listeDesplusPetits.get(position);
    }

    //determine le nombre de lien dans un graphe
    private int nombreLien(int[] row)
    {
        int n = 0;
        for (int i = 0; i < row.length; i++)
        {
            if (row[i] != 0)
                n++;
        }
        return n;
    }


    //la methode determine la liaison la plus petite d'un noeud(ligne de matrice adjacente)
    private Integer[] plusPetitDeLigne(int[] ligne, int iLigne, boolean sortie)
    {
        Integer[] positionValeurLigne = new Integer[3];
        positionValeurLigne[2]= iLigne;
        int positionduplusPetit = 0;
        int poids = 11;

        for (int i = 0; i < nbNoueds; i++)
        {
            if(ligne[i] != 0)
            {
                if (!sortie)
                {
                    if (poids > ligne[i] && i != deijAJgraphe(sortieLabyr[0], sortieLabyr[1]))
                    {
                        positionduplusPetit = i;
                        poids = ligne[i];
                    }
                }
                else
                {
                    if (poids > ligne[i] && i != deijAJgraphe(entreeLabyr[0], entreeLabyr[1]))
                    {
                        positionduplusPetit = i;
                        poids = ligne[i];
                    }
                }
            }
        }

        if (!sortie)
        {
            if(nouedVisiter.contains(positionduplusPetit) )
            {
                grapheLabyr.liason(positionduplusPetit, iLigne, 0);
                //reciprocite
                grapheLabyr.liason(iLigne, positionduplusPetit, 0);
                positionValeurLigne[0] = positionduplusPetit;
                positionValeurLigne[1] = 11;
            }
            else
            {
                positionValeurLigne[0] = positionduplusPetit;
                positionValeurLigne[1] = poids;
            }
        }
        else
        {
            positionValeurLigne[0] = positionduplusPetit;
            positionValeurLigne[1] = poids;
        }
        return positionValeurLigne;
    }


    //equivalent des coordonnes d'un graphe a celui d'une matrice adjacente
    public int deijAJgraphe(int i, int j)
    {
        return (nLab * i) + j;
    }

    //Nous dit si on a affaire a un coin d'apres ses coordonnees
    private boolean siCoin(int i, int j)
    {
        return ((i == 0) && (j == 0)) || ((i == mLab - 1) && (j == 0)) || ((i == mLab - 1 ) && (j == nLab - 1)) || ((i == 0) && (j == nLab - 1));
    }

    //Nous dit si a que type de coin on a affaire d'apres ses coordonnees
    private TypeDeCoin typeCoin(int i, int j)
    {
        if ((i == 0) && (j == 0))
            return TypeDeCoin.hautguache;
        else if ((i == mLab - 1) && (j == 0))
            return TypeDeCoin.basgauche;
        else if ((i == mLab - 1 ) && (j == nLab - 1))
            return TypeDeCoin.basdroit;
        else
            return TypeDeCoin.hautdroit;
    }

    //Nous dit si on a affaire a un coin d'apres ses coordonnees
    private boolean si3Lien(int i, int j)
    {
        return (i == 0) || (j == 0) || (i == mLab - 1 ) || (j == nLab - 1);
    }

    //Nous dit si a que type de noeud a 3Lien on a affaire d'apres ses coordonnees
    private Type3Lien type3Loin(int i, int j)
    {
        if (i == 0)
            return Type3Lien.aucunHaut;
        else if (j == 0)
            return Type3Lien.aucunGuache;
        else if (i == mLab - 1 )
            return Type3Lien.aucunBas;
        else
            return Type3Lien.aucunDroit;
    }


    private int nombreAleatoire(int a)
    {
        return (int) (Math.random() * a) + 1;
    }

    public int getCompteurInit()
    {
        return compteurInit;
    }

    public int getCompteurTotal()
    {
        return compteurInit + compteurLabyr + compteurLabyr;
    }

    public int getCompteurLabyr()
    {
        return compteurLabyr;
    }

    public int getCompteurAffichage()
    {
        return compteurAffichage;
    }

    @Override
    public String toString()
    {
        String affichage = "";
        String firstLigne = "";
        String secondLigne = "";

        compteurAffichage += 3;
        for (int i = 0; i < mLab; i++)
        {
            compteurAffichage++;
            for (int j = 0; j < nLab; j++)
            {
                if (i == entreeLabyr[0] && j == entreeLabyr[1])
                {
                    firstLigne += " E ";
                }
                else if (i == sortieLabyr[0] && j == sortieLabyr[1])
                {
                    firstLigne += " S ";
                }
                else
                {
                    firstLigne += " 0 ";
                }
                compteurAffichage++;

                if (j + 1 < nLab)
                {
                    if (grapheLabyr.getElement(deijAJgraphe(i, j + 1), deijAJgraphe(i, j)) != 0)
                    {
                        firstLigne += " 0 ";
                    }
                    else
                    {
                        firstLigne += " # ";
                    }
                }
                compteurAffichage++;

                if (i + 1 < mLab)
                {
                    if (grapheLabyr.getElement(deijAJgraphe(i + 1, j), deijAJgraphe(i, j)) != 0)
                    {
                        secondLigne += " 0 ";
                    }
                    else
                    {
                        secondLigne += " # ";
                    }
                }
                compteurAffichage++;

                if ((j + 1 < nLab ))
                {
                    secondLigne += " # ";

                }
                compteurAffichage++;

            }
            affichage += "\n" + firstLigne + "\n" + secondLigne;
            firstLigne = "";
            secondLigne = "";
            compteurAffichage += 3;
        }

        compteurAffichage++;
        return affichage;
    }



    public String toStringGraphe()
    {
        return "Entree i = " + entreeLabyr[0] + ", j = " + entreeLabyr[1] +
                "\nSortie i = " + sortieLabyr[0] + ", j = " + sortieLabyr[1] +
                "\nLabyrinthe \n" + grapheLabyr;
    }
}
