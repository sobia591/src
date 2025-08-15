import java.util.Objects;

/**
 * Driver class for the Smartshelf class of Coursework Re-assessment
 * in the Software and Programming II module at BBK in 2024/25.
 *
 * @author Carsten Fuhs
 */
public class Coursework1Main {

    /* The following is a tiny "home-grown" testing framework.
     * We will see a more advanced framework, JUnit, later in the module.
     */

    /** Index value for the next test. */
    private static int testNo = 1;

    /** Number of passed tests. */
    private static int passes = 0;

    /** Number of failed tests. */
    private static int fails = 0;

    /** Output for successful test. */
    private static final String YEA = "OK    "; //"PASSED";

    /** Output for unsuccessful test. */
    private static final String NAY = "FAILED";

    /**
     * Acceptable distance from expected value for double values,
     * should be slightly above 0.
     */
    private static final double DELTA = 1e-9;

    /**
     * Tests two int values for equality.
     *
     * Side effects: screen output of test result and increment of static
     * counter variables according to result.
     *
     * @param description  to be used as part of the screen output
     * @param expected  the expected value
     * @param actual  the actual value
     */
    public static void testIntEqual(String description, int expected, int actual) {
        sideEffectsForTest(description, expected + "", actual + "",
            expected == actual);
    }

    /**
     * Tests two long values for equality.
     *
     * Side effects: screen output of test result and increment of static
     * counter variables according to result.
     *
     * @param description  to be used as part of the screen output
     * @param expected  the expected value
     * @param actual  the actual value
     */
    public static void testLongEqual(String description, long expected, long actual) {
        sideEffectsForTest(description, expected + "", actual + "",
            expected == actual);
    }

    /**
     * Tests two double values for equality (up to a small "delta").
     *
     * Side effects: screen output of test result and increment of static
     * counter variables according to result.
     *
     * @param description  to be used as part of the screen output
     * @param expected  the expected value
     * @param actual  the actual value
     */
    public static void testDoubleEqual(String description, double expected, double actual) {
        sideEffectsForTest(description, expected + "", actual + "",
            expected - DELTA <= actual && actual <= expected + DELTA);
        // small rounding errors are ok
    }

    /**
     * Tests two Objects for equality. Works for Object and all its subclasses
     * (String, Product, ...).
     *
     * Side effects: screen output of test result and increment of static
     * counter variables according to result.
     *
     * @param description  to be used as part of the screen output
     * @param expected  the expected value
     * @param actual  the actual value
     */
    public static void testObjectEqual(String description, Object expected, Object actual) {
        sideEffectsForTest(description, expected + "", actual + "",
            Objects.equals(expected, actual));
        // Objects.equals is graceful on null
    }

    /**
     * Helper method for the side effects of the tests for different data types
     * (here already converted to Strings): screen output and increment of
     * static counter variables.
     *
     * @param description  description of the test to be printed
     * @param expected  String representation of the expected value
     * @param actual  String representation of the actual value
     * @param result  true: test has passed; false: test has failed
     */
    private static void sideEffectsForTest(String description, String expected, String actual, boolean result) {
        String output;
        if (result) {
            passes++;
            output = YEA;
        } else {
            fails++;
            output = NAY;
        }
        //System.out.println("Test " + testNo + ": " + description
        //    + ", expected: " + expected + ", actual: " + actual
        //    + " ===> " + output);
        System.out.println(output + " - Test " + testNo + ": " + description
            + ", expected: " + expected + ", actual: " + actual);
        testNo++;
    }

    /* The code to test our Smartshelf in particular starts here. */

    /**
     * Constants for use in the tests.
     */
    private static final Item ITEM1 = new Item("Pen", 35);
    private static final Item ITEM2 = new Item("Face mask", 20); 
    private static final Item ITEM3 = new Item("Kilogramme", 1000);
    private static final Item ITEM4 = new Item("Soda", 400);
    private static final Item ITEM5 = new Item("Water", 395);
    private static final Item ITEM6 = new Item("Lemonade", 400);
    private static final Item ITEM7 = new Item("Kilo", 1000);
    private static final Item ITEM8 = new Item("Book", 120);

    /* Methods to create suitably constructed and modified Smartshelf objects. */

    /**
     * @return an empty Smartshelf
     */
    private static Smartshelf makeEmptySmartshelf() {
        return new Smartshelf();
    }

    /**
     * @return a Smartshelf to which ITEM1 has been added
     */
    private static Smartshelf makeAddOneItemSmartshelf() {
        Smartshelf k = makeEmptySmartshelf();
        k.add(ITEM1);
        return k;
    }

    /**
     * @return a Smartshelf to which ITEM8 has been added twice
     */
    private static Smartshelf makeAddTwoItemSameSmartshelf() {
        Smartshelf k = makeEmptySmartshelf();
        k.add(ITEM8);
        k.add(ITEM8);
        return k;
    }

    /**
     * @return a Smartshelf to which ITEM8, null, ITEM8 have been added
     */
    private static Smartshelf makeAddTwoItemSameAndNullSmartshelf() {
        Smartshelf k = makeEmptySmartshelf();
        k.add(ITEM8);
        k.add(null);
        k.add(ITEM8);
        return k;
    }

    /**
     * @return a Smartshelf to which ITEM1, null, ITEM2 have been added
     */
    private static Smartshelf makeAddTwoItemAndNullSmartshelf() {
        Smartshelf k = makeEmptySmartshelf();
        k.add(ITEM1);
        k.add(null);
        k.add(ITEM2);
        return k;
    }

    /**
     * @return a Smartshelf on which addAll was invoked with ITEM1, null, ITEM2
     */
    private static Smartshelf makeAddAllTwoItemAndNullSmartshelf() {
        Smartshelf k = makeEmptySmartshelf();
        Item[] items = { ITEM1, null, ITEM2 };
        k.addAll(items);
        return k;
    }

    /**
     * @return a Smartshelf constructed with ITEM1, null, ITEM2 in the argument
     *  array
     */
    private static Smartshelf makeConstructorTwoItemAndNullSmartshelf() {
        Item[] items = { ITEM1, null, ITEM2 };
        Smartshelf k = new Smartshelf(items);
        return k;
    }

    /**
     * @return a Smartshelf with both constructor arguments and a call to add()
     */
    private static Smartshelf makeConstructorAddTwoItemAndNullSmartshelf() {
        Item[] items = { ITEM1, null };
        Smartshelf k = new Smartshelf(items);
        k.add(ITEM2);
        return k;
    }

    /**
     * @return a Smartshelf on which reset() was called right before returning
     */
    private static Smartshelf makeResetSmartshelf() {
        Item[] items = { ITEM1, null };
        Smartshelf k = new Smartshelf(items);
        k.add(ITEM2);
        k.empty();
        return k;
    }

    /**
     * @return a Smartshelf on which the keepOnlyItemsWith() mutator has been
     *  called to remove some items
     */
    private static Smartshelf makeKeepSmartshelf() {
        Item[] items = { ITEM3, null, ITEM4, ITEM5, ITEM6, ITEM7, ITEM8, null, null, ITEM8  };
        Smartshelf k = new Smartshelf(items);
        k.add(ITEM2);
        k.keepOnlyItemsWith(400);
        return k;
    }

    /**
     * @return an array with two Smartshelfs: the first one has ITEM1, ITEM2,
     *  and the second one has ITEM3
     */
    private static Smartshelf[] makeTwoSmartshelfs() {
        return new Smartshelf[] { new Smartshelf(new Item[] { ITEM1, ITEM2 }),
                                new Smartshelf(new Item[] { ITEM3 })};
    }

    /**
     * Main method that drives the tests.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        Smartshelf sack;
        sack = makeEmptySmartshelf();
        testObjectEqual("toString", "[]", sack.toString());
        sack = makeEmptySmartshelf();
        testObjectEqual("greatestItem", null, sack.greatestItem());
        sack = makeEmptySmartshelf();
        testIntEqual("numberOfItems", 0, sack.numberOfItems());
        sack = makeEmptySmartshelf();
        testIntEqual("makeNewSmartshelfWith", 0, sack.makeNewSmartshelfWith(120).numberOfItems());
        sack = makeEmptySmartshelf();
        testIntEqual("totalWeightInGrammes", 0, sack.totalWeightInGrammes());
        sack = makeEmptySmartshelf();
        testDoubleEqual("averageWeightInGrammes", -1.0, sack.averageWeightInGrammes());

        sack = makeAddOneItemSmartshelf();
        testObjectEqual("toString", "[" + ITEM1 + "]", sack.toString());
        sack = makeAddOneItemSmartshelf();
        testObjectEqual("greatestItem", ITEM1, sack.greatestItem());
        sack = makeAddOneItemSmartshelf();
        testIntEqual("numberOfItems", 1, sack.numberOfItems());
        sack = makeAddOneItemSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 1, sack.makeNewSmartshelfWith(120).numberOfItems());
        sack = makeAddOneItemSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 0, sack.makeNewSmartshelfWith(10).numberOfItems());
        sack = makeAddOneItemSmartshelf();
        testIntEqual("totalWeightInGrammes", 35, sack.totalWeightInGrammes());
        sack = makeAddOneItemSmartshelf();
        testDoubleEqual("averageWeightInGrammes", 35.0, sack.averageWeightInGrammes());

        sack = makeAddTwoItemSameSmartshelf();
        testObjectEqual("toString", "[" + ITEM8 + ", " + ITEM8 + "]", sack.toString());
        sack = makeAddTwoItemSameSmartshelf();
        testObjectEqual("greatestItem", ITEM8, sack.greatestItem());
        sack = makeAddTwoItemSameSmartshelf();
        testIntEqual("numberOfItems", 2, sack.numberOfItems());
        sack = makeAddTwoItemSameSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 2, sack.makeNewSmartshelfWith(120).numberOfItems());
        sack = makeAddTwoItemSameSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 0, sack.makeNewSmartshelfWith(10).numberOfItems());
        sack = makeAddTwoItemSameSmartshelf();
        testIntEqual("totalWeightInGrammes", 240, sack.totalWeightInGrammes());
        sack = makeAddTwoItemSameSmartshelf();
        testDoubleEqual("averageWeightInGrammes", 120.0, sack.averageWeightInGrammes());

        sack = makeAddTwoItemSameAndNullSmartshelf();
        testObjectEqual("toString", "[" + ITEM8 + ", " + ITEM8 + "]", sack.toString());

        sack = makeAddTwoItemAndNullSmartshelf();
        testObjectEqual("greatestItem", ITEM1, sack.greatestItem());
        sack = makeAddTwoItemAndNullSmartshelf();
        testIntEqual("numberOfItems", 2, sack.numberOfItems());
        sack = makeAddTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 2, sack.makeNewSmartshelfWith(120).numberOfItems());
        sack = makeAddTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 1, sack.makeNewSmartshelfWith(21).numberOfItems());
        sack = makeAddTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 1, sack.makeNewSmartshelfWith(20).numberOfItems());
        sack = makeAddTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 0, sack.makeNewSmartshelfWith(19).numberOfItems());
        sack = makeAddTwoItemAndNullSmartshelf();
        testIntEqual("totalWeightInGrammes", 55, sack.totalWeightInGrammes());
        sack = makeAddTwoItemAndNullSmartshelf();
        testDoubleEqual("averageWeightInGrammes", 27.5, sack.averageWeightInGrammes());

        sack = makeAddAllTwoItemAndNullSmartshelf();
        testObjectEqual("greatestItem", ITEM1, sack.greatestItem());
        sack = makeAddAllTwoItemAndNullSmartshelf();
        testIntEqual("numberOfItems", 2, sack.numberOfItems());
        sack = makeAddAllTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 2, sack.makeNewSmartshelfWith(120).numberOfItems());
        sack = makeAddAllTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 1, sack.makeNewSmartshelfWith(21).numberOfItems());
        sack = makeAddAllTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 1, sack.makeNewSmartshelfWith(20).numberOfItems());
        sack = makeAddAllTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 0, sack.makeNewSmartshelfWith(19).numberOfItems());
        sack = makeAddAllTwoItemAndNullSmartshelf();
        testIntEqual("totalWeightInGrammes", 55, sack.totalWeightInGrammes());
        sack = makeAddAllTwoItemAndNullSmartshelf();
        testDoubleEqual("averageWeightInGrammes", 27.5, sack.averageWeightInGrammes());

        sack = makeConstructorTwoItemAndNullSmartshelf();
        testObjectEqual("greatestItem", ITEM1, sack.greatestItem());
        sack = makeConstructorTwoItemAndNullSmartshelf();
        testIntEqual("numberOfItems", 2, sack.numberOfItems());
        sack = makeConstructorTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 2, sack.makeNewSmartshelfWith(120).numberOfItems());
        sack = makeConstructorTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 1, sack.makeNewSmartshelfWith(21).numberOfItems());
        sack = makeConstructorTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 1, sack.makeNewSmartshelfWith(20).numberOfItems());
        sack = makeConstructorTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 0, sack.makeNewSmartshelfWith(19).numberOfItems());
        sack = makeConstructorTwoItemAndNullSmartshelf();
        testIntEqual("totalWeightInGrammes", 55, sack.totalWeightInGrammes());
        sack = makeConstructorTwoItemAndNullSmartshelf();
        testDoubleEqual("averageWeightInGrammes", 27.5, sack.averageWeightInGrammes());

        sack = makeConstructorAddTwoItemAndNullSmartshelf();
        testObjectEqual("greatestItem", ITEM1, sack.greatestItem());
        sack = makeConstructorAddTwoItemAndNullSmartshelf();
        testIntEqual("numberOfItems", 2, sack.numberOfItems());
        sack = makeConstructorAddTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 2, sack.makeNewSmartshelfWith(120).numberOfItems());
        sack = makeConstructorAddTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 1, sack.makeNewSmartshelfWith(21).numberOfItems());
        sack = makeConstructorAddTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 1, sack.makeNewSmartshelfWith(20).numberOfItems());
        sack = makeConstructorAddTwoItemAndNullSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 0, sack.makeNewSmartshelfWith(19).numberOfItems());
        sack = makeConstructorAddTwoItemAndNullSmartshelf();
        testIntEqual("totalWeightInGrammes", 55, sack.totalWeightInGrammes());
        sack = makeConstructorAddTwoItemAndNullSmartshelf();
        testDoubleEqual("averageWeightInGrammes", 27.5, sack.averageWeightInGrammes());

        sack = makeResetSmartshelf();
        testObjectEqual("toString", "[]", sack.toString());
        sack = makeResetSmartshelf();
        testObjectEqual("greatestItem", null, sack.greatestItem());
        sack = makeResetSmartshelf();
        testIntEqual("numberOfItems", 0, sack.numberOfItems());
        sack = makeResetSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 0, sack.makeNewSmartshelfWith(120).numberOfItems());
        sack = makeResetSmartshelf();
        testIntEqual("totalWeightInGrammes", 0, sack.totalWeightInGrammes());
        sack = makeResetSmartshelf();
        testDoubleEqual("averageWeightInGrammes", -1.0, sack.averageWeightInGrammes());

        sack = makeKeepSmartshelf();
        testIntEqual("greatestItem", 400, sack.greatestItem().getWeightInGrammes());
        sack = makeKeepSmartshelf();
        testIntEqual("numberOfItems", 6, sack.numberOfItems());
        sack = makeKeepSmartshelf();
        testIntEqual("makeNewSmartshelfWith", 3, sack.makeNewSmartshelfWith(120).numberOfItems());
        sack = makeKeepSmartshelf();
        testIntEqual("totalWeightInGrammes", 1455, sack.totalWeightInGrammes());
        sack = makeKeepSmartshelf();
        testDoubleEqual("averageWeightInGrammes", 242.5, sack.averageWeightInGrammes());

        testObjectEqual("heaviestSmartshelf", null, Smartshelf.heaviestSmartshelf(new Smartshelf[0]));

        Smartshelf[] sacks = makeTwoSmartshelfs();
        testIntEqual("heaviestSmartshelf", 1000, Smartshelf.heaviestSmartshelf(sacks).totalWeightInGrammes());

        System.out.println();
        System.out.println(YEA + ": " + passes);
        System.out.println(NAY + ": " + fails);
    }

    /*

OK     - Test 1: toString, expected: [], actual: []
OK     - Test 2: greatestItem, expected: null, actual: null
OK     - Test 3: numberOfItems, expected: 0, actual: 0
OK     - Test 4: makeNewSmartshelfWith, expected: 0, actual: 0
OK     - Test 5: totalWeightInGrammes, expected: 0, actual: 0
OK     - Test 6: averageWeightInGrammes, expected: -1.0, actual: -1.0
OK     - Test 7: toString, expected: [(Pen, 35g)], actual: [(Pen, 35g)]
OK     - Test 8: greatestItem, expected: (Pen, 35g), actual: (Pen, 35g)
OK     - Test 9: numberOfItems, expected: 1, actual: 1
OK     - Test 10: makeNewSmartshelfWith, expected: 1, actual: 1
OK     - Test 11: makeNewSmartshelfWith, expected: 0, actual: 0
OK     - Test 12: totalWeightInGrammes, expected: 35, actual: 35
OK     - Test 13: averageWeightInGrammes, expected: 35.0, actual: 35.0
OK     - Test 14: toString, expected: [(Book, 120g), (Book, 120g)], actual: [(Book, 120g), (Book, 120g)]
OK     - Test 15: greatestItem, expected: (Book, 120g), actual: (Book, 120g)
OK     - Test 16: numberOfItems, expected: 2, actual: 2
OK     - Test 17: makeNewSmartshelfWith, expected: 2, actual: 2
OK     - Test 18: makeNewSmartshelfWith, expected: 0, actual: 0
OK     - Test 19: totalWeightInGrammes, expected: 240, actual: 240
OK     - Test 20: averageWeightInGrammes, expected: 120.0, actual: 120.0
OK     - Test 21: toString, expected: [(Book, 120g), (Book, 120g)], actual: [(Book, 120g), (Book, 120g)]
OK     - Test 22: greatestItem, expected: (Pen, 35g), actual: (Pen, 35g)
OK     - Test 23: numberOfItems, expected: 2, actual: 2
OK     - Test 24: makeNewSmartshelfWith, expected: 2, actual: 2
OK     - Test 25: makeNewSmartshelfWith, expected: 1, actual: 1
OK     - Test 26: makeNewSmartshelfWith, expected: 1, actual: 1
OK     - Test 27: makeNewSmartshelfWith, expected: 0, actual: 0
OK     - Test 28: totalWeightInGrammes, expected: 55, actual: 55
OK     - Test 29: averageWeightInGrammes, expected: 27.5, actual: 27.5
OK     - Test 30: greatestItem, expected: (Pen, 35g), actual: (Pen, 35g)
OK     - Test 31: numberOfItems, expected: 2, actual: 2
OK     - Test 32: makeNewSmartshelfWith, expected: 2, actual: 2
OK     - Test 33: makeNewSmartshelfWith, expected: 1, actual: 1
OK     - Test 34: makeNewSmartshelfWith, expected: 1, actual: 1
OK     - Test 35: makeNewSmartshelfWith, expected: 0, actual: 0
OK     - Test 36: totalWeightInGrammes, expected: 55, actual: 55
OK     - Test 37: averageWeightInGrammes, expected: 27.5, actual: 27.5
OK     - Test 38: greatestItem, expected: (Pen, 35g), actual: (Pen, 35g)
OK     - Test 39: numberOfItems, expected: 2, actual: 2
OK     - Test 40: makeNewSmartshelfWith, expected: 2, actual: 2
OK     - Test 41: makeNewSmartshelfWith, expected: 1, actual: 1
OK     - Test 42: makeNewSmartshelfWith, expected: 1, actual: 1
OK     - Test 43: makeNewSmartshelfWith, expected: 0, actual: 0
OK     - Test 44: totalWeightInGrammes, expected: 55, actual: 55
OK     - Test 45: averageWeightInGrammes, expected: 27.5, actual: 27.5
OK     - Test 46: greatestItem, expected: (Pen, 35g), actual: (Pen, 35g)
OK     - Test 47: numberOfItems, expected: 2, actual: 2
OK     - Test 48: makeNewSmartshelfWith, expected: 2, actual: 2
OK     - Test 49: makeNewSmartshelfWith, expected: 1, actual: 1
OK     - Test 50: makeNewSmartshelfWith, expected: 1, actual: 1
OK     - Test 51: makeNewSmartshelfWith, expected: 0, actual: 0
OK     - Test 52: totalWeightInGrammes, expected: 55, actual: 55
OK     - Test 53: averageWeightInGrammes, expected: 27.5, actual: 27.5
OK     - Test 54: toString, expected: [], actual: []
OK     - Test 55: greatestItem, expected: null, actual: null
OK     - Test 56: numberOfItems, expected: 0, actual: 0
OK     - Test 57: makeNewSmartshelfWith, expected: 0, actual: 0
OK     - Test 58: totalWeightInGrammes, expected: 0, actual: 0
OK     - Test 59: averageWeightInGrammes, expected: -1.0, actual: -1.0
OK     - Test 60: greatestItem, expected: 400, actual: 400
OK     - Test 61: numberOfItems, expected: 6, actual: 6
OK     - Test 62: makeNewSmartshelfWith, expected: 3, actual: 3
OK     - Test 63: totalWeightInGrammes, expected: 1455, actual: 1455
OK     - Test 64: averageWeightInGrammes, expected: 242.5, actual: 242.5
OK     - Test 65: heaviestSmartshelf, expected: null, actual: null
OK     - Test 66: heaviestSmartshelf, expected: 1000, actual: 1000

OK    : 66
FAILED: 0

     */
}
