public class Main {
    public static void main(String[] args) {
        
        Furniture chair = new Chair("");
        Furniture table = new Table("");
        Table table1 = new Table("");
        //Furniture Furniture1 = new Furniture("дерево");
        table.displayInfo();
        table1.displayInfo();
        chair.assemble();
        table.assemble();
    }
}