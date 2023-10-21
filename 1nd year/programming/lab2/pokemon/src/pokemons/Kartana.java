package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.StatusAttacks.Swagger;
import attacks.PhysicalAttacks.DoubleEdge;
import attacks.SpecialAttacks.Flamethrower;
import attacks.SpecialAttacks.FlashCannon;

public class Kartana extends Pokemon {
    public Kartana(String name, int lvl) {
        super(name, lvl);
        this.setStats(59, 181, 131, 59, 31, 109);
        this.setType(Type.GRASS, Type.STEEL);
        this.addMove(new Swagger());
        this.addMove(new DoubleEdge());
        this.addMove(new Flamethrower());
        this.addMove(new FlashCannon());
    }

}
