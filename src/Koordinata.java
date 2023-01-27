public class Koordinata {
    public int x;
    public int y;

    public Koordinata(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public Koordinata levo(){
        return new Koordinata(y,x-1);
    }

    public Koordinata desno(){
        return new Koordinata(y, x+1);
    }

    public Koordinata gore(){
        return new Koordinata(y-1, x);
    }

    public Koordinata dole(){
        return new Koordinata(y+1, x);
    }

    public boolean jednaka(Koordinata k){
        return (this.x == k.x && this.y == k.y);
    }

    @Override
    public String toString() {
        return "[" + this.y + "," + this.x + "]";
    }
}
