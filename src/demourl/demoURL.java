package demourl;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Bap Bun
 */
public class demoURL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("https://fptshop.com.vn/tim-kiem?s=iphone+16&categories=may-tinh-xach-tay");
            System.out.println("Protocal: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + url.getPort());
            System.out.println("Path: " + url.getPath());
            System.out.println("File: " + url.getFile());
            System.out.println("Query: " + url.getQuery());
            System.out.println("Ref: " + url.getRef());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

}
