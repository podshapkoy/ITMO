package attacks.PhysicalAttacks;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        super.applyOppEffects(pokemon);
        if (pokemon.getCondition() == Status.BURN || pokemon.getCondition() == Status.POISON||pokemon.getCondition()==Status.PARALYZE){
            Effect effect = new Effect().stat(Stat.ATTACK, 2);
            pokemon.addEffect(effect);
        }
    }
    @Override
    protected String describe() {

        return "вызывает Facade";
    }

}
