package attacks.PhysicalAttacks;

import ru.ifmo.se.pokemon.*;

public class PetalBlizzard extends PhysicalMove {
    public PetalBlizzard() {

        super(Type.GRASS, 90, 100);
    }

    @Override
    protected String describe() {

        return "вызывает Petal Blizzard";
    }
}
