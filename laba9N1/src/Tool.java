public class Tool {
    public String name;
    public Tool(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name;
    }

}