public abstract class Furniture {
    protected String material;

    public Furniture(String material){
        this.material=material;
    }

    public void displayInfo() {
        System.out.println("Стол");
    }

    public abstract void assemble();
}

