package objectA6NQW1;

import org.json.JSONObject;

public class ObjectA6NQW1 {
    public static void main(String[] args){
        JSONObject obj = new JSONObject();

        obj.put("nev", "BSzilard");
        obj.put("fizetes", 100000000);
        obj.put("kor", 20);

        System.out.print(obj);

    }
}
