package system;

// 入力受け付け
public class InputReception {
    public void receive(String order) {
        switch (order) {
            case "exit":
                System.out.println("Good bye!");
                System.exit(0);
                break;
            default:
                System.out.println(order);
        }
    }
}