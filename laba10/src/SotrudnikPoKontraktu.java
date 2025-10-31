public class SotrudnikPoKontraktu extends Sotrudnik {
    public SotrudnikPoKontraktu(String fio, String dolzhnost, double oklad) throws OkladException {
        super(fio, dolzhnost, oklad);
    }

    @Override
    public double rasschitatZarplatu() {
        try {
            return oklad * 1.2;
        } catch (Exception e) {
            System.out.println("Ошибка при расчёте зарплаты по контракту: " + e.getMessage());
            return oklad;
        }
    }
}