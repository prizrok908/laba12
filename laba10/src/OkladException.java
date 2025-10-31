public class OkladException extends Exception {
    private double oklad;

    public OkladException(double oklad) {
        super("Невозможно создать сотрудника – указан отрицательный оклад: " + oklad);
        this.oklad = oklad;
    }

    public double getOklad() {
        return oklad;
    }
}