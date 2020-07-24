import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.EventHandler;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main extends Application {


    public static void main(String[] args) {
        //validate("5f53b0d5d2cf89f218a78a25");

        Application.launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        boolean done = false;
        Scanner sc = new Scanner(System.in);

        while (!done) {
            System.out.println("Are you using (P)layer names or inputting (S)tats? (P/S): ");
            String usingNameString = sc.nextLine();

            if (usingNameString.equalsIgnoreCase("p")) {
                System.out.println("Enter your name: ");
                String playerName = sc.nextLine();
                System.out.println("Enter your opponents name: ");
                String opponentName = sc.nextLine();
                System.out.println("Calculating... ");
                Simulator.simulate(playerName, opponentName);


            } else if(usingNameString.equalsIgnoreCase("s")){
                System.out.println("Feature not completed");
            }
            else{
                System.out.println("Please select one of the two options");
            }
        }
    }

    public static boolean validate(String key) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 25000);
            DataOutputStream out =
                    new DataOutputStream(socket.getOutputStream());
            out.writeUTF(key);
            out.flush();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String result = in.readUTF();
            System.out.println("Result: " + result);

            out.close();
            in.close();
            socket.close();

        }catch(IOException e){
            return false;
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                return false;
            }
        }
        return true;

    }
}
