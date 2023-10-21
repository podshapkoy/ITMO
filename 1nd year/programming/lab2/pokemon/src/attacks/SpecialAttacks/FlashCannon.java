package attacks.SpecialAttacks;

import ru.ifmo.se.pokemon.*;

public class FlashCannon extends SpecialMove {
    public FlashCannon() {
        super(Type.STEEL, 80, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect effect = new Effect().chance(0.1).stat(Stat.SPECIAL_DEFENSE, -1);
        pokemon.addEffect(effect); //добавляет эффект, влияющий на покемона
        super.applyOppEffects(pokemon);
    }

    @Override
    protected String describe() {

        return "вызывает FlashCannon";
    }

}
