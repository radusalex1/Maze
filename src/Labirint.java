import javax.swing.*;
import java.io.FileNotFoundException;

public class Labirint {

    private static void initUI() throws FileNotFoundException {
        JFrame f = new JFrame("Labirint");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.setSize(500,500);
        f.setVisible(true);
    }
    public static void main(String[] args)
    {
        System.out.println("Hello world");
        SwingUtilities.invokeLater(new Runnable() //new Thread()
        {
            public void run()
            {
                try {
                    initUI();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
