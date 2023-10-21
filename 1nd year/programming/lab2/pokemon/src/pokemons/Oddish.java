package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.PhysicalAttacks.Facade;
import attacks.SpecialAttacks.MegaDrain;

public class Oddish extends Pokemon {
    public Oddish(String name, int lvl) {
        super(name, lvl);
        this.setStats(45, 50, 55, 75, 65, 30);
        this.setType(Type.GRASS, Type.POISON);
        this.addMove(new Facade());
        this.addMove(new MegaDrain());
    }
}
