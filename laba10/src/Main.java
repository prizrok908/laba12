public class Main {
    public static void main(String[] args) {
        System.out.println("=== Проверка OkladException ===");
        try {
            Sotrudnik s1 = new Sotrudnik("Колотов Д.П.", "Программист", -50000);
        } catch (OkladException e) {
            System.out.println(e.getMessage());
            try {
                throw e;
            } catch (OkladException ex) {
                System.out.println("Повторный перехват: " + ex.getMessage());
            }
        }

        Sotrudnik s2 = null;
        try {
            s2 = new Sotrudnik("Лепешов М.Э.", "Аналитик", 70000);
        } catch (OkladException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== Проверка PremiyaException ===");
        ShtatnySotrudnik shtatny = null;
        try {
            shtatny = new ShtatnySotrudnik("Жигелев А.Д.", "Руководитель", 100000, -20000);
        } catch (OkladException e) {
            System.out.println(e.getMessage());
        }

        if (shtatny != null) {
            double zarplata = shtatny.rasschitatZarplatu();
            System.out.println("Рассчитанная зарплата: " + zarplata);
        }

        System.out.println("\n=== Сотрудник по контракту ===");
        SotrudnikPoKontraktu kontrakt = null;
        try {
            kontrakt = new SotrudnikPoKontraktu("Жешко В.П.", "Консультант", 80000);
        } catch (OkladException e) {
            System.out.println(e.getMessage());
        }

        if (kontrakt != null) {
            System.out.println("Зарплата по контракту: " + kontrakt.rasschitatZarplatu());
        }
        
        System.out.println("\n=== Фирма и Отдел ===");
        Firma firma = new Firma();
        firma.setNazvanie("ООО 'КБиП'");

        Otdel otdel = new Otdel();
        otdel.setNazvanie("IT-отдел");
        otdel.setKolichestvoSotrudnikov(5);

        System.out.println("Фирма: " + firma.getNazvanie());
        System.out.println("Отдел: " + otdel.getNazvanie() + ", сотрудников: " + otdel.getKolichestvoSotrudnikov());
    }
}