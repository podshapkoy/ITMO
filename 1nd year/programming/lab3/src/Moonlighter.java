public class Moonlighter extends Personage {
    String type = "Алиса";

    Moonlighter(String name) {
        this.name = name;
        this.type = "Алиса";
    }

    Moonlighter(String name, double x, double y, int height) {
        this.name = name;
        this.type = "Алиса";
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public void overLook() {
        System.out.println(this.name + " не заметила, как");
    }


}
