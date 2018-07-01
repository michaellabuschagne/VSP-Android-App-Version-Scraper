package io.mikescode.app.version.scraper.test;

import io.mikescode.app.version.scraper.domain.model.AppPageRetrievalRegexException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleRegexTest {
    static String string = "googleplaysouce.html";
    public static void main(String[] args) {
        parsePageDataAndRetrieveVersion(getFileWithUtil());
    }

    static private String getFileWithUtil() {

        String result = "";

        ClassLoader classLoader = GoogleRegexTest.class.getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(string), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    static public String parsePageDataAndRetrieveVersion(String pageData) {
        String fullVersionText = performPatterMatch(pageData, "Current Version.*\\d\\.\\d\\.\\d", true);
        return performPatterMatch(fullVersionText, "\\d\\.\\d\\.\\d", false);
    }

    static private String performPatterMatch(String inputString, String regex, boolean failMultiMatch) {
        Pattern patter = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = patter.matcher(inputString);
        boolean foundMatch = false;
        String matchedString = null;
        while (matcher.find()) {
            System.out.println("Full match: [" + matcher.group(0)+"]");
            matchedString = matcher.group(0);
            if (foundMatch && failMultiMatch) {
                throw new AppPageRetrievalRegexException("More than one match found [" + matcher.group(0) + "]");
            }
            foundMatch = true;
            if (!failMultiMatch && foundMatch) {
                return matchedString;
            }
        }
        return matchedString;
    }
}
