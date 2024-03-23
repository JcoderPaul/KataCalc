import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KataCalc {

    public static void main(String[] args) {

        do {
            String mathematicalExpression = userInterface();
            System.out.println("Ответ: \n" + calc(mathematicalExpression));
            repeatCalculation();
        } while (repeatCalc);

    }
    private static boolean arabicNumber = false;
    private static boolean repeatCalc = false;

    public static String calc(String input){

       Integer numberAnswer = null;
       String stringAnswer = "";

       String[] expressionElement = stringParserAndOperatorDefinition(input);
       Integer[] operandForCalc = numberDefinition(expressionElement);

        switch (expressionElement[1]) {
            case "+": numberAnswer = plus(operandForCalc[0], operandForCalc[1]);
                      break;
            case "-": numberAnswer = minus(operandForCalc[0], operandForCalc[1]);
                      break;
            case "/": numberAnswer = divide(operandForCalc[0], operandForCalc[1]);
                      break;
            case "*": numberAnswer = multiply(operandForCalc[0], operandForCalc[1]);
                      break;
            default:
                break;
        }

        if (arabicNumber) {
            stringAnswer = Integer.toString(numberAnswer);
        }
        else if (numberAnswer >= 0) {
            stringAnswer = arabicToRoman.get(numberAnswer);
        }
        else throw new OurUniversalException("throws Exception");

        return stringAnswer;
    }

    private static Integer plus(int a, int b){
        return a + b;
    }
    private static Integer minus(int a, int b){
        return a - b;
    }
    private static Integer divide(int a, int b){
        return a / b;
    }
    private static Integer multiply(int a, int b){
        return a * b;
    }

    private static String userInterface() {
        String greeting = "*** Добро пожаловать в Kata Calculator ***\n\n" +
                          "Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления\n" +
                          "с двумя числами: a + b, a - b, a * b, a / b. Вы можете использовать арабские\n" +
                          "(1,2,3,4,5...), а также римские (I,II,III,IV,V...) числа. Значение каждого\n" +
                          "вводимого чисел не может превышать 10. Арабские и римские числа не сочетаются\n" +
                          "в одном выражении!\n" +
                          "\nВнимание! Операцию от операндов отделяйте пробелом, пример: 1 + 2 или V + IX";

        String invite = "\nВведите математическое выражение: ";

        System.out.println(greeting);
        Scanner scanner = new Scanner(System.in);
        System.out.println(invite);
        String inData = scanner.nextLine().trim();

        return inData;
    }

    private static String[] stringParserAndOperatorDefinition(String enterString){
        String[] strData = enterString.split("\s+");

        Pattern operatorPattern = Pattern.compile("\\+|-|\\*|/");
        Matcher myMatcher = operatorPattern.matcher(strData[1]);

        if (strData.length != 3) {
            throw new OurUniversalException("throws Exception");
        }
        else
            if ((strData[0].length() <= 4 && strData[2].length() <= 4)
                                                                    && myMatcher.matches())
                return strData;
            else
                throw new OurUniversalException("throws Exception");
    }

    private static Integer[] numberDefinition(String[] dataForCalc){
        Integer[] operandArray = new Integer[2];

        Pattern numberPattern = Pattern.compile("[0-9]+");
        Matcher myMatcherZero = numberPattern.matcher(dataForCalc[0]);

        Matcher myMatcherTwo = numberPattern.matcher(dataForCalc[2]);

                if (myMatcherZero.matches() && myMatcherTwo.matches()){
                    if (stringToNumber.containsKey(dataForCalc[0]) &&
                            stringToNumber.containsKey(dataForCalc[2])) {
                        operandArray[0] = stringToNumber.get(dataForCalc[0]);
                        operandArray[1] = stringToNumber.get(dataForCalc[2]);
                        arabicNumber = true;
                    }
                    else throw new OurUniversalException("throws Exception");
                }
                else
                    if (romanToArabic.containsKey(dataForCalc[0]) &&
                             romanToArabic.containsKey(dataForCalc[2])) {
                    operandArray[0] = romanToArabic.get(dataForCalc[0]);
                    operandArray[1] = romanToArabic.get(dataForCalc[2]);
                    arabicNumber = false;
                }
                else throw new OurUniversalException("throws Exception");

        return operandArray;
    }

    private static boolean repeatCalculation(){
        System.out.print("\nПовторить расчет (Yes/No): ");
        Scanner scanner = new Scanner(System.in);
        String yesOrNoAnswer = scanner.nextLine().trim();

        Pattern yesPattern = Pattern.compile("Yes|yes");
        Matcher yesMatcher = yesPattern.matcher(yesOrNoAnswer);
        Pattern noPattern = Pattern.compile("No|no");
        Matcher noMatcher = noPattern.matcher(yesOrNoAnswer);

        if (yesMatcher.matches()) {
            repeatCalc = true;
        }
        else if (noMatcher.matches()) {
            repeatCalc = false;
        }
        else repeatCalculation() ;

        return repeatCalc;
    }

    static Map<String, Integer> stringToNumber = Map.of( "1",1, "2",2, "3",3,
                                                  "4",4, "5",5, "6",6,
                                                  "7",7, "8",8, "9",9,
                                                  "10",10
    );

    static Map<String, Integer> romanToArabic = Map.of( "I",1, "II",2, "III",3,
                                                 "IV",4, "V",5, "VI",6,
                                                 "VII",7, "VIII",8, "IX",9,
                                                "X",10
    );

    static Map<Integer, String> arabicToRoman = new HashMap<Integer, String>(){{
        put(1,"I"); put(2,"II"); put(3,"III"); put(4,"IV"); put(5,"V");
        put(6,"VI"); put(7,"VII"); put(8,"VIII"); put(9,"IX"); put(10,"X");
        put(11,"XI"); put(12,"XII"); put(13,"XIII"); put(14,"XIV"); put(15,"XV");
        put(16,"XVI"); put(17,"XVII"); put(18,"XVIII"); put(19,"XIX"); put(20,"XX");
        put(21,"XI"); put(22,"XII"); put(23,"XIII"); put(24,"XIV"); put(25,"XV");
        put(26,"XVI"); put(27,"XVII"); put(28,"XVIII"); put(29,"XIX"); put(30,"XXX");
        put(31,"XXXI"); put(32,"XXXII"); put(33,"XXXIII"); put(34,"XXXIV"); put(35,"XXXV");
        put(36,"XXXVI"); put(37,"XXXVII"); put(38,"XXXVIII"); put(39,"XXXIX"); put(40,"XL");
        put(41,"XLI"); put(42,"XLII"); put(43,"XLIII"); put(44,"XLIV"); put(45,"XLV");
        put(46,"XLVI"); put(47,"XLVII"); put(48,"XLVIII"); put(49,"XLIX"); put(50,"L");
        put(51,"LI"); put(52,"LII"); put(53,"LIII"); put(54,"LIV"); put(55,"LV");
        put(56,"LVI"); put(57,"LVII"); put(58,"LVIII"); put(59,"LIX"); put(60,"LX");
        put(61,"LXI"); put(62,"LXII"); put(63,"LXIII"); put(64,"LXIV"); put(65,"LXV");
        put(66,"LXVI"); put(67,"LXVII"); put(68,"LXVIII"); put(69,"LXIX"); put(70,"LXX");
        put(71,"LXXI"); put(72,"LXXII"); put(73,"LXXIII"); put(74,"LXXIV"); put(75,"LXXV");
        put(76,"LXXVI"); put(77,"LXXVII"); put(78,"LXXVIII"); put(79,"LXXIX"); put(80,"LXXX");
        put(81,"LXXXI"); put(82,"LXXXII"); put(83,"LXXXIII"); put(84,"LXXXIV"); put(85,"LXXXV");
        put(86,"LXXXVI"); put(87,"LXXXVII"); put(88,"LXXXVIII"); put(89,"LXXXIX"); put(90,"XC");
        put(91,"XCI"); put(92,"XCII"); put(93,"XCIII"); put(94,"XCIV"); put(95,"XCV");
        put(96,"XCVI"); put(97,"XCVII"); put(98,"XCVIII"); put(99,"XCIX"); put(100,"C");
    }};

    private static class OurUniversalException extends RuntimeException{
        public OurUniversalException() {
        }
        public OurUniversalException(String message) {
            super(message);
        }
    }
}