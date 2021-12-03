import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegularExpressions {
    static boolean isEmail(String text) {
        Pattern emailPattern = Pattern.compile("([a-z]+|(-)+|(_)+|(\\d)+)+@([a-z]+|(-)+|(_)+|(\\d)+)+\\.[a-z]{2,4}");
        Matcher m = emailPattern.matcher(text);
        return m.find();
    }

    static void isTime(String text) {
        Pattern numberPattern = Pattern.compile("\\d\\d:\\d\\d");
        Pattern timePattern = Pattern.compile("((0|1)\\d|20|21|22|23):(0|1|2|3|4|5)\\d");
        Matcher n = numberPattern.matcher(text);

        while (n.find()) {
            System.out.print(n.group());
            Matcher t = timePattern.matcher(n.group());
            if (t.find()) {
                System.out.println(" соответствует");
            } else {
                System.out.println(" не соответствует");
            }
        }
    }

    static String fixSpace(String text) {
        Pattern spacePattern = Pattern.compile("([^\\s]+)( +), ([^\\s]+)");
        Matcher m = spacePattern.matcher(text);
        while (m.find()) {
            //System.out.println("опа, попалась запятая");
            text = text.replaceAll("([^\\s]+)( +), ([^\\s]+)", "$1, $3");
            m = spacePattern.matcher(text); //обновлаем Matcher, text изменился
        }
        return text;
    }

    static String swapPlaces(String text) {
        Pattern spacePattern = Pattern.compile("([^\\s]+)-([^\\s]+)");
        Matcher m = spacePattern.matcher(text);
        while (m.find()) {
            //System.out.println("опа, попался дефис");
            text = text.replaceAll("([^\\s]+)-([^\\s]+)", "$2-$1");
        }
        return text;
    }

    static int cats(String text) {
        int k = 0;
        Pattern catPattern = Pattern.compile("([^a-z]+)кот([^a-z]+)", Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
        Matcher m = catPattern.matcher(text);
        while (m.find()) {
            k++;
        }
        return k;
    }

    static String count(String text) {
        Pattern numberPattern = Pattern.compile("([^\\d^-])(-?)([\\d]+)([^\\d])");
        Matcher m = numberPattern.matcher(text);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            //System.out.println("опа, попалось число "+m.group(2)+m.group(3));
            String letterFirst = m.group(1);
            String digits = m.group(2) + m.group(3);
            String letterLast = m.group(4);
            String replacement = letterFirst + (Integer.parseInt(digits) + 1) + letterLast;

            m.appendReplacement(sb, replacement);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String text = "Какой-то тудым-сюдым  , 23:16 and -27:66 and mrgroove91@gmail.com  , hello. Тот КОТ, который рокот";
        System.out.println(isEmail(text));
        isTime(text);
        System.out.println(fixSpace(text));
        System.out.println(swapPlaces(text));
        System.out.println(cats(text));
        System.out.println(count(text));
    }
}
