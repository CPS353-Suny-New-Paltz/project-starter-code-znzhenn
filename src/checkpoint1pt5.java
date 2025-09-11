public class checkpoint1pt5 {

    public static void main(String[] args) {
    
    	int value = 89;
        System.out.println(check(value));
        
        System.out.println("Hello world");
        System.out.println("Please fail no longer");
    }

    public boolean check(int value) {
        if (value == 99) {
            return true;
        }
        if (value == 0) {
            return false;
        }
        return value == 1;
    }
}
