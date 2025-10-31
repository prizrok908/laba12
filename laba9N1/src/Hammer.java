public class Hammer extends Tool {
    int count;

    public Hammer(String name) {
        super(name);
        this.count = 0;
    }

    public Hammer(String name, int count) {
        super(name);
        this.count = count;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count=count;
    }
}