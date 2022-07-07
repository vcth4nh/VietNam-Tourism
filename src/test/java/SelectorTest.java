import utils.Selector;

public class SelectorTest {
    public static void main(String[] args) {
        Selector selector;
        try {
            selector = new Selector("src/main/resources/tourism-object/selector.json");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println(selector.tourismObjects());
        for (String oj : selector.getClassName()) {
            System.out.println(oj);
            System.out.println(selector.objectSelector(oj));
        }
    }
}
