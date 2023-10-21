package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.PhysicalAttacks.Facade;
import attacks.SpecialAttacks.MegaDrain;
import attacks.StatusAttacks.PoisonPowder;
import attacks.PhysicalAttacks.PetalBlizzard;

public class Bellossom extends Pokemon {
    public Bellossom(String name, int lvl) {
        super(name, lvl);
        this.setStats(75, 80, 95, 90, 100, 50);
        this.setType(Type.GRASS);
        this.addMove(new Facade());
        this.addMove(new MegaDrain());
        this.addMove(new PoisonPowder());
        this.addMove(new PetalBlizzard());
    }
}
