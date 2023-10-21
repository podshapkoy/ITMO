package attacks.StatusAttacks;

import ru.ifmo.se.pokemon.*;

public class PoisonPowder extends StatusMove {
    public PoisonPowder() {
        super(Type.POISON, 0, 75);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        super.applyOppEffects(pokemon);
        pokemon.setCondition(new Effect().condition(Status.POISON));
    }

    @Override
    protected String describe() {

        return "применяет Poison Powder";
    }

}
