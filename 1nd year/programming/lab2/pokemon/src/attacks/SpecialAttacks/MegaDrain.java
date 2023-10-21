package attacks.SpecialAttacks;

import ru.ifmo.se.pokemon.*;

public class MegaDrain extends SpecialMove {
    public MegaDrain() {
        super(Type.GRASS, 40, 100);
    }

    @Override
    protected void applySelfDamage(Pokemon pokemon, double damage) {
        pokemon.setMod(Stat.HP, -(int) (Math.round(damage)) / 2);
        System.out.println("Я исцеляюсь!");
    }

    @Override
    protected String describe() {
        return "применяет Mega Drain";
    }
}
