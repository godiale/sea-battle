package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemConsoleIO implements IConsoleIO {

    @Override
    public String readLine(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                return bufferedReader.readLine();
            }
            catch (IOException e) {
                System.out.println("Error occured (will retry): " + e);
            }
        }
    }

    @Override
    public void printLine(String line) {
        System.out.println(line);
    }
}
