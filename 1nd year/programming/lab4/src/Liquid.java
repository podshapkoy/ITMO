public class Liquid implements Insects{
    String name = "она";
    String type = " ";
    int force = 1;
    double x = 0;
    double y = 0;
    double z = 0;
    int skillSwear = 0;

    Liquid(double x, double y, double z) {
        String name = "Зелье";
        String type = "Жидкость";
        int force = 9;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean potionSelection(Personage p) {
        if (this.x == p.x && this.y == p.y && this.z >= p.height) {
            System.out.println(this.name + " без надписей костей, черепов, яда.");
            this.z = 6 * p.height / 7;
            return true;
        }
        else {
            System.out.println(this.name + " с надписями костей, черепов, яда.");
            this.z = 0;
            return false;
        }
    }

    @Override
    public void potionDelicious(Personage p) {
        System.out.println(this.name + " оказалось необыкновенно вкусным.");
    }
    @Override
    public void potionOver(Personage p) {
        p.force--;
        System.out.println(this.name + " закончилось.");
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
            Alice tmp = (Alice) s;
            return (tmp.name.equals(this.name)  && this.force == tmp.force && this.type.equals(tmp.type));
        }
    }

    @Override
    public int hashCode(){
        final int power = 239;
        int hash = 0;
        for (int i = 0; i < name.length(); ++i){
            hash = hash * power + (int)name.charAt(i);
        }

        for (int i = 0; i < type.length(); ++i){
            hash = hash * power + (int)type.charAt(i);
        }

        hash *= force * x * y * z;
        return hash;
    }

    @Override
    public String toString(){
        return this.type + " " + this.name;
    }
}
