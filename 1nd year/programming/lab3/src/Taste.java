public class Taste implements DefinitionOfTaste {
    Savor sound = Savor.TASTELESS;
    String type = "Вкус";

    @Override
    public void goodTaste() {
        this.sound = Savor.TASTY;
        System.out.println("На вкус - точь-в-точь смесь вишневого пирога, омлета, ананаса, жареной индюшки, тянучки и горячих гренков с маслом");
    }

    @Override
    public int hashCode(){
        final int power = 239;
        int hash = 0;

        for (int i = 0; i < type.length(); ++i) {
            hash = hash * power + (int)type.charAt(i);
        }

        if (sound == Savor.TASTELESS) {
            hash *= 2;
        } else if (sound == Savor.TASTY) {
            hash *= 3;
        } else if (sound == Savor.UNSAVORY) {
            hash *= 4;
        }
        return hash;
    }

    @Override
    public boolean equals(Object s) {
        if (s == null) {
            return false;
        }
        if (this == s) {
            return true;
        }
        if (!(getClass() == s.getClass())){
            return false;
        }
        else {
            Taste tmp = (Taste) s;
            return (sound == tmp.sound && tmp.type.equals(type));
        }
    }

    @Override
    public String toString(){
        return type;
    }
}
