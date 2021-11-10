class Point{
    private int coordi=-1;
    private int coordj=-1;
    private int value=-1;
    private int previusi=-1;
    private int previusj=-1;
    public Point(int coordi, int coordj) {
        this.coordi = coordi;
        this.coordj = coordj;

    }

    public Point(int coordi, int coordj, int value) {
        this.coordi = coordi;
        this.coordj = coordj;
        this.value = value;
    }

    public int getPreviusi() {
        return previusi;
    }

    public int getPreviusj() {
        return previusj;
    }

    public void setPreviusi(int previusi) {
        this.previusi = previusi;
    }

    public void setPreviusj(int previusj) {
        this.previusj = previusj;
    }

    public int getCoordi() {
        return coordi;
    }

    public int getCoordj() {
        return coordj;
    }

    public int getValue() {
        return value;
    }

    public void setCoordi(int coordi) {
        this.coordi = coordi;
    }

    public void setCoordj(int coordj) {
        this.coordj = coordj;
    }

    public void setValue(int value) {
        this.value = value;
    }
}