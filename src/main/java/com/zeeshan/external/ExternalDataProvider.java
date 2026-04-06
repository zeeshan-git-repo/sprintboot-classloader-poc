package com.zeeshan.external;

public class ExternalDataProvider {
    public String fetchData() {
        for(int i = 0; i< 10; i++){
            try {
                System.out.println("Fetching data..." + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
        return "Hello from external class!";
    }
}
