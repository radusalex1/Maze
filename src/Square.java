import java.awt.*;

public class Square {
    private int x;
    private int y;
    private int lat;
    private int type;
    private int i,j;
    private boolean partOfPath=false;
    public Square(int x, int y, int lat, int type, int i, int j) {
        this.x = x;
        this.y = y;
        this.lat=lat;
        this.type=type;
        this.i=i;
        this.j=j;
    }

    public void setPartOfPath(boolean partOfPath) {
        this.partOfPath = partOfPath;
    }

    public boolean isPartOfPath() {
        return partOfPath;
    }

    public void setI(int i) {
        this.i = i;
    }
    public void setJ(int j) {
        this.j = j;
    }
    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }
    public int getMidx()
    {
        return (x+lat)/2;
    }
    public int getMidy()
    {
        return (y+lat)/2;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setLat(int lat) {
        this.lat = lat;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getLat() {
        return lat;
    }

    public void DrawSquare(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(x,y,lat,lat);
        if(type==0)///wall
        {
            g.setColor(Color.BLACK);
        }
        if(type==1)///path
        {
            if(partOfPath==true)
            {
                g.setColor(Color.GREEN);
            }
            else
            {
                g.setColor(Color.lightGray);
            }
        }
        if(type==2)///exit
        {
            g.setColor(Color.RED);
        }
        if(type==3)///start
        {
            g.setColor(Color.cyan);
        }
        g.fillRect(x+1,y+1,lat,lat);
    }

}
