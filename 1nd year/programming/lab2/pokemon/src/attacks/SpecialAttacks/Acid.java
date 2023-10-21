package attacks.SpecialAttacks;

import ru.ifmo.se.pokemon.*;

public class Acid extends SpecialMove {
    public Acid() {
        super(Type.POISON, 40, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect effect = new Effect().chance(0.1).stat(Stat.SPECIAL_DEFENSE, -1);
        pokemon.addEffect(effect); //добавляет эффект, влияющий на покемона
        super.applyOppEffects(pokemon);
    }

    @Override
    protected String describe() {

        return "вызывает Acid";
    }
}
