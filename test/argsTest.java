public class argsTest {

    public static void main(String[] args) {
//        System.out.println(args);
        System.out.println(args.length);

        for (String s:args
             ) {

            System.out.printf(s+"\n");
        }

        System.out.println();
    }
}
