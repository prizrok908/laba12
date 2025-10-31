public class Chair extends Furniture {

    public Chair(String material){
        super(material);
    }

    @Override
    public void assemble() {
        System.out.println("Собираем стул: прикручиваем ножки к сиденью.");
    }

}