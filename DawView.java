import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DawView extends JFrame {
    private JTextField textField11;
    private JTextField textField12;
    private Daw daw;
    private Kick kick;
    private Snare snare;
    private Hat hat;

    public DawView() {
        JLabel label1 = new JLabel("Добавить Сэмпл");
        JLabel label11 = new JLabel("Имя");
        JLabel label12 = new JLabel("Группа");
        textField11 = new JTextField("");
        textField12 = new JTextField("");
        JButton button1 = new JButton("Добавить");
        JTable table;
        JScrollPane scrollPane;
        Daw daw = new Daw();
        MyTable myTableModel;
        

        setSize(1400, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        Container p1 = new Container(); //контейнер для левой части окна
        add(p1);
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));


        p1.add(label1);
        p1.add(label11);
        textField11.setSize(80,30);
        p1.add(textField11);
        p1.add(label12);
        textField12.setSize(80,30);
        p1.add(textField12);
        p1.add(button1);


        Container p2=new Container();//контейнер для правой части окна
        p2.setLayout(new FlowLayout());
        add(p2);


        table=new JTable();
        table.setModel(myTableModel=new MyTable(daw));
        
        table.setAutoCreateRowSorter(true);
        scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(1000, 500));
        p2.add(scrollPane);
        setVisible(true);

        // button1.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         student=new Student(textField11.getText(),textField12.getText());
        //         myTableModel.addStudent(student);
        //     }
        // });
    }
}