import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class About extends JFrame implements ActionListener {
    JPanel navbar;
    JLabel title;
    JButton home;
    JButton about;
    JButton logout;
    JLabel image;
    JButton orders;
    String u;
    String p;
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==logout){
            this.dispose();
            Login l = new Login();
        }
        else if (e.getSource()==home){
            this.dispose();
            Home h = new Home(u,p);
        }
        else if (e.getSource()==about){
            this.dispose();
            About a = new About(u,p);
        }
        else if (e.getSource()==orders){
            this.dispose();
            Orders o = new Orders(u,p);
        }
    }
    About(String s,String t){
        u = s;
        p = t;
        this.setLayout(null);
        this.setTitle("The Coffee Shop");
        ImageIcon i = new ImageIcon(getClass().getResource("logo.png"));
        this.setIconImage(i.getImage());
        this.getContentPane().setBackground(new Color(0xeeeeee));
        navbar = new JPanel();
        navbar.setBackground(new Color(0xffffff));
        navbar.setBounds(27,20,1480,50);
        title = new JLabel();
        title.setText("THE COFFEE SHOP.");
        title.setFont(new Font("Helvetica", Font.BOLD, 28));
        title.setBounds(20, 10, 400,30);
        home = new JButton();
        home.setBackground(new Color(0xffffff));
        home.setText("Home");
        home.setFont(new Font("Helvetica", Font.BOLD, 23));
        home.setBounds(650,10, 100,30);
        home.setBorder(null);
        home.setFocusable(false);
        home.addActionListener(this);
        about = new JButton();
        about.setBackground(new Color(0xffffff));
        about.setText("About");
        about.setFont(new Font("Helvetica", Font.BOLD, 23));
        about.setBounds(800,10, 100,30);
        about.setBorder(null);
        about.setFocusable(false);
        about.addActionListener(this);
        orders = new JButton();
        orders.setBackground(new Color(0xffffff));
        orders.setText("Orders");
        orders.setFont(new Font("Helvetica", Font.BOLD, 23));
        orders.setBounds(950,10, 100,30);
        orders.setBorder(null);
        orders.setFocusable(false);
        orders.addActionListener(this);
        logout = new JButton();
        logout.setBackground(new Color(0xffffff));
        logout.setText("Logout");
        logout.setFont(new Font("Helvetica", Font.BOLD, 23));
        logout.setBounds(1360,10, 100,30);
        logout.setBorder(null);
        logout.setFocusable(false);
        logout.addActionListener(this);
        navbar.setLayout(null);
        navbar.add(title);
        navbar.add(home);
        navbar.add(about);
        navbar.add(logout);
        navbar.add(orders);
        this.add(navbar);
        ImageIcon icon = new ImageIcon(getClass().getResource("about.png"));
        image = new JLabel();
        image.setIcon(icon);
        image.setBounds(0,0,1600,850);
        this.add(image);
        this.setSize(1670,850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
