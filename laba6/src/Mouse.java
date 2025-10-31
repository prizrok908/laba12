class Mouse {
    public String name;
    private int age;
    protected String color;
    String favoriteFood;

    public Mouse() {
        this.name = "Неизвестная мышь";
        this.age = 0;
        this.color = "серый";
        this.favoriteFood = "сыр";
    }

    public Mouse(String name, int age, String color, String favoriteFood) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.favoriteFood = favoriteFood;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            System.out.println("Возраст не может быть отрицательным!");
        }
    }

    public void squeak() {
        System.out.println(name + " пищит: Пи-пи-пи!");
    }

    protected void hide() {
        System.out.println(name + " спряталась в норке.");
    }

    void eat() {
        System.out.println(name + " ест " + favoriteFood + ".");
    }

    private void sleep() {
        System.out.println(name + " спит.");
    }

    public void rest() {
        sleep();
    }

    public void info() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age + " лет");
        System.out.println("Цвет: " + color);
        System.out.println("Любимая еда: " + favoriteFood);
    }
}