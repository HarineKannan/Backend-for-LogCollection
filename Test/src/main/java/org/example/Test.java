package org.example;
import org.example.InsertIntoElasticsearch;

import java.io.IOException;

public class Test {
    public native Object[] getArray(String neededLog);

    static {
        System.load("C:\\Users\\harine-pt7602\\source\\repos\\test\\x64\\Debug\\test.dll");
    }

    public static void main(String[] args) throws IOException,NullPointerException {
        boolean includeTimestamp = true;
        boolean includeEventCode = false;
        boolean includeSourcename = true;
        boolean includeMessage = true;
        String neededLog="Application";
        Test sample = new Test();
        Object[] result = sample.getArray(neededLog);
        for (Object obj : result) {
            Object[] array = (Object[]) obj;
            System.out.println("Event Code: " + array[0] + ", Source Name: " + array[1] + ".time " + array[2] + "Message" + array[3] );
        }
        InsertIntoElasticsearch.insertion(result,includeEventCode,includeSourcename,includeTimestamp,includeMessage);
    }
}