import java.net.URL.*;

public class Test {
    
	public static void main(String... args) throws Exception {
        URL location = Test.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
    }
}