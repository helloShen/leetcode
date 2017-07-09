/**
 * Unit test for Phone Number
 */
package com.ciaoshen.leetcode;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class PhoneNumberTest {
    private String digit1 = "23";
    private List<String> answer1 = new ArrayList<>(Arrays.asList(new String[]{"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"}));

    @Test
    public void testLetterCombinations() {
        PhoneNumber pn = new PhoneNumber();
        List<String> result1 = pn.letterCombinations(digit1);
        assertThat("List equality without order",result1,containsInAnyOrder(expected.toArray()));
    }
}
