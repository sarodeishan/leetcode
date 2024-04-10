package src;

import java.time.Instant;
import java.util.*;

public class InvalidTransactions implements TreeTemplate {

    record TestCase(String[] transactions, List<String> output) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
                new TestCase(new String[]{"alice,20,800,mtv", "alice,50,100,beijing"}, List.of("alice,20,800,mtv", "alice,50,100,beijing")),
                new TestCase(new String[]{"alice,20,800,mtv", "alice,50,1200,mtv"}, List.of("alice,50,1200,mtv")),
                new TestCase(new String[]{"alice,20,800,mtv", "bob,50,1200,mtv"}, List.of("bob,50,1200,mtv"))
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        InvalidTransactions obj = new InvalidTransactions();
        Instant start = Instant.now();
        var result = obj.invalidTransactions(testCase.transactions());
        boolean success = (Objects.equals(result, testCase.output()));
        StringBuilder message = new StringBuilder();
        message.append("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns")
                .append(" ").append("Success:" + (success))
                .append(" ").append("Output:" + result);
        if (success) {
            System.out.println(message);
        } else {
            System.err.println(message);
        }
        System.out.println();
    }

    public List<String> invalidTransactions(String[] transactions) {
        Set<String> result = new LinkedHashSet<>();
        Map<String, String> lastTransaction = new HashMap<>();
        for (String transaction : transactions) {
            String[] txn = transaction.split(",");
            if (Integer.parseInt(txn[2]) > 1000) {
                //invalid due to amount
                result.add(transaction);
            }
            if (lastTransaction.containsKey(txn[0])) {
                String[] lastTxn = lastTransaction.get(txn[0]).split(",");
                if (!Objects.equals(txn[3], lastTxn[3]) && Integer.parseInt(txn[1]) <= Integer.parseInt(lastTxn[1]) + 60) {
                    //invalid due to diff city and 60min timespan
                    result.add(lastTransaction.get(txn[0]));
                    result.add(transaction);
                }
            }
            lastTransaction.put(txn[0], transaction);
        }
        return result.stream().toList();
    }

}