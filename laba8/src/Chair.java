public class Chair implements Furniture, Ds {
    @Override
    public void assemble() {
        System.out.println("Собираем стул: прикручиваем ножки к сиденью.");
    }
}