package org.example;

import java.util.regex.Pattern;
import java.util.ArrayList;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            String delimiter = useCustomDelimiter(numbers)[0];
            String numbersPart = useCustomDelimiter(numbers)[1];
            String[] nums = numbersPart.split(delimiter);
            return getSum(nums);
        }
    }

    private int getSum(String[] nums) {
        int sum = 0;
        ArrayList<String> negatives = new ArrayList<>();
        for (String num : nums) {
            if (num.isEmpty()) continue;
            int intNum = Integer.parseInt(num);
            if (intNum < 0) {
                negatives.add(String.valueOf(intNum));
            } else if (intNum <= 1000) {
                sum += intNum;
            }
        }
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException(
                    "negatives not allowed: " + String.join(", ", negatives)  // Note the space
            );
        }
        return sum;
    }

    public static String[] useCustomDelimiter(String numbers) {
        String delimiter = "[,\\n]";
        String numbersPart = numbers;

        if (numbers.startsWith("//")) {
            int delimiterEnd = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterEnd);
            if (delimiter.isEmpty()) {
                delimiter = "\n";
            } else {
                delimiter = Pattern.quote(delimiter);
            }
            numbersPart = numbers.substring(delimiterEnd + 1);
        }
        String[] parts = new String[]{delimiter, numbersPart};
        return parts;
    }
}
