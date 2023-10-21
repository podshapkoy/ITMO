public class Main {
    public static void main(String[] args) {
        System.out.println("\"Алиса в стране чудес\"");

        Moonlighter alice = new Moonlighter("Алиса", 100, 100, 200);
        Liquid flea = new Liquid(100, 100, 300);

        alice.feel();
        flea.potionSelection(alice);
        flea.potionDelicious(alice);

        Taste thisTaste = new Taste();
        thisTaste.goodTaste();
        alice.overLook();
        flea.potionOver(alice);
    }
}
