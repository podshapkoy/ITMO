package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.StatusAttacks.Swagger;
import attacks.PhysicalAttacks.Bulldoze;
import attacks.SpecialAttacks.Acid;
import attacks.PhysicalAttacks.Crunch;

public class Arbok extends Pokemon {
    public Arbok(String name, int lvl) {
        super(name, lvl);
        this.setStats(60, 95, 69, 65, 79, 80);
        this.setType(Type.POISON);
        this.addMove(new Swagger());
        this.addMove(new Bulldoze());
        this.addMove(new Acid());
        this.addMove(new Crunch());
    }
}
