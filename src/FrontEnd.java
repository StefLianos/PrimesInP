import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FrontEnd extends JFrame {

    private static JFrame frame;
    private static JPanel north, center, south;
    private static Result result;
    private static JTextArea inputArea;
    private static JTextArea feed;
    private static JScrollPane scroll;
    private static String filePath = "";

    public static void makeWindow() {

        result = new Result("",false,0);


        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setTitle("Primes in P");

        setNorth();
        setCenter();
        setSouth();

        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void setNorth()
    {
        north = new JPanel(new BorderLayout());

        JPanel subNorth = new JPanel(new FlowLayout());
        inputArea = new JTextArea();
        inputArea.setRows(2);

        //make text area less shit looking

        JButton naiveStart = new JButton("Check using the naive method");
        naiveStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateText("computing ...");
                //if csv
                if (filePath != "")
                {
                    try {
                        ExecuteFromSCV.executeNaiveFromSCV(filePath);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    updateText("CSV file created with results");
                    filePath = "";
                }
                else
                {
                    result = Naive.runNaive(inputArea.getText());
                    updateText(result.getText() + "\r\n" + "Total execution time: " + result.getTimeString());
                }
            }
        });


        JButton fermatStart = new JButton("Check using Fermat's Little Theorem");
        fermatStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateText("computing ...");
                //if csv
                if (filePath != "")
                {
                    try {
                        ExecuteFromSCV.executeFermatFromSCV(filePath);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    updateText("CSV file created with results");
                    filePath = "";
                }
                else
                {
                    result = Fermat.runFermat(inputArea.getText());
                    updateText(result.getText() + "\r\n" + "Total execution time: " + result.getTimeString());
                }
            }
        });

        JButton aksStart = new JButton("Check using the AKS Test");
        aksStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateText("computing ...");
                //if csv
                if (filePath != "")
                {
                    try {
                        ExecuteFromSCV.executeAKSFromSCV(filePath);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    updateText("CSV file created with results");
                    filePath = "";
                }
                else
                {
                    result = AKS.runAKS(inputArea.getText());
                    updateText(result.getText() + "\r\n" + "Total execution time: " + result.getTimeString());
                }
            }
        });

        subNorth.add(naiveStart);
        subNorth.add(fermatStart);
        subNorth.add(aksStart);

        north.add(subNorth,BorderLayout.NORTH);
        north.add(inputArea,BorderLayout.SOUTH);

        JButton browse = new JButton("import CSV file");
        north.add(browse);

        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jc = new JFileChooser();
                int returnValue = jc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jc.getSelectedFile();
                    filePath = selectedFile.getAbsolutePath();
                }
                inputArea.setText(filePath);
            }
        });

        frame.add(north,BorderLayout.NORTH);
    }

    public static void setCenter()
    {
        center = new JPanel(new BorderLayout());
        feed = new JTextArea();
        feed.setEditable(false);
        feed.setLineWrap(true);
        scroll = new JScrollPane(feed);
        center.add(scroll, BorderLayout.CENTER);
        frame.add(center,BorderLayout.CENTER);
    }

    public static void setSouth()
    {
        south = new JPanel(new BorderLayout());
        JButton clear = new JButton("clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputArea.setText("");
                feed.setText("");
                filePath = "";
            }
        });


        south.add(clear,BorderLayout.CENTER);

        frame.add(south,BorderLayout.SOUTH);

    }

    public static void updateText(String txt)
    {
        feed.setText(txt);
        frame.update(frame.getGraphics());
    }
}
