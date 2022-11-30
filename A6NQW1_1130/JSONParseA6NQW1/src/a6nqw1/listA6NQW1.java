package a6nqw1;

import org.json.simple.JSONObject;

public class listA6NQW1 {
    public static void main(String[] args){
        JSONObject obj = new JSONObject();

        obj.put("nev", "BSZ");
        obj.put("fizetes", 100000000);
        obj.put("kor", 20);

        System.out.print(obj.get("nev") + " ");
        System.out.print(obj.get("fizetes") + " ");
        System.out.print(obj.get("kor"));

    }
}
