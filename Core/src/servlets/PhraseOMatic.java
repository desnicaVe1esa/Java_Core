package servlets;

public class PhraseOMatic {

    public static String makePhrase() {

        String[] wordListOne = {"Братишка", "Проголодался наверное", "Я тебе покушать принес"};

        String[] wordListTwo = {"Начальник", "Этот мудак обосрался", "Уберите его отсюда"};

        String[] wordListThree = {"Чисти говно", "Блять", "Как я вилкой-то чистить буду", "Чисть, чисть, чисть, чисть"};

        int one = wordListOne.length;
        int two = wordListTwo.length;
        int three = wordListThree.length;

        int randOne = (int) (Math.random() * one);
        int randTwo = (int) (Math.random() * two);
        int randThree = (int) (Math.random() * three);

        String phrase = wordListOne[randOne] + " " + wordListTwo[randTwo] + " " + wordListThree[randThree];

        return (phrase);
    }

    public static void main(String[] args) {
        System.out.println(makePhrase());
    }
}
