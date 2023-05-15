// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        SignUpAndIn sign = new SignUpAndIn();
        System.out.println("add user 1");
        sign.singUp();
        System.out.println("add user 2");
        sign.singUp();
        System.out.println("read user");
        System.out.println(sign.singIn());

    }
}