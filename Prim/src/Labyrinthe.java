import java.util.*;

public class Labyrinthe
{
    private int[] entreeLabyr;
    private int[] sortieLabyr;
    private Graphe grapheLabyr;
    private int nLab;
    private int mLab;
    private int nbNoueds;
    private Graphe copieGr;
    private Graphe labFInal;
    private ArrayList<Integer> nouedVisiter;
    private int nombreO;
    private int nombreHastag;

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
        genereEntreeSortie();
//        ponderationGraphe();
    }

    public void setEntreeLabyr(int[] entreeLabyr)
    {
        this.entreeLabyr = entreeLabyr;
    }

    public void setSortieLabyr(int[] sortieLabyr)
    {
        this.sortieLabyr = sortieLabyr;
    }

    public void setGrapheLabyr(Graphe grapheLabyr)
    {
        this.grapheLabyr = grapheLabyr;
    }

    private void genereLabyrinthe()
    {
        genereEntreeSortie();
        ponderationGraphe();
    }

    public Graphe getCopieGr()
    {
        return copieGr;
    }

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

    public void ponderationGraphe()
    {

        int i ;
        int j;
        int coin = 0, lien3 = 0, centre = 0;

        for (int k = 0; k < nbNoueds; k++)
        {
            i = k / nLab;
            j = k % nLab;

            if (siCoin(i, j))
            {
                coin++;
                switch (typeCoin(i, j))
                {
                    case basgauche -> {
//                        System.out.println("basgauche");


                        etablissementDeLiason(i, j , k, Orientation.nord);
                        etablissementDeLiason(i, j , k, Orientation.est);

//                        //nord
//                        grapheLabyr.liason(k, deijAJgraphe(i - 1, j), nombreAleatoire(10));
//                        //east
//                        grapheLabyr.liason(k, deijAJgraphe(i, j + 1), nombreAleatoire(10));
                    }
                    case basdroit -> {
//                        System.out.println("basdroit");


                        etablissementDeLiason(i, j , k, Orientation.nord);
                        etablissementDeLiason(i, j , k, Orientation.ouest);

//                        //nord
//                        grapheLabyr.liason(k, deijAJgraphe(i - 1, j), nombreAleatoire(10));
//                        //west
//                        grapheLabyr.liason(k , deijAJgraphe(i, j - 1), nombreAleatoire(10));
                    }

                    case hautguache -> {

//                        System.out.println("hautguache");

                        etablissementDeLiason(i, j , k, Orientation.sud);
                        etablissementDeLiason(i, j , k, Orientation.est);

//                        //east
//                        grapheLabyr.liason(k , deijAJgraphe(i, j + 1), nombreAleatoire(10));
//                        //sud
//                        grapheLabyr.liason(k , deijAJgraphe(i + 1, j), nombreAleatoire(10));
                    }
                    case hautdroit -> {

//                        System.out.println("hautdroit");

                        etablissementDeLiason(i, j , k, Orientation.sud);
                        etablissementDeLiason(i, j , k, Orientation.ouest);

//                        //sud
//                        grapheLabyr.liason(k , deijAJgraphe(i + 1, j), nombreAleatoire(10));
//                        //west
//                        grapheLabyr.liason(k , deijAJgraphe(i , j- 1), nombreAleatoire(10));
                    }
                }
            }
            else if (si3Lien(i, j))
            {
                lien3++;
                switch (type3Loin(i, j))
                {
                    case aucunBas -> {
//                        System.out.println("aucunBas");

                        etablissementDeLiason(i, j , k, Orientation.nord);
                        etablissementDeLiason(i, j , k, Orientation.est);
                        etablissementDeLiason(i, j , k, Orientation.ouest);

//                        //nord
//                        grapheLabyr.liason(k, deijAJgraphe(i - 1, j), nombreAleatoire(10));
//                        //west
//                        grapheLabyr.liason(k , deijAJgraphe(i,  - 1), nombreAleatoire(10));
//                        //east
//                        grapheLabyr.liason(k , deijAJgraphe(i, j + 1), nombreAleatoire(10));
                    }

                    case aucunHaut -> {
//                        System.out.println("aucunHaut");

                        etablissementDeLiason(i, j , k, Orientation.sud);
                        etablissementDeLiason(i, j , k, Orientation.est);
                        etablissementDeLiason(i, j , k, Orientation.ouest);

//                        //west
//                        grapheLabyr.liason(k , deijAJgraphe(i,  j- 1), nombreAleatoire(10));
//                        //east
//                        grapheLabyr.liason(k , deijAJgraphe(i, j + 1), nombreAleatoire(10));
//                        //sud
//                        grapheLabyr.liason(k , deijAJgraphe(i + 1, j), nombreAleatoire(10));
                    }

                    case aucunDroit -> {


//                        System.out.println("hein");

                        etablissementDeLiason(i, j , k, Orientation.nord);
                        etablissementDeLiason(i, j , k, Orientation.sud);
                        etablissementDeLiason(i, j , k, Orientation.ouest);
//                        System.out.println("aucunDroit");
//                        //nord
//                        grapheLabyr.liason(k, deijAJgraphe(i - 1, j), nombreAleatoire(10));
//                        //west
//                        grapheLabyr.liason(k , deijAJgraphe(i ,j- 1), nombreAleatoire(10));
//                        //sud
//                        grapheLabyr.liason(k , deijAJgraphe(i + 1, j), nombreAleatoire(10));
                    }

                    case aucunGuache -> {
//                        System.out.println("aucunGuache");

                        etablissementDeLiason(i, j , k, Orientation.nord);
                        etablissementDeLiason(i, j , k, Orientation.sud);
                        etablissementDeLiason(i, j , k, Orientation.est);
//                        System.out.println("aucunGuache");

//                        //nord
//                        grapheLabyr.liason(k, deijAJgraphe(i - 1, j), nombreAleatoire(10));
//                        //east
//                        grapheLabyr.liason(k , deijAJgraphe(i, j + 1), nombreAleatoire(10));
//                        //sud
//                        grapheLabyr.liason(k , deijAJgraphe(i + 1, j), nombreAleatoire(10));
                    }
                }
            }
            else
            {
//                centre++;

                etablissementDeLiason(i, j , k, Orientation.nord);
                etablissementDeLiason(i, j , k, Orientation.sud);
                etablissementDeLiason(i, j , k, Orientation.est);
                etablissementDeLiason(i, j , k, Orientation.ouest);
//                //nord
//                grapheLabyr.liason(k, deijAJgraphe(i - 1, j), nombreAleatoire(10));
//                //west
//                grapheLabyr.liason(k , deijAJgraphe(i,  - 1), nombreAleatoire(10));
//                //east
//                grapheLabyr.liason(k , deijAJgraphe(i, j + 1), nombreAleatoire(10));
//                //sud
//                grapheLabyr.liason(k , deijAJgraphe(i + 1, j), nombreAleatoire(10));
            }
        }
//        System.out.println("noeud au coin : " + coin);
//        System.out.println("noeud de 3 lien : " + lien3);
//        System.out.println("noeud au centre : " + centre);
    }

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
        copieGr = new Graphe(grapheLabyr);
        int iG = deijAJgraphe(entreeLabyr[0], entreeLabyr[1]);

        nouedVisiter.add(iG);

        ArrayList<Integer[]> lesPlusPetitsElementsdesLignes = new ArrayList<>();
        Integer[] lienMinimal;
        int o = 0;

        for (int i = 0; i < nbNoueds - 2; i++)
//        while (nouedVisiter.size() < nbNoueds)
        {
//            if (i == 5)
//                System.out.println("yo");
            for (Integer ligne: nouedVisiter)
            {
                lesPlusPetitsElementsdesLignes.add(plusPetitDeLigne(grapheLabyr.getRows(ligne), ligne, false));
//                    lesPlusPetitsElementsdesLignes.add(plusPetitDeLigne(grapheLabyr.getRows(nouedVisiter.get(ligne)), nouedVisiter.get(ligne)));
            }
            lienMinimal = plusPetitParmisLesPlusPetits(lesPlusPetitsElementsdesLignes, nouedVisiter);
            grapheLabyr.liason(lienMinimal[2], lienMinimal[0], 0);
            grapheLabyr.liason(lienMinimal[0], lienMinimal[2], 0);

            labFInal.liason(lienMinimal[2], lienMinimal[0], 1);

            if(labFInal.getElement(lienMinimal[0], lienMinimal[2]) == 0 )
                labFInal.liason(lienMinimal[0], lienMinimal[2], 1);

            if (!nouedVisiter.contains(lienMinimal[0]))
                nouedVisiter.add(lienMinimal[0]);

            lesPlusPetitsElementsdesLignes.clear();
//            System.out.println(nouedVisiter.size());

//            System.out.println(toStringLaby(labFInal));
            o++;
        }



        grapheLabyr = labFInal;
        String affiche = toStringLaby();
        int count = 0;
        for (int i = 0; i < affiche.length(); i++) {
            if (affiche.charAt(i) == '#') {
                count++;
            }
        }

        nombreO = o;
        nombreHastag = count - 2;
        System.out.println("dasdasdasdasdasdasdasdas : " + nouedVisiter.size());
        System.out.println("Nombre # : " + nombreHastag);
        System.out.println("Nombre o : " + o);

    }

    private Integer[] plusPetitParmisLesPlusPetits(ArrayList<Integer[]> listeDesplusPetits, ArrayList<Integer> nouedV)
    {
        int position = 0;
        int doublon = -1;

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
//                    System.out.println("rien");
                    position = i;
                }
                else if ((listeDesplusPetits.get(position)[1] == listeDesplusPetits.get(i)[1]) && listeDesplusPetits.get(position)[1] != 11)
                {
//                    System.out.println("doublon : " + listeDesplusPetits.get(position)[1]);
                    int a = nombreLien(labFInal.getRows(listeDesplusPetits.get(position)[0]));
                    int b = nombreLien(labFInal.getRows(listeDesplusPetits.get(i)[0]));

                    int c = nombreLien(labFInal.getRows(listeDesplusPetits.get(position)[2]));
                    int d = nombreLien(labFInal.getRows(listeDesplusPetits.get(i)[2]));

                    if (nombreLien(labFInal.getRows(listeDesplusPetits.get(position)[0])) > nombreLien(labFInal.getRows(listeDesplusPetits.get(i)[0])))
                    {

                        System.out.println("1ere cas ");
//                        grapheLabyr.liason(listeDesplusPetits.get(position)[2], listeDesplusPetits.get(position)[0], 0);
//
//                        //reciprocite
//                        grapheLabyr.liason(listeDesplusPetits.get(position)[0], listeDesplusPetits.get(position)[2], 0);
                    position = i;
                    }
                    else
                    {
                        System.out.println("2eme cas ");
//                        grapheLabyr.liason(listeDesplusPetits.get(i)[2], listeDesplusPetits.get(i)[0], 0);
//
//                        //reciprocite
//                        grapheLabyr.liason(listeDesplusPetits.get(i)[0], listeDesplusPetits.get(i)[2], 0);
                        listeDesplusPetits.get(i)[1] = 11;
                    }
//                    else if(nombreLien(labFInal.getRows(listeDesplusPetits.get(position)[0])) < nombreLien(labFInal.getRows(listeDesplusPetits.get(i)[0])))
//                    {
//                        System.out.println("2eme cas ");
//                        grapheLabyr.liason(listeDesplusPetits.get(i)[2], listeDesplusPetits.get(i)[0], 0);
//
//                        //reciprocite
//                        grapheLabyr.liason(listeDesplusPetits.get(i)[0], listeDesplusPetits.get(i)[2], 0);
//                        listeDesplusPetits.get(i)[1] = 11;
//                    }
//                    else
//                    {
//                        System.out.println("egalite");
//                    }




//                  System.out.println("doublon");
//                   doublon = i;
//                    grapheLabyr.liason(listeDesplusPetits.get(i)[2], listeDesplusPetits.get(i)[0], 0);

//                    if(grapheLabyr.getElement(listeDesplusPetits.get(i)[0], listeDesplusPetits.get(i)[2]) == 0 )
//                    {
//                        grapheLabyr.liason(listeDesplusPetits.get(i)[0], listeDesplusPetits.get(i)[2], 0);
//                        grapheLabyr.liason(listeDesplusPetits.get(i)[2], listeDesplusPetits.get(i)[0], 0);
//                    }
//                    else
//                    {
//                        grapheLabyr.liason(listeDesplusPetits.get(i)[2], listeDesplusPetits.get(i)[0], 0);
//                    }


//                    listeDesplusPetits.get(i)[1] = 11;
                }
            }
        }


        return listeDesplusPetits.get(position);

    }

    private int nombreLien(int[] row)
    {
        int n = 0;

        for (int i = 0; i < row.length; i++)
        {
//            System.out.println("stuck");
            if (row[i] != 0)
                n++;
        }
//
//        while (n < row.length)
//        {
//
//            if (row[n] != 0)
//                n++;
//        }

        return n;

    }


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
                    if (poids > ligne[i])
                    {
                        positionduplusPetit = i;
                        poids = ligne[i];
                    }
                }
            }
        }

//        if(nouedVisiter.contains(ligne[i]))
//        {
//            grapheLabyr.liason(i, iLigne, 0);
//            //reciprocite
//            grapheLabyr.liason(iLigne, i, 0);
//
//        }


        if(nouedVisiter.contains(positionduplusPetit))
        {
//            System.out.println("position plus petit : " + positionduplusPetit + "ligne : " + iLigne + "poids : " + poids);
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

        return positionValeurLigne;
    }

//    private HashMap<Integer, Integer> sortHashmap(HashMap<Integer, Integer> hm)
//    {
//
//        Collections.sort(hm, new Comparator<Map.Entry<String, Integer> >() {
//            public int compare(Map.Entry<String, Integer> o1,
//                               Map.Entry<String, Integer> o2)
//            {
//                return (o1.getValue()).compareTo(o2.getValue());
//            }
//        });
//
//        // put data from sorted list to hashmap
//        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
//        for (Map.Entry<String, Integer> aa : hm) {
//            temp.put(aa.getKey(), aa.getValue());
//        }
//        return temp;
//
//    }


    public Graphe getGrapheLabyr()
    {
        return grapheLabyr;
    }

    public int deijAJgraphe(int i, int j)
    {
        return (nLab * i) + j;
    }

//    public void ponderationGraphe()
//    {
//        for (int i = 0; i < nbNoueds; i++)
//        {
//            for (int j = 0; j < nbNoueds; j++)
//            {
//                if (siCoin(i, j))
//                {
//                    switch (typeCoin(i, j))
//                    {
//                        case basgauche -> {
//                            System.out.println("basgauche");
//
//                            //nord
//                            grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
//                            //east
//                            grapheLabyr.liason(i , j + 1, nombreAleatoire(10));
//
//                        }
//
//                        case basdroit -> {
//                            System.out.println("basdroit");
//
//                            //nord
//                            grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
//                            //west
//                            grapheLabyr.liason(i , j - 1, nombreAleatoire(10));
//                        }
//
//                        case hautguache -> {
//                            System.out.println("hautguache");
//
//                            //east
//                            grapheLabyr.liason(i , j + 1, nombreAleatoire(10));
//                            //sud
//                            grapheLabyr.liason(i + 1, j, nombreAleatoire(10));
//
//                        }
//
//                        case hautdroit -> {
//                            System.out.println("hautdroit");
//                            //sud
//                            grapheLabyr.liason(i + 1, j, nombreAleatoire(10));
//                            //west
//                            grapheLabyr.liason(i , j - 1, nombreAleatoire(10));
//
//                        }
//                    }
//                }
//                else if (si3Lien(i, j))
//                {
//                    switch (type3Loin(i, j))
//                    {
//                        case aucunBas -> {
//                            //nord
//                            grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
//                            //west
//                            grapheLabyr.liason(i , j - 1, nombreAleatoire(10));
//                            //east
//                            grapheLabyr.liason(i , j + 1, nombreAleatoire(10));
//                        }
//
//                        case aucunHaut -> {
//                            //west
//                            grapheLabyr.liason(i , j - 1, nombreAleatoire(10));
//                            //east
//                            grapheLabyr.liason(i , j + 1, nombreAleatoire(10));
//                            //sud
//                            grapheLabyr.liason(i + 1, j, nombreAleatoire(10));
//                        }
//
//                        case aucunDroit -> {
//                            //nord
//                            grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
//                            //west
//                            grapheLabyr.liason(i , j - 1, nombreAleatoire(10));
//                            //sud
//                            grapheLabyr.liason(i + 1, j, nombreAleatoire(10));
//                        }
//
//                        case aucunGuache -> {
//                            //nord
//                            grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
//                            //east
//                            grapheLabyr.liason(i , j + 1, nombreAleatoire(10));
//                            //sud
//                            grapheLabyr.liason(i + 1, j, nombreAleatoire(10));
//                        }
//                    }
//                }
//                else
//                {
//                    //nord
//                    grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
//                    //west
//                    grapheLabyr.liason(i , j - 1, nombreAleatoire(10));
//                    //east
//                    grapheLabyr.liason(i , j + 1, nombreAleatoire(10));
//                    //sud
//                    grapheLabyr.liason(i + 1, j, nombreAleatoire(10));
//                }
//            }
//        }
//    }

    private boolean siCoin(int i, int j)
    {
//        return ((i == 0) && (j == 0)) || ((i == nbNoueds - 1) && (j == 0)) || ((i == nbNoueds - 1 ) && (j == nbNoueds - 1)) || ((i == 0) && (j == nbNoueds - 1));

        return ((i == 0) && (j == 0)) || ((i == mLab - 1) && (j == 0)) || ((i == mLab - 1 ) && (j == nLab - 1)) || ((i == 0) && (j == nLab - 1));
    }

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

    private boolean si3Lien(int i, int j)
    {
        return (i == 0) || (j == 0) || (i == mLab - 1 ) || (j == nLab - 1);
    }

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

    @Override
    public String toString()
    {
        return "Entree i = " + entreeLabyr[0] + ", j = " + entreeLabyr[1] +
                "\nSortie i = " + sortieLabyr[0] + ", j = " + sortieLabyr[1] +
                "\nLabyrinthe \n" + grapheLabyr;
    }

    public String toStringLaby()
    {
        String affichage = "";
        String firstLigne = "";
        String secondLigne = "";
        for (int i = 0; i < mLab; i++)
        {
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

                if (j + 1 < nLab)
                {
                    //                    grapheLabyr.getElement(deijAJgraphe(i, j + 1) , deijAJgraphe(i, j)    est
                    if (grapheLabyr.getElement(deijAJgraphe(i, j + 1), deijAJgraphe(i, j)) != 0)
                    {
                        firstLigne += " 0 ";
                    }
                    else
                    {
                        firstLigne += " # ";
                    }
                }
//                deijAJgraphe(i + 1, j)
                if (i + 1 < mLab)
                {
//                        System.out.println(i +  "      " + j);
                    //                    grapheLabyr.getElement(deijAJgraphe(i + 1, j), deijAJgraphe(i, j)     sud
                    if (grapheLabyr.getElement(deijAJgraphe(i + 1, j), deijAJgraphe(i, j)) != 0)
                    {
                        secondLigne += " 0 ";
                    }
                    else
                    {
                        secondLigne += " # ";
                    }
                }

                if ((j + 1 < nLab ))
                {

                    secondLigne += " # ";

                }

//                affichage = "\n" + firstLigne + "\n" + secondLigne + "\n";
//                System.out.println(firstLigne);
//                System.out.println(secondLigne);

            }
            affichage += "\n" + firstLigne + "\n" + secondLigne;
//            System.out.println(firstLigne);
//            System.out.println(secondLigne);
            firstLigne = "";
            secondLigne = "";

        }

        return affichage;
    }

    public String toStringLaby(Graphe graphe)
    {
        String affichage = "";
        String firstLigne = "";
        String secondLigne = "";
        for (int i = 0; i < mLab; i++)
        {
            for (int j = 0; j < nLab; j++)
            {
                if (i == entreeLabyr[0] && j == entreeLabyr[1])
                {
                    firstLigne += "E";
                }
                else if (i == sortieLabyr[0] && j == sortieLabyr[1])
                {
                    firstLigne += "S";
                }
                else
                {
                    firstLigne += "0";
                }

                if (j + 1 < nLab)
                {
                    //                    grapheLabyr.getElement(deijAJgraphe(i, j + 1) , deijAJgraphe(i, j)    est
                    if (graphe.getElement(deijAJgraphe(i, j + 1), deijAJgraphe(i, j)) != 0)
                    {
                        firstLigne += "0";
                    }
                    else
                    {
                        firstLigne += "#";
                    }
                }
//                deijAJgraphe(i + 1, j)
                if (i + 1 < mLab)
                {
//                        System.out.println(i +  "      " + j);
                    //                    grapheLabyr.getElement(deijAJgraphe(i + 1, j), deijAJgraphe(i, j)     sud
                    if (graphe.getElement(deijAJgraphe(i + 1, j), deijAJgraphe(i, j)) != 0)
                    {
                        secondLigne += "0";
                    }
                    else
                    {
                        secondLigne += "#";
                    }
                }

                if ((j + 1 < nLab ))
                {

                    secondLigne += "#";

                }

//                affichage = "\n" + firstLigne + "\n" + secondLigne + "\n";
//                System.out.println(firstLigne);
//                System.out.println(secondLigne);

            }
            affichage += "\n" + firstLigne + "\n" + secondLigne;
//            System.out.println(firstLigne);
//            System.out.println(secondLigne);
            firstLigne = "";
            secondLigne = "";

        }

        return affichage;
    }

    public int[] getPositionDansGraphe(int j)
    {
        int[] position = new int[2];

        position[0] = j / mLab;
        position[1] = j % nLab;

        return position;
    }


}
