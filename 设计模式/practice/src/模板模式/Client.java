package 模板模式;

public class Client {
    public static void main(String[] args) {
        CaffeineBeverage c = new Coffee();
        c.prepareRecipe();
        CaffeineBeverage d = new Tea();
        d.prepareRecipe();
    }
}
