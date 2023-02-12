package ma.fstt.ihm;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login{
    public static void main(String[] args) {

        // هنا قمنا بإنشاء النافذة مع تحديد بعض خصائصها
        JFrame frame = new JFrame("Login");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // و وضعنا له حدود و عنوان Layout Manager لا يستخدم أي Panel هنا قمنا بتعريف
        JPanel panel = new JPanel(null);
        panel.setBorder(BorderFactory.createTitledBorder("LOGIN"));
        panel.setBounds(50, 30, 290, 300);

        // Frame في الـ Panel هنا وضعنا الـ
        frame.add(panel);

        // Panel هنا قمنا بتعريف جميع الأشياء التي سنضعها في الـ
        JLabel label1 = new JLabel("Username");
        JLabel label2 = new JLabel("Password");
        JLabel label5 = new JLabel("Role");
        JTextField field1 = new JTextField();
        JPasswordField password = new JPasswordField();
        JComboBox comboBox = new JComboBox(new String[]{ "Admin", "Client"});
        JButton button = new JButton("Submit");
        JButton button1 = new JButton("Create an account");

        // Panel هنا قمنا بتحديد مكان كل شيء سنضيفه بداخل الـ
        label1.setBounds(30, 90, 100, 25);
        label2.setBounds(30, 150, 100, 25);
        label5.setBounds(30, 210, 100, 25);
        field1.setBounds(120, 90, 130, 25);
        password.setBounds(120, 150, 130, 25);
        comboBox.setBounds(120, 210, 130, 25);
        button.setBounds(120, 255, 70, 25);
        button1.setBounds(200, 255, 70, 25);

        // Panel في الـ Buttons هنا قمنا بوضع الـ
        panel.add(label1);
        panel.add(label2);
        panel.add(label5);
        panel.add(field1);
        panel.add(password);
        panel.add(comboBox);
        panel.add(button);
        panel.add(button1);

        // مرئية Frame هنا جعلنا الـ
        frame.setVisible(true);

    }
}
