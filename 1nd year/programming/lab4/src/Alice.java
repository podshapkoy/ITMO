public class Alice extends Personage {
    String type = "Алиса";

    /**
     * Проверка исключений в компиляторе
     */
    class unchException extends RuntimeException{
        unchException(String msg){
            super(msg);
        }
    }

    Alice(String name, double x, double y, int height) {
        this.name = name;
        this.type = "Алиса";
        this.x = x;
        this.y = y;
        this.force = 7;
        this.height = height;
    }

    public void first() {
        System.out.println(this.name + " была умная девочка и не спешила откликнуться на любезное приглашение.");
    }

    public void second() {
        System.out.println(this.name + " недаром перечитала множество поучительных рассказов про детей, с которыми случались разные неприятности. А ведь, кажется, так просто запомнить, что, например, раскаленной докрасна кочергой можно обжечься, если будешь держать ее в руках слишком долго; что если ОЧЕНЬ глубоко порезать палец ножом, из этого пальца, как правило, пойдет кровь, и так далее и тому подобное. ");
    }
    public void third() {
        System.out.println(this.name + "-то отлично помнила, что если выпьешь слишком много из бутылки, на которой нарисованы череп и кости и написано \"Яд!\", то почти наверняка тебе не поздоровится (то есть состояние твоего здоровья может ухудшиться). ");
    }
    public void fourth() {
        System.out.println(this.name + "Подождав немного и убедившись, что все остается по-прежнему, Алиса побежала было в сад; но - такая незадача! - у самого выхода она вспомнила, что оставила золотой ключик на столе, а подбежав опять к столику, обнаружила, что теперь ей никак до ключа не дотянуться.");
    }
    public boolean tease (Liquid p){
        if (p == null){
            throw new unchException("error");
        }
        try {
            if (p.skillSwear < this.skillSwear) {
                battle(p);
            }
            MessangeToInsects msg = new MessangeToInsects("Ой, что же это со мной делается!", "сказала", p, this);
            msg.sayMessange();
            if (p.force >= this.force){
                this.feel();
                p.potionSelection(this);
                p.potionDelicious(this);

                MessangeToInsects msg3 = new MessangeToInsects("Я начинаю взлеать!", "выкрикнула", p, this);

                System.out.println("Она взлетала все выше и выше. И уже начала подумывать о том, что вот-вот может улететь в космос.");
                msg3.sayMessange();
            }
            else {
                MessangeToInsects msg3 = new MessangeToInsects("Я, наверное, и правда складываюсь, как подзорная труба!", "воскликнула", p, this);
                System.out.println("Спорить с этим было трудно: к этому времени в ней осталось всего лишь четверть метра. Алиса так и сияла от радости, уверенная, что она теперь свободно может выйти в чудесный сад. Но все-таки она решила на всякий случай немного подождать и убедиться, что она уже перестала уменьшаться в росте.");
                msg3.sayMessange();
            }



        } catch (unchException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(this.name + " немой.");
        }
        return false;
    }

    /**
     * Алиса и Зелья
     * @param p - Зелье
     * @return true - Алиса выпила, false - иначе
     */
    public boolean battle(Liquid p){
        if (p.force >= this.force){
            this.feel();
            p.potionSelection(this);
            p.potionDelicious(this);

            System.out.println(this.name + " Однако на этой бутылочке был череп, кости и надпись \"Яд!\", и Алиса рискнула попробовать ее содержимое.");
            if (this.mood != Drawings.BONES){
                changeMood(Drawings.BONES);
            }
            return true;
        }
        else {
            System.out.println(" Однако на этой бутылочке не было ни черепа, ни костей, ни надписи \"Яд!\", и Алиса рискнула попробовать ее содержимое.");
            if (this.mood != Drawings.POISON){
                changeMood(Drawings.POISON);
            }
            return false;
        }
    }
    public void changeMood(Drawings newMood){
        switch (newMood){
            case SKULL: {
                System.out.println(this.name + "громко икнула.");
                break;
            }
            case BONES: {
                System.out.println(this.name + "начала себя странно чувствовать.");
                break;
            }
            case POISON: {
                System.out.println("А так как оно оказалось необыкновенно вкусным (на вкус - точь-в-точь смесь вишневого пирога, омлета, ананаса, жареной индюшки, тянучки и горячих гренков с маслом.");
                break;
            }
            default: {
                System.out.println(this.name + " выпила зелье без эффекта");
            }
        }
    }


}
