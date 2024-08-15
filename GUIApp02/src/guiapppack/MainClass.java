package guiapppack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
class User{
    private String id;
    private String name;
    private  User next;
    public User(){
        id=name="";next=null;
    }
    public User(String id ,String name)
    {
        this.id=id;
        this.name=name;
        next=null;
    }
    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public User getNext()
    {
        return next;
    }
    public void setId(String id)
    {
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setNext(User next)
    {
        this.next=next;
    }
    
}
class MainPanel extends JPanel
{
    private JLabel lblId,lblName;
    private JTextField txtId,txtName;
    private JButton btnSubmit,btnCommit,btnShow,btnExit;
    private User start=null,end =null;
    private JLabel makelabel(String cap,int x,int y,int w,int h)
    {
        JLabel temp=new JLabel(cap);
        temp.setOpaque(true);
        temp.setBounds(x, y, w, h);
        temp.setBackground(Color.LIGHT_GRAY);
        temp.setFont(new Font("Courier New",1,24));
        add(temp); 
        return temp;
    }
    private JTextField makeTextField(int x,int y,int w, int h)
    {
         JTextField temp=new JTextField();
         temp.setFont(new Font("Courier New",1,20));
         temp.setHorizontalAlignment(JTextField.CENTER);
         temp.setBounds(x, y, w, h);
         add(temp);
         return temp;
    }
    private JButton makeButton(String cap,int x,int y,int w, int h)
    {
         JButton temp=new JButton(cap);
         temp.setFont(new Font("Verdana",1,14));
         temp.setBounds(x, y, w, h);
         temp.addActionListener(new ActionListener()
         {
             @Override
             public void actionPerformed(ActionEvent e)
             {
                 Object ob=e.getSource();
                 if(ob==btnSubmit)
                 {
                     User temp=new User(txtId.getText(),txtName.getText());
                     if(start==null)
                         start=temp;
                     else
                         end.setNext(temp);
                     end=temp;
                     JOptionPane.showMessageDialog(null,"User Registered successfully");
                     txtId.setText("");
                     txtName.setText("");
                     txtId.grabFocus();
                 }
                 else if(ob==btnCommit)
                 {
                     try{
                    if(start==null)
                        JOptionPane.showMessageDialog(null,"No Record found");
                    else
                    {
                    File file=new File("user.csv");
                    FileWriter fwrite=new FileWriter(file, false);
                    for(User ptr=start;ptr!=null;ptr=ptr.getNext())
                        fwrite.write(ptr.getId()+","+ptr.getName()+"\n");
                    fwrite.close();
                    JOptionPane.showMessageDialog(null,"Data commited succesfully");
                    }
                   }
                     catch(IOException ex){
                         JOptionPane.showMessageDialog(null,ex);
                     }
                     catch(Exception ex)
                     {
                         JOptionPane.showMessageDialog(null,ex);
                     }
                 }
                 else if(ob==btnShow)
                 {
                    if(start==null)
                       JOptionPane.showMessageDialog(null,"No Record Found");
                    else
                    {
                        int ctr=1;
                       System.out.println("---+-------+----------------");
                       System.out.println("ENO|USER ID|USER NAME");
                       System.out.println("---+-------+----------------");
                       for(User ptr=start;ptr!=null;ptr=ptr.getNext(),ctr++)
                          System.out.printf("%03d|%-7s|%-20s\n",ctr,ptr.getId(),ptr.getName());
                       System.out.println("---+-------+----------------");
                       System.out.println("    Total Users found :  "+(ctr-1));
                       System.out.println("---+-------+----------------");
                       
                    }
                     
                 }
                 else if(ob==btnExit)
                 {
                     System.exit(0);
                 }
             }
         });
         add(temp);
         return temp;
    }
    public MainPanel()
    {
        lblId=makelabel("Enter User ID", 10, 10, 250, 30);
        txtId=makeTextField(290,10,300,30);
        lblName=makelabel("Enter User Name", 10, 70, 250, 30);
        txtName=makeTextField(290,70,300,30);
        btnSubmit=makeButton("Submit",40,130,100,30);
        btnCommit=makeButton("Commit",180,130,100,30);
        btnShow=makeButton("Show",320,130,100,30);
        btnExit=makeButton("Exit",460,130,100,30);
    }
}
class MainFrame extends JFrame
{
    private MainPanel panel;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setBackground(Color.YELLOW);
        panel.setLayout(new BorderLayout());
        super.add(panel);   //
    }
}
public class MainClass
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setTitle("New User Registration");
        frame.setSize(650, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}