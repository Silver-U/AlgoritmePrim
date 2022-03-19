public class main
{
    public static void main(String[] args)
    {
        Labyrinthe monLabyr = new Labyrinthe(6, 4);
        System.out.println(monLabyr);
        System.out.println("***************************");
        System.out.println("***************************");
        monLabyr.ponderationGraphe();
        System.out.println(monLabyr);
    }
}
