public interface Insects {
    int skillSwear = 110;
    /**
     *    Метод, который определяет выбор зелья
     *    @Personage p - персонаж
     *    @return true - в случае успеха, false - иначе
     */
    public boolean potionSelection(Personage p);
    /**
     *    Метод, в котором зелье оказывается вкусным
     */
    public void potionDelicious(Personage p);

    /**
     *    Метод, в котором зелье заканчивается
     */
    void potionOver(Personage p);
}
