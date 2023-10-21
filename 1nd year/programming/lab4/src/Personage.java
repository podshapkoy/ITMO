public abstract class Personage {
    String name = "NoName";
    String type = "Персонаж";
    double x = 0;
    double y = 0;
    int skillSwear = 10;
    int force;
    int height;
    Drawings mood = Drawings.SKULL;

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
            Personage tmp = (Personage) s;
            return (tmp.name.equals(name) && force == tmp.force && mood == tmp.mood && tmp.type.equals(type));
        }
    }

    @Override
    public int hashCode(){
        final int power = 239;
        int hash = 0;
        for (int i = 0; i < name.length(); ++i) {
            hash = hash * power + (int)name.charAt(i);
        }

        for (int i = 0; i < type.length(); ++i) {
            hash = hash * power + (int)type.charAt(i);
        }


        if (mood == Drawings.SKULL) {
            hash *= 2;
        } else if (mood == Drawings.BONES) {
            hash *= 3;
        } else if (mood == Drawings.POISON) {
            hash *= 4;
        }

        hash *= force * x * y * height;
        return hash;
    }


    /**
     * Кто-то что-то попробовал
     */
    public void feel(){
        System.out.print(name + " попробовала ");
    }
}
