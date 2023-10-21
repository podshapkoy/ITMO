public class Main {
    public static void main(String[] args) {
        System.out.println("\"Алиса в стране чудес\"");

        Alice alice = new Alice("Алиса", 100, 100, 200);
        Liquid flea = new Liquid(100, 100, 300);

        alice.first();
        alice.second();
        alice.third();

        alice.tease(flea);
        alice.fourth();


    }
}
