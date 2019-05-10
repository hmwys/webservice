public class regexTest {
    public static void main(String[] args) {
        String s1 = "<p>1919年，山河动荡。百年前的中国，如同风雨飘摇里的一座将倾的大厦。\n" +
                "\n" +
                "\n" +
                "\n" +
                "1919年5月4日，由于中国巴黎外交的失败，郁积已久的亡国之忧和救国之情，骤然化作海啸山鸣般的五四运动。\n" +
                "\n" +
                "\n" +
                "\n" +
                "这些青年先进知识分子们，为了国家和民族存亡，成为洪流中的先锋。\n" +
                "\n" +
                "1919-2019，百年过去了。时代在变，然而，五四精神从未远去。我们看到，为了中国的强盛、为了民族的复兴，一代代青年，在最好的年华投身国家建设。\n" +
                "\n" +
                "\n" +
                "\n" +
                "如今，有人发出这样的疑问，“不放假不发钱，我们为什么要过五四？”\n" +
                "\n" +
                "\n" +
                "htpicpic/p01.pnghtpice"+
                "作者：共青团中央\n" +
                "https://www.bilibili.com/read/cv2506739\n" +
                "出处： bilibili</p>";
        System.out.println(s1.replaceAll("\\n", "<br></p><p>"));
        System.out.println(s1);
    }
}
