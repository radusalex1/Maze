import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class MyPanel extends JPanel {
    private static final int initialx = 20;
    private Vector<Point> points = new Vector<>();

    private Point[][] mat = new Point[100][100];


    private int noRows = 0;
    private int noColls = 0;
    private int latura = 50;
    private Vector<Square> listaSquares;
    private int pointx = initialx;
    private int pointy = 20;


    public MyPanel() throws FileNotFoundException {

        Vector<Point> Intrari = new Vector<>();
        Vector<Point> Iesiri = new Vector<>();

        GetMatrix(Intrari, Iesiri);
        pointx = 20;
        pointy = 20;
        listaSquares = new Vector<>();
        for (int i = 0; i < noRows; i++) {
            for (int j = 0; j < noColls; j++) {
                Square s = new Square(pointx, pointy, latura, mat[i][j].getValue(), mat[i][j].getCoordi(), mat[i][j].getCoordj());
                listaSquares.add(s);
                pointx += latura;
            }
            pointx = initialx;
            pointy += latura;
        }
        repaint();
        BFS(Intrari, Iesiri);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Square s : listaSquares) {
            s.DrawSquare(g);
        }

    }

    private void GetMatrix(Vector<Point> Starters, Vector<Point> Ends) throws FileNotFoundException {
        Scanner infile = null;
        infile = new Scanner(new File("matrix.txt"));

        while (infile.hasNextLine()) {
            noRows = infile.nextInt();
            noColls = infile.nextInt();

            for (int i = 0; i < noRows; i++) {
                for (int j = 0; j < noColls; j++) {
                    if (infile.hasNextLine()) {
                        int value = infile.nextInt();
                        Point p = new Point(i, j, value);
                        mat[i][j] = p;
                        if (value == 2) {
                            Point end = p;
                            Ends.add(end);
                        }
                        if (value == 3) {
                            Point start = p;
                            Starters.add(start);
                        }
                    }
                }
            }
        }
        DisplayMatrix();
    }

    private void DisplayMatrix() {
        for (int i = 0; i < noRows; i++) {
            for (int j = 0; j < noColls; j++) {
                System.out.print(mat[i][j].getValue() + " ");
            }
            System.out.println();
        }
    }

    private boolean isValid(Point cell, boolean[][] vis) {
        int i = cell.getCoordi();
        int j = cell.getCoordj();
        if (i < 0 || j < 0 || i >= noRows || j >= noColls) {
            return false;
        }

        if (vis[i][j]) {
            return false;
        }
        if (mat[i][j].getValue() == 0) {
            return false;
        }
        return true;
    }

    private void BFS(Vector<Point> Intrari, Vector<Point> Iesiri) {
        int dx[] = {-1, 0, 1, 0};
        int dy[] = {0, 1, 0, -1};

        for (Point iesire : Iesiri) {
            ///trebuie sa ajung la intrari
            for (Point intrare : Intrari) {
                Point start = iesire;
                Point finish = intrare;

                boolean[][] vis = new boolean[noRows][noColls];

                Queue<Point> q = new LinkedList<>();

                q.add(new Point(start.getCoordi(), start.getCoordj()));

                vis[start.getCoordi()][start.getCoordj()] = true;
                boolean ok=false;
                while (!q.isEmpty() && ok==false) {
                    Point Previouscell = q.peek();
                    q.remove();

                    for (int i = 0; i < 4 && ok==false; i++) {

                        Point Nextcell = new Point(0, 0);
                        Nextcell.setCoordi(Previouscell.getCoordi() + dx[i]);
                        Nextcell.setCoordj(Previouscell.getCoordj() + dy[i]);

                        if (Nextcell.getCoordi() == finish.getCoordi() &&
                                Nextcell.getCoordj() == finish.getCoordj()) {
                            /*finish.setPreviusi(Previouscell.getCoordi());
                            finish.setPreviusj(Previouscell.getCoordj());

                            UpdateVectorPoints(Previouscell,finish);
                            trebuie sa refac drumul de la intrare catre iesire
                            caut previous==coord
                            Vector<Point>path= new Vector<>
                           for (Point p : points) {
                                if (p.getPreviusi() != -1 && p.getPreviusj() != -1) {
                                    System.out.print(p.getCoordi() + " " + p.getCoordj() + " , ");
                                }
                            }

                            System.out.println();
                            for (Point p : points) {
                                p.setPreviusi(-1);
                                p.setPreviusj(-1);
                            }*/
                            mat[finish.getCoordi()][finish.getCoordj()].setPreviusi(Previouscell.getCoordi());
                            mat[finish.getCoordi()][finish.getCoordj()].setPreviusj(Previouscell.getCoordj());
                        /*for(int row=0;row<noRows;row++)
                         {
                             for(int col=0;col<noColls;col++)
                             {
                                 if(mat[row][col].getPreviusi()!=-1)
                                 {
                                     System.out.print(mat[row][col].getCoordi()+" "+mat[row][col].getCoordj()+", ");
                                 }

                             }
                         }
                         System.out.println();
                            for(int row=0;row<noRows;row++)
                            {
                                for(int col=0;col<noColls;col++)
                                {
                                    vis[row][col]=false;
                                    mat[row][col].setPreviusi(-1);
                                    mat[row][col].setPreviusj(-1);
                                }
                            }*/

                            //trebuie sa refac drumul
                            Vector<Point> path = new Vector<>();
                            path.add(finish);
                            int x=finish.getPreviusi();
                            int y= finish.getPreviusj();
                            while(x!=-1 && y!=-1)
                            {
                                path.add(mat[x][y]);
                                int x1=x;
                                x=mat[x1][y].getPreviusi();
                                if(x==-1 || y==-1)
                                {
                                    break;
                                }
                                y=mat[x1][y].getPreviusj();
                            }
                            for(Point p:path)
                            {
                                for(Square s:listaSquares)
                                {
                                    if(p.getCoordi()==s.getI()&& p.getCoordj()==s.getJ())
                                    {
                                        s.setPartOfPath(true);
                                    }
                                }
                                System.out.print(p.getCoordi()+" "+p.getCoordj()+" , ");
                            }
                            System.out.println();
                           ok=true;

                        }
                        if (isValid(Nextcell, vis) && ok==false) {
                            //Nextcell.setPreviusi(Previouscell.getCoordi());
                            //Nextcell.setPreviusj(Previouscell.getCoordj());
                            q.add(Nextcell);

                            mat[Nextcell.getCoordi()][Nextcell.getCoordj()].setPreviusi(Previouscell.getCoordi());
                            mat[Nextcell.getCoordi()][Nextcell.getCoordj()].setPreviusj(Previouscell.getCoordj());
                            vis[Nextcell.getCoordi()][Nextcell.getCoordj()] = true;
                        }
                    }
                }
                repaint();
               /* for(Square s:listaSquares)
                {
                    s.setPartOfPath(false);
                }*/
            }
        }
    }
}

