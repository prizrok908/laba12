public class ShtatnySotrudnik extends Sotrudnik {
    private double premiya;

    public ShtatnySotrudnik(String fio, String dolzhnost, double oklad, double premiya) throws OkladException {
        super(fio, dolzhnost, oklad);
        this.premiya = premiya;
    }

    public double getPremiya() {
        return premiya;
    }

    public void setPremiya(double premiya) {
        this.premiya = premiya;
    }

    @Override
    public double rasschitatZarplatu() {
        try {
            if (premiya < 0) {
                throw new PremiyaException("Премия не может быть отрицательной!");
            }
            return oklad + premiya;
        } catch (PremiyaException e) {
            System.out.println("Ошибка: " + e.getMessage());
            return oklad;
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка при расчёте зарплаты: " + e.getMessage());
            return oklad;
        }
    }
}