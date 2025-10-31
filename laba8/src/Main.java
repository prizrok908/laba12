public class Main {
    public static void main(String[] args) {
        Furniture chair = new Chair();
        Furniture table = new Table();

        chair.assemble();
        table.assemble();
    }
}