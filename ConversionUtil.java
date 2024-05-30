package OA;

import java.util.*;

public class ConversionUtil {

    /**
     * class {
     *     List<ConvertRate> getConvertRate<>();
     * }
     */
    static class ConversionRate {
        String fromUnit;
        String toUnit;
        double rate;

        public ConversionRate(String fromUnit,String toUnit,double rate){
            this.fromUnit = fromUnit;
            this.toUnit = toUnit;
            this.rate = rate;
        }
    }

    static List<ConversionRate> conversionRates = new ArrayList<>();
    static Map<String, Map<String,Double>> graph = buildGraph(conversionRates);

    public static Map<String, Map<String, Double>> buildGraph(List<ConversionRate> conversionRates) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        // Undirected graph
        // Key: fromUnit, Value: Map<K: toUnit, V: rate>
        for (ConversionRate cr : conversionRates) {
            if (!graph.containsKey(cr.fromUnit)) {
                graph.put(cr.fromUnit, new HashMap<>());
            }
            graph.get(cr.fromUnit).put(cr.toUnit, cr.rate);
            if (!graph.containsKey(cr.toUnit)) {
                graph.put(cr.toUnit, new HashMap<>());
            }
            graph.get(cr.toUnit).put(cr.fromUnit, (double) 1.0 / cr.rate);
        }
        return graph;
    }


    public static double convert() {

        return 0.0;
    }

    public static double getExchangeRate(String fromUnit, String toUnit, Integer value){
        if (fromUnit == null || toUnit == null) {
            return 0.0;
        }

        // adding each unit to the queue til found the correct conversion rate
        Queue<String> queue = new ArrayDeque<>();
        // dedup for every visited node
        Set<String> seen = new HashSet<>();

        queue.add(fromUnit);
        seen.add(fromUnit);

        while(!queue.isEmpty()){
            String curUnit = queue.poll();

            Map<String, Double> nexts = graph.get(curUnit);

            for (String nextUnit : nexts.keySet()) {
                if (seen.contains(nextUnit)) {
                    continue;
                }

                if (nextUnit.equals(toUnit)) {
                    return value * nexts.get(nextUnit);
                }

                queue.offer(nextUnit);
                seen.add(nextUnit);
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        // Creating original table
/*        Currency obj1=new Currency("USD","JPY",113.6);
        Currency obj2=new Currency("USD","AUD",1.39);
        Currency obj3=new Currency("JPY","GBP",0.0065);
        Currency obj4=new Currency("INR","USD",0.013);
        Currency obj5=new Currency("GBP","EUR",1.19);

        Currency[] curr=new Currency[5];
        curr[0]=obj1;
        curr[1]=obj2;
        curr[2]=obj3;
        curr[3]=obj4;
        curr[4]=obj5;
        System.out.println(getExchangeRate(curr,"INR","EUR"));
        System.out.println(getExchangeRate(curr,"USD","JPY"));*/
    }
}
