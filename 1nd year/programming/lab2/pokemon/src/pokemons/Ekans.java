package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.StatusAttacks.Swagger;
import attacks.PhysicalAttacks.Bulldoze;
import attacks.SpecialAttacks.Acid;

public class Ekans extends Pokemon {
    public Ekans(String name, int lvl) {
        super(name, lvl);
        this.setStats(35, 60, 44, 40, 54, 55);
        this.setType(Type.POISON);
        this.addMove(new Swagger());
        this.addMove(new Bulldoze());
        this.addMove(new Acid());
    }
}
