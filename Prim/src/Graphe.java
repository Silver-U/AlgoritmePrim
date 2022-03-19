import java.util.Arrays;

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
