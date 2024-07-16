/**
 * @author Adam Czekalski
 * <p>
 * dir /b /s *.java > sources.txt <- compile classpaths to txt file
 * javac -d bin --module-path (module path) @sources.txt <- compile
 * jar cvf lab05pop.jar -C bin . <- create jar
 * java -p (module path) -m pl.edu.pwr.aczekalski.lab05/pl.edu.pwr.aczekalski.lab05.MainFrame <- launch application
 */
package pl.edu.pwr.aczekalski.lab05;

import javax.swing.*;

public class MainFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserInterface userInterface = new UserInterface();
                if (!userInterface.chooseParameters.isVisible()) { //if JDialog disappears, animation begins
                    userInterface.startT();
                }
            }
        });
    }
}
