import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessangeToInsects {
    public String tone;
    public String msg;
    public Insects toWhom;
    public Personage fromWhom;
    class PotionException extends Exception{
        PotionException(String msg){
            super(msg);
        }
    }

    /**
     * Метод, который создает сообщение
     */
    MessangeToInsects(String msg, String tone, Insects toWhom, Personage fromWhom){
        this.msg = msg;
        this.toWhom = toWhom;
        this.fromWhom = fromWhom;
        this.tone = tone;
    }
    /**
     * Метод в котором Алиса говорит об эффекте зелья
     * Причем, если skillSwear у зелья больше, чем у персонажа, то монолог идет дальше.
     */
    void sayMessange() throws IOException {
        System.out.println("\"" + msg + "\"" + " - " + tone + " " + fromWhom.name + ".");

        if (toWhom.skillSwear >= fromWhom.skillSwear){

            class Answer {
                private ArrayList<String> tone = new ArrayList<>();
                private ArrayList<String> ans = new ArrayList<>();

                Answer() throws IOException {
                    FileReader answer = new FileReader("src\\materials\\answer.txt");
                    FileReader toneFile = new FileReader("src\\materials\\tone.txt");
                    Scanner scanAns = new Scanner(answer);
                    Scanner scanTone = new Scanner(toneFile);
                    while(scanAns.hasNextLine()) {
                        ans.add(scanAns.nextLine());
                    }
                    while (scanTone.hasNextLine()) {
                        tone.add(scanTone.nextLine());
                    }

                    answer.close();
                    toneFile.close();
                }

                private void sayAnswer() throws PotionException {
                    if (ans.size() == 0 || tone.size() == 0){
                        throw new PotionException(toWhom + " не понимала, что делать дальше.");
                    }
                    System.out.println("\"" + ans.get((int)(Math.random()*100 % ans.size())) + "\"" + " - " + tone.get((int)(Math.random()*100 % tone.size())) + " " + toWhom + ".");
                }
            }

            Answer answer = new Answer();

            try {
                answer.sayAnswer();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(toWhom + " заплакала и промолчала, потому что обратилась к недопустимому индексу массива.");
            } catch (PotionException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Никто ничего не ответил, потому что, на самом деле, никакого зелья не существует. Как и Алисы.");
            } catch (Exception e){
                System.out.println(toWhom + " просто испарилась. Никто не знает почему.");
            }
        }
    }
}