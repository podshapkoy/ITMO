import ru.ifmo.se.pokemon.*;
import pokemons.*;

public class Main {
    public static void main(String[] args) {

        Battle b = new Battle();
        Pokemon p1 = new Kartana("Константин", 10);
        Pokemon p2 = new Ekans("Боба", 10);
        Pokemon p3 = new Arbok("Биба", 10);
        Pokemon p4 = new Oddish("Пупа", 10);
        Pokemon p5 = new Gloom("Лупа", 10);
        Pokemon p6 = new Bellossom("Палупа", 10);
        b.addAlly(p1);
        b.addFoe(p3);
        b.addAlly(p2);
        b.addFoe(p4);
        b.addAlly(p5);
        b.addFoe(p6);
        b.go();
    }
}