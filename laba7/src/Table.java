public class Table extends Furniture {

    public Table(String material){
        super(material);
    }

    @Override
    public void assemble() {
        System.out.println("Собираем стол: устанавливаем ножки и крепим столешницу.");
    }

    public void printInfo(){
        displayInfo();
        System.out.println("стол");
    }

}