package es.carlosgs.introjee.sesiones;

public class Punto {
    private int x;
    private int y;

    public Punto() {
    }

    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX (int x) {
        this.x = x;
    }

    public int getX () {
        return this.x;
    }

    public void setY (int y) {
        this.y = y;
    }

    public int getY () {
        return this.y;
    }
}
