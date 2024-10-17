import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        HashMap<Object, Object> hashmap = new HashMap<>();
//            hashmap.put(22,"A");
//            hashmap.put(55,"B");
//            hashmap.put(33,"Z");
//            hashmap.put(44,"M");
//            hashmap.put(99,"I");
//            hashmap.put(88,"X");
//                System.out.println("Iterating Hashmap...");
//                for(Map.Entry m : hashmap.entrySet()){
//                    System.out.println(m.getKey()+" "+m.getValue());
//                }
        String[] names={"muthaiya","Annachi","vijay", "sanjay"};
        Arrays.sort(names);
        for(String a:names){
            System.out.println(a);
        }
    }
}