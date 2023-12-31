package attacks.SpecialAttacks;

import ru.ifmo.se.pokemon.*;

public class Flamethrower extends SpecialMove{
    public Flamethrower() {
        super(Type.FIRE, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect effect = new Effect().chance(0.1).condition(Status.BURN);
        pokemon.addEffect(effect); //добавляет эффект, влияющий на покемона
        super.applyOppEffects(pokemon);
    }

    @Override
    protected String describe() {
        return "вызывает Flamethrower";
    }
}
