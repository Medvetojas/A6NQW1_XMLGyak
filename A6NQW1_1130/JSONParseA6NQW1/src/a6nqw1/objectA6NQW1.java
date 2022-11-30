package a6nqw1;

import org.json.simple.JSONObject;

public class objectA6NQW1 {
    public static void main(String[] args){
        JSONObject obj = new JSONObject();

        obj.put("Név:", "BSzilard");
        obj.put("Fizetés:", 100000000);
        obj.put("Kor:", 20);

        System.out.print(obj);

    }
}
