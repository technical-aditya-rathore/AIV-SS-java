package Start;

import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;

import static Start.Task3.Sort;

/**
 * Created by Alex on 17.02.14.
 */
public class Task3Test {

    @Test
    public void testSort() {
        double[] DefaultMas = new double[]{2, 3, 5, 1};
        double[] SortOK = new double[]{1,2,3,5};
        if(!Arrays.equals(Sort(DefaultMas),SortOK)) Assert.fail("Sort test fail");
    }

    //написать тест на метод проверки правильности ввода
}
