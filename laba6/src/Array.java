class Array {
    private Element[] elements;

    public Array(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер массива должен быть положительным");
        }
        elements = new Element[size];
        for (int i = 0; i < size; i++) {
            elements[i] = new Element(0.0);
        }
    }

    public Array(double[] values) {
        elements = new Element[values.length];
        for (int i = 0; i < values.length; i++) {
            elements[i] = new Element(values[i]);
        }
    }

    public double get(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }
        return elements[index].getValue();
    }

    public void set(int index, double value) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }
        elements[index].setValue(value);
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < elements.length; i++) {
            System.out.print(elements[i]);
            if (i < elements.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public Array add(Array other) {
        if (this.elements.length != other.elements.length) {
            throw new IllegalArgumentException("Массивы должны быть одинаковой длины");
        }
        double[] result = new double[this.elements.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.get(i) + other.get(i);
        }
        return new Array(result);
    }

    public Array subtract(Array other) {
        if (this.elements.length != other.elements.length) {
            throw new IllegalArgumentException("Массивы должны быть одинаковой длины");
        }
        double[] result = new double[this.elements.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.get(i) - other.get(i);
        }
        return new Array(result);
    }

    public Array multiply(Array other) {
        if (this.elements.length != other.elements.length) {
            throw new IllegalArgumentException("Массивы должны быть одинаковой длины");
        }
        double[] result = new double[this.elements.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.get(i) * other.get(i);
        }
        return new Array(result);
    }

    public int length() {
        return elements.length;
    }
}