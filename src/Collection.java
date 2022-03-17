import java.util.ArrayList;
import java.util.List;

class Collection {
    static List<Integer> count(int n) {
        List<Integer> a = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            a.add(i);
        }
        return a;
    }

    static List<String> concatenateLists(List<String> a, List<String> b) {
        List<String> c = new ArrayList<String>(a);
        c.addAll(b);
        return c;
    }

    public static void printList(List<String> a) {
        System.out.println("Элементов в списке: " + a.toArray().length);
        for (String s : a) {
            System.out.println(s);
        }
    }

    public static void printListWithIndices(List<String> a) {
        System.out.println("Элементов в списке: " + a.toArray().length);
        int i = 0;
        for (String s : a) {
            i++;
            System.out.println(i + ". " + s);
        }
    }

    static List<String> reverseList(List<String> a){
        List<String> b = new ArrayList<>();
        for (int i = a.toArray().length-1; i >= 0; i--) {
            b.add(a.get(i));
        }
        return b;
    }

    public static void reverseListInPlace(List<String> a){
        List<String> b = new ArrayList<String>(a);
        int k = 0;
        for (int i = a.toArray().length-1; i >= 0; i--) {
            a.set(k, b.get(i));
            k++;
        }
    }

    public static void main(String[] args) {
        List<String> list1 = List.of("abc", "xyz", "ooo");
        printListWithIndices(list1);

        List<Integer> listInt = count(5);
        System.out.println(listInt);

        List<String> list3 = List.of("aaa", "bbb", "ccc");
        List<String> list4 = List.of("xxx", "yyy", "zzz");
        List<String> list3plusList4 = concatenateLists(list3, list4);
        System.out.println(list3plusList4); //aaa bbb ccc xxx yyy zzz

        //созадем изменяемый список list5.
        List<String> list5 = new ArrayList<>(List.of("first", "middle", "last"));
        //сначала чистая функция
        List<String> list5rev = reverseList(list5);
        //проверяем, что list5rev перевернутый, а list5 остался без изменений.
        System.out.println("list5rev = " + list5rev + ", but list5 = " + list5);

        //теперь функция, которая меняет сам список
        reverseListInPlace(list5);
        //проверяем, что список действительно изменился
        System.out.println("list5 = " + list5);

    }
}
