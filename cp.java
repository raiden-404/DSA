import java.util.Scanner;

public class cp {
    public static void main(String[] args) {
        int red = 0;
        int blue = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter red : ");
        red = sc.nextInt();
        System.out.print("Enter blue : ");
        blue = sc.nextInt();
        if(blue > red) {
            System.out.println("Win : color red");
        } else {
            System.out.println("Win : colur blue");
        }
    }
}
