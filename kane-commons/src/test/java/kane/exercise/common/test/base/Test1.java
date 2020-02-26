package kane.exercise.common.test.base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.google.common.base.Splitter;
import org.junit.Test;

/**
 * @author kane
 */
public class Test1 {
    @Test
    public void test1(){
        LocalDate lastest = LocalDate.parse("20191208", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate current = LocalDate.parse("20190901", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(lastest.getYear() + "-" + lastest.getMonth());
        System.out.println(current.getYear() + "-" + current.getMonth());
        System.out.println((lastest.getYear() * 12 + lastest.getMonthValue() - current.getYear() * 12 - current.getMonthValue()));
    }

    @Test
    public void test2(){
        System.out.println("a,b,c,a,d,e,a,f".replace("a", "1"));
    }

    @Test
    public void test3(){
        //noinspection UnstableApiUsage
        Map<String, String> split =
                Splitter.on(';').trimResults().withKeyValueSeparator(Splitter.on(",").trimResults()).split("LIST:0 ,男 ; 1,0".substring(5));

    }

}