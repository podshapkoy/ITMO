package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.PhysicalAttacks.Facade;
import attacks.SpecialAttacks.MegaDrain;
import attacks.StatusAttacks.PoisonPowder;

public class Gloom extends Pokemon {
    public Gloom(String name, int lvl) {
        super(name, lvl);
        this.setStats(60, 65, 70, 85, 75, 40);
        this.setType(Type.GRASS, Type.POISON);
        this.addMove(new Facade());
        this.addMove(new MegaDrain());
        this.addMove(new PoisonPowder());
    }
}
