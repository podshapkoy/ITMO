package attacks.PhysicalAttacks;

import ru.ifmo.se.pokemon.*;

public class DoubleEdge extends PhysicalMove {
    public DoubleEdge() {
        super(Type.NORMAL, 120, 100);
    }

    @Override
    protected void applySelfDamage(Pokemon pokemon, double damage) {
        pokemon.setMod(Stat.HP, -(int) (Math.round(damage)) / 3);
        System.out.println("Я исцеляюсь!");
    }

    @Override
    protected String describe() {
        return "применяет Double Edge";
    }

}
