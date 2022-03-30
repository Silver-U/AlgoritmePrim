public class main
{
    public static void main(String[] args)
    {
        Labyrinthe monLabyr;

//        Labyrinthe monLabyr  = new Labyrinthe(3, 3);
//        int[] tab = new int[2];
//        tab[0] = 0;
//        tab[1] = 0;
//        monLabyr.setEntreeLabyr(tab);
//        int[] tab1 = new int[2];
//        tab1[0] = 0;
//        tab1[1] = 2;
//        monLabyr.setSortieLabyr(tab1);
//        int[][] graphe = {
//                {0,  3 , 0,  2,  0 , 0,  0 , 0,  0},
//                {3,  0,  9 , 0 , 7 , 0,  0 , 0,  0},
//                {0 , 9 , 0 , 0 , 0,  5 , 0  ,0 , 0},
//                {2 , 0,  0 , 0 , 7  ,0,  1 , 0 , 0},
//                {0,  7 , 0,  7 , 0,  4,  0 , 6 , 0},
//                {0 , 0,  5,  0 , 4 , 0 , 0 , 0 , 8},
//                {0  ,0 , 0 , 1 , 0 , 0 , 0 , 1 , 0},
//                {0 , 0 , 0,  0 , 6 , 0 , 1 , 0 , 9},
//                {0,  0  ,0  ,0  ,0,  8 , 0 , 9 , 0}
//        };
//
//        Graphe g = new Graphe(3, 3);
//        g.setMatriceAdjacente(graphe);
//        monLabyr.setGrapheLabyr(g);
//
//                System.out.println("***************************");
//
//        System.out.println(monLabyr.toString());
//            monLabyr.algorithmPrim();
//            System.out.println(monLabyr.toStringLaby());
//            System.out.println("***************************");
//
//        System.out.println();
//
//
//        tab = new int[2];
//        tab[0] = 0;
//        tab[1] = 2;
//        monLabyr.setEntreeLabyr(tab);
//        tab1 = new int[2];
//        tab1[0] = 0;
//        tab1[1] = 1;
//        monLabyr.setSortieLabyr(tab1);
//        graphe = new int[][]{
//                {0 , 7 , 0 , 8 , 0  ,0 , 0  ,0 , 0},
//                {7,  0 , 8 , 0 , 1  ,0 , 0 , 0,  0},
//                {0,  8 , 0 , 0 , 0 , 3 , 0 , 0,  0},
//                {8,  0 , 0 , 0 , 6,  0 , 6 , 0 , 0},
//                {0,  1 , 0 , 6 , 0,  7 , 0,  6 , 0},
//                {0,  0 , 3,  0 , 7 , 0 , 0,  4,  0},
//                {0 , 0  ,0 , 6 , 0 , 0  ,0  ,4  ,0},
//                {0 , 0  ,0 , 0 , 6 , 0,  4,  0 , 2},
//                {0 , 0  ,0 , 0  ,0 , 7 , 0 , 2 , 0}
//        };
//
//        g = new Graphe(3, 3);
//        g.setMatriceAdjacente(graphe);
//        monLabyr.setGrapheLabyr(g);
//
//        System.out.println("***************************");
//
//        System.out.println(monLabyr.toString());
//        monLabyr.algorithmPrim();
//        System.out.println(monLabyr.toStringLaby());
//        System.out.println("***************************");
//        monLabyr.ponderationGraphe();
//        System.out.println(monLabyr);
//        System.out.println("***************************");
//        System.out.println("***************************");
////        monLabyr.algorithmPrim();
////        System.out.println(monLabyr);
////        System.out.println(monLabyr.toStringLaby());;
//        System.out.println("***************************");
//        System.out.println("***************************");
//        System.out.println(monLabyr.getCopieGr());


        int m = 3, n = 3;
        for (int i = 0; i < 50; i++)
        {

//            System.out.println("***************************");
            monLabyr = new Labyrinthe(m, n);
            monLabyr.ponderationGraphe();
            System.out.println(monLabyr.toString());
            monLabyr.algorithmPrim();
            System.out.println(monLabyr.toStringLaby());

//            if ((monLabyr.getNombreO() != (m * n) - 2) && (monLabyr.getNouedVisiter() != (m * n) - 1))
//            {
////                System.out.println(monLabyr.toString());
//                System.out.println(monLabyr.toStringLaby());
//            }

            System.out.println("***************************");
//            System.out.println();

        }


    }
}
