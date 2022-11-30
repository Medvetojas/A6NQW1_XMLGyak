package a6nqw1;

import org.json.JSONObject;
import org.json.XML;

public class listA6NQW1 {
    public static void main(String[] args){
        JSONObject obj = new JSONObject();

        obj.put("nev", "BSZ");
        obj.put("fizetes", 100000000);
        obj.put("kor", 20);

        System.out.println("Név: "+obj.get("nev"));
        System.out.println("Fizetés:"+obj.get("fizetes"));
        System.out.println("Kor:"+obj.get("kor"));

    }
}
