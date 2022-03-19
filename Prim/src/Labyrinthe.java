public class Labyrinthe
{
    private int[] entreeLabyr;
    private int[] sortieLabyr;
    private Graphe grapheLabyr;
    private int nLab;
    private int mLab;
    private int nbNoueds;

    public Labyrinthe(int m, int n)
    {
        entreeLabyr = new int[2];
        sortieLabyr = new int[2];
        nLab = n;
        mLab = m;
        grapheLabyr = new Graphe(m , n);
        nbNoueds = m * n;
    }

    private void genereLabyrinthe()
    {
        genereEntreeSortie();
    }

    private void genereEntreeSortie()
    {
        entreeLabyr[0] = nombreAleatoire(mLab);
        entreeLabyr[1] = nombreAleatoire(nLab);

        do
        {
            sortieLabyr[0] = nombreAleatoire(mLab);
            sortieLabyr[1] = nombreAleatoire(nLab);
        }
        while ((entreeLabyr[0] == sortieLabyr[0]) && (entreeLabyr[1] == sortieLabyr[1]));
    }

    public void ponderationGraphe()
    {
        for (int i = 0; i < nbNoueds; i++)
        {
            for (int j = 0; j < nbNoueds; j++)
            {
                if (siCoin(i, j))
                {
                    switch (typeCoin(i, j))
                    {
                        case basgauche -> {
                            System.out.println("basgauche");

                            //nord
                            grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
                            //east
                            grapheLabyr.liason(i , j + 1, nombreAleatoire(10));

                        }

                        case basdroit -> {
                            System.out.println("basdroit");

                            //nord
                            grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
                            //west
                            grapheLabyr.liason(i , j - 1, nombreAleatoire(10));
                        }

                        case hautguache -> {
                            System.out.println("hautguache");

                            //east
                            grapheLabyr.liason(i , j + 1, nombreAleatoire(10));
                            //sud
                            grapheLabyr.liason(i + 1, j, nombreAleatoire(10));

                        }

                        case hautdroit -> {
                            System.out.println("hautdroit");
                            //sud
                            grapheLabyr.liason(i + 1, j, nombreAleatoire(10));
                            //west
                            grapheLabyr.liason(i , j - 1, nombreAleatoire(10));

                        }
                    }
                }
                else if (si3Lien(i, j))
                {
                    switch (type3Loin(i, j))
                    {
                        case aucunBas -> {
                            //nord
                            grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
                            //west
                            grapheLabyr.liason(i , j - 1, nombreAleatoire(10));
                            //east
                            grapheLabyr.liason(i , j + 1, nombreAleatoire(10));
                        }

                        case aucunHaut -> {
                            //west
                            grapheLabyr.liason(i , j - 1, nombreAleatoire(10));
                            //east
                            grapheLabyr.liason(i , j + 1, nombreAleatoire(10));
                            //sud
                            grapheLabyr.liason(i + 1, j, nombreAleatoire(10));
                        }

                        case aucunDroit -> {
                            //nord
                            grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
                            //west
                            grapheLabyr.liason(i , j - 1, nombreAleatoire(10));
                            //sud
                            grapheLabyr.liason(i + 1, j, nombreAleatoire(10));
                        }

                        case aucunGuache -> {
                            //nord
                            grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
                            //east
                            grapheLabyr.liason(i , j + 1, nombreAleatoire(10));
                            //sud
                            grapheLabyr.liason(i + 1, j, nombreAleatoire(10));
                        }
                    }
                }
                else
                {
                    //nord
                    grapheLabyr.liason(i - 1, j, nombreAleatoire(10));
                    //west
                    grapheLabyr.liason(i , j - 1, nombreAleatoire(10));
                    //east
                    grapheLabyr.liason(i , j + 1, nombreAleatoire(10));
                    //sud
                    grapheLabyr.liason(i + 1, j, nombreAleatoire(10));
                }
            }
        }
    }

    private boolean siCoin(int i, int j)
    {
        return ((i == 0) && (j == 0)) || ((i == nbNoueds - 1) && (j == 0)) || ((i == nbNoueds - 1 ) && (j == nbNoueds - 1)) || ((i == 0) && (j == nbNoueds - 1));
    }

    private TypeDeCoin typeCoin(int i, int j)
    {
        if ((i == 0) && (j == 0))
            return TypeDeCoin.hautguache;
        else if ((i == nbNoueds - 1) && (j == 0))
            return TypeDeCoin.basgauche;
        else if ((i == nbNoueds - 1 ) && (j == nbNoueds - 1))
            return TypeDeCoin.basdroit;
        else
            return TypeDeCoin.hautdroit;
    }

    private boolean si3Lien(int i, int j)
    {
        return (i == 0) || (j == 0) || (i == nbNoueds - 1 ) || (j == nbNoueds - 1);
    }

    private Type3Lien type3Loin(int i, int j)
    {
        if (i == 0)
            return Type3Lien.aucunHaut;
        else if (j == 0)
            return Type3Lien.aucunGuache;
        else if (i == nbNoueds - 1 )
            return Type3Lien.aucunBas;
        else
            return Type3Lien.aucunDroit;
    }

    private int nombreAleatoire(int a)
    {
        return (int) (Math.random() * a);
    }

    @Override
    public String toString()
    {
        return "Labyrinthe{" +
                "grapheLabyr=" + grapheLabyr +
                '}';
    }

    public int[] getPositionDansGraphe(int j)
    {
        int[] position = new int[2];

        position[0] = j / mLab;
        position[1] = j % nLab;

        return position;
    }
}
