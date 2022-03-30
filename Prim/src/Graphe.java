public class Graphe
{
    private int[][] matriceAdjacente;
    private int m;
    private int n;

    public Graphe(int m, int n)
    {
        this.m = m;
        this.n = n;
        matriceAdjacente = new int[m * n][m * n];
    }

    public Graphe(Graphe graphe)
    {
        m = graphe.getM();
        n = graphe.getN();
        matriceAdjacente = new int[m * n][m * n];
        for (int i = 0; i < m * n; i++)
        {
            for (int j = 0; j < m * n; j++)
            {
                matriceAdjacente[i][j] = graphe.getElement(i, j);
            }
        }
    }

    public void setMatriceAdjacente(int[][] matriceAdjacente)
    {
        this.matriceAdjacente = matriceAdjacente;
    }

    public Graphe getG()
    {
        return this;
    }

    public int getM()
    {
        return m;
    }

    public int getN()
    {
        return n;
    }

    public int getElement(int i, int j)
    {
        return matriceAdjacente[i][j];
    }

    public int[] getRows(int i)
    {
        return matriceAdjacente[i];
    }

    public void liason(int i, int j, int poids)
    {
        matriceAdjacente[i][j] = poids;
    }

    @Override
    public String toString()
    {
        String message ="\nNombre de ligne(s) = " + m * n +
                "\nNombre de Colonnes(s) = " + m * n +
                "\nmatrice = ";

        String tableauMessage = "";

        for (int i = 0; i < m * n; i++)
        {
            message += "\n|";
            for (int j = 0; j < m * n; j++)
            {
                message += " " + matriceAdjacente[i][j] + " ";
            }

            message += "|";
        }

        return message;
    }
}
