package GUI;

import Console.Consultation;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Console.WestminsterSkinConsultationManager.consult;
import static Console.WestminsterSkinConsultationManager.doctorArrayList;

public class Frame1 extends GUI_frame implements ActionListener {

    private String Patient_Name;
    private String Patient_Surname;
    private String Phone_Number;
    private String Doctor_Consultation_ID;
    private String Additional_Note;
    private String End_Hours;
    private String Security_Key;


    private int patId;
    private int again = 1;
    private LocalDate dateOfBirth, conDate;
    private LocalTime consulStart,consulEnd;
    private JButton Submit_button;
    private JButton Picture;
    private final JTextField getname;
    private final JTextField getbirthday;
    private final JTextField getid;
    private final JTextField getdate;
    private final JTextField getsurname;
    private final JTextField getphone;
    private final JComboBox getdoc;
    private final JComboBox getstarttime1;
    private final JComboBox getstarttime2;
    private final JComboBox getdura;
    private final JTextArea getnote;
    private final JLabel addphopath_label;

    Frame1(){
        // Adding an image for the background
        ImageIcon img = new ImageIcon("src/GUI/ico.jpg");
        JLabel topic_label = new JLabel();
        topic_label.setText("ALL DOCTORS DETAILS");
        set_lable(topic_label,290,20,550,20,20);

        //lables for the doctor detail colomns
        JLabel colum_label = new JLabel();
        colum_label.setText("|     Name     |    SurName    |   Phone-No   |   Licence    | Specialisation |");
        colum_label.setForeground(new Color(248, 176, 4, 255));
        set_lable(colum_label,39, -70, 750, 300,15);

        DefaultTableModel tableModel = new DefaultTableModel(0,5);
        JTable table = new JTable(tableModel);
        table.setBounds(40, 100, 700, 240);

        for (int i =0; doctorArrayList.size()>i;i++) {
            String[] details = {doctorArrayList.get(i).getName(), doctorArrayList.get(i).getSurname(), doctorArrayList.get(i).getMobileNo(), doctorArrayList.get(i).getMedicalLicence(), doctorArrayList.get(i).getSpecialisation()};
            tableModel.addRow(details);
        }

        //lables for the doctor consultations
        JLabel topic1_label = new JLabel();
        topic1_label.setText("DOCTOR CONSULTATION");
        set_lable(topic1_label,280, 360, 550, 20,20);

        JLabel jname_label = new JLabel();
        jname_label.setText("NAME                 :                    SURNAME           :");
        set_lable(jname_label,30, 400, 650, 20,15);

        getname = new JTextField();
        set_textfild(getname,240,400,150,20);

        getsurname = new JTextField();
        set_textfild(getsurname,600,400,150,20);

        JLabel birthday_label = new JLabel();
        birthday_label.setText("BIRTHDAY(YYY-MM-DD)  :                    PHONE NUMBER      :");
        set_lable(birthday_label,30, 450, 550, 20,15);

        getbirthday = new JTextField();
        set_textfild(getbirthday,240,450,150,20);

        getphone = new JTextField();
        set_textfild(getphone,600,450,150,20);

        JLabel id_label = new JLabel();
        id_label.setText("PATIENT ID           :                    DOCTOR LICENCE ID :");
        set_lable(id_label,30, 495, 550, 20,15);

        getid = new JTextField();
        set_textfild(getid,240,495,150,20);

        String [] doc = new String[doctorArrayList.size()];
        for(int i = 0;doctorArrayList.size()>i;i++){
            doc[i]=doctorArrayList.get(i).getMedicalLicence();
        }
        getdoc =new JComboBox(doc);
        set_combobox(getdoc,600,495,150,20);

        JLabel time_label = new JLabel();
        time_label.setText("CONSULTATION START TIME:                 CONSULTATION DURATION:");
        set_lable(time_label,30, 545, 700, 20,15);

        String[] hours = { "HH","08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20","21","22" };
        String[] min = { "MM","00", "15", "30", "45" };
        String[] dura = { "HH","01", "02", "03", "04", "05"};

        //J combo box
        getstarttime1 =new JComboBox(hours);
        set_combobox(getstarttime1,260,545,50,20);

        getstarttime2 =new JComboBox(min);
        set_combobox(getstarttime2,340,545,50,20);

        getdura =new JComboBox(dura);
        set_combobox(getdura,600,545,150,20);

        JLabel date_label = new JLabel();
        date_label.setText("CONSULTATION DATE(YYYY-MM-DD):");
        set_lable(date_label,195, 595, 550, 20,15);

        getdate = new JTextField();
        set_textfild(getdate,490,595,150,20);

        JLabel jnote_label = new JLabel();
        jnote_label.setText("ADDITIONAL NOTE :");
        set_lable(jnote_label,800, 100, 550, 20,15);

        getnote = new JTextArea();
        getnote.setText(".");
        getnote.setBounds(800,130,250,100);
        getnote.setFont(new Font("console",Font.ITALIC,15));
        getnote.setLineWrap(true);

        addphopath_label = new JLabel();
        addphopath_label.setBounds(800, 300, 250, 30);
        addphopath_label.setBackground(Color.white);
        addphopath_label.setOpaque(true);

        JLabel rat = new JLabel();
        rat.setIcon(img);
        rat.setBounds(0,0,1200,700);
        rat.setOpaque(true);

        this.add(table);
        this.add(getnote);
        this.add(addphopath_label);

        button();
        this.add(rat);
        window("CONSULTATION",1200,700);
    }
    public void check_equal(){
        int timedura = Integer.parseInt(End_Hours);
        boolean not_equal = true;
        for (Consultation consultation : consult) {
            if (Doctor_Consultation_ID.equals(consultation.getDoctor_consultation_ID())) {
                if (consultation.getConsultation_Date().isEqual(conDate)) {
                    if(consultation.getConsultation_Start_Time().isBefore(consulStart) && consultation.getConsultation_End_Time().isAfter(consulStart) ||
                            consultation.getConsultation_Start_Time().isBefore(consulEnd) && consultation.getConsultation_End_Time().isAfter(consulEnd) ||
                            consultation.getConsultation_Start_Time().isBefore(consulStart) && consultation.getConsultation_End_Time().isAfter(consulEnd) ||
                            consultation.getConsultation_Start_Time().isAfter(consulStart) && consultation.getConsultation_End_Time().isBefore(consulEnd) )
                    {
                        not_equal = false;
                        break;
                    }
                }
            }
        }
        for(Consultation consultation : consult){
            if(Objects.equals(consultation.getPatientId(), patId)){
                again++;
            }
        }
        int cost;
        if(again==1){
            cost =(timedura*15);


        }else{
            cost = 25*timedura;

        }
        if(not_equal){

            consult.add(new Consultation(again, Patient_Name, Patient_Surname, dateOfBirth, Phone_Number,patId, Doctor_Consultation_ID, consulStart,consulEnd, conDate, Additional_Note, cost, Security_Key));
        }else{
            int docsiz=doctorArrayList.size();
            String [] random = new String[docsiz];
            for(int i =0;docsiz > i ;i++){
                random[i] = doctorArrayList.get(i).getMedicalLicence();
            }
            Random rand = new Random();
            int randomIndex = rand.nextInt(random.length);
            Doctor_Consultation_ID = random[randomIndex];
            again = 0 ;
            check_equal();
        }
    }
    public void set_combobox(JComboBox combo,int x, int y , int width , int height){
        combo.setBounds(x,y,width,height);
        combo.setBackground(new Color(0xFFFFFF));
        this.add(combo);
    }
    public void set_lable(JLabel lable ,int x, int y , int width , int height,int font){
        lable.setBounds(x,y,width,height);
        lable.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,font));
        this.add(lable);
    }

    public void set_textfild(JTextField textfild,int x, int y , int width , int height){//150,20
        textfild.setBounds(x,y,width,height);
        textfild.setFont(new Font("console",Font.ITALIC,15));
        this.add(textfild);
    }
    public void button_set(JButton but,String name, int x, int y , int width , int height) {
        but.setBounds(x,y,width,height);
        but.setText(name);
        but.setFocusable(false);
        but.addActionListener(this);
        this.add(but);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== Submit_button) {
            try {
                String regex = "^[A-Za-z]\\w{2,29}$";
                Pattern p = Pattern.compile(regex);
                Patient_Name = getname.getText().trim();
                Patient_Surname = getsurname.getText().trim();
                Matcher f = p.matcher(Patient_Name);
                Matcher s = p.matcher(Patient_Surname);
                if (f.matches() && s.matches()) {
                    try {
                        dateOfBirth = LocalDate.parse(getbirthday.getText().trim());
                        Phone_Number = getphone.getText().trim();
                        // validation for mobilenumber
                        if (10 == Phone_Number.length()) {
                            try {
                                Integer.parseInt(Phone_Number);
                                try {
                                    patId = Integer.parseInt(getid.getText());
                                    Doctor_Consultation_ID = (String) getdoc.getSelectedItem();
                                    String start_Hours = (String) getstarttime1.getSelectedItem();
                                    String start_Min = (String) getstarttime2.getSelectedItem();
                                    String start_Time = start_Hours + ":" + start_Min + ":00";
                                    try {
                                        consulStart = LocalTime.parse(start_Time);
                                        End_Hours = (String) getdura.getSelectedItem();
                                        consulEnd = consulStart.plusHours(Long.parseLong(End_Hours));
                                        System.out.println(consulEnd);
                                        try {
                                            conDate = LocalDate.parse(getdate.getText().trim());
                                            System.out.println("cat");
                                            if (conDate.isAfter(LocalDate.now()) && conDate.isBefore(LocalDate.now().plusYears(3))) {
                                                Additional_Note = getnote.getText();
                                                try {


                                                    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
                                                    SecretKey myDesKey = keygenerator.generateKey();

                                                    // Creating object of Cipher
                                                    Cipher desCipher;
                                                    desCipher = Cipher.getInstance("DES");


                                                    // Creating byte array to store string
                                                    byte[] text = Additional_Note.getBytes(StandardCharsets.UTF_8);

                                                    // Encrypting text
                                                    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
                                                    byte[] textEncrypted = desCipher.doFinal(text);

                                                    // Converting encrypted byte array to string
                                                    Additional_Note =  Base64.getEncoder().encodeToString(textEncrypted);
                                                    System.out.println(Additional_Note);

                                                    //// covert security key

                                                    Security_Key = Base64.getEncoder().encodeToString(myDesKey.getEncoded());
                                                    System.out.println(Security_Key);

//                                                    // covert  string to security
//                                                    byte[] encodedKey = Base64.getDecoder().decode(notenkey);
//                                                    notekey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "DES");
//                                                    System.out.println(notekey);
//
//                                                    /////
//
//                                                    /// Decrypting text
//                                                    byte[] output =Base64.getDecoder().decode(note);
//                                                    desCipher.init(Cipher.DECRYPT_MODE, notekey);
//                                                    byte[] textDecrypted1 = desCipher.doFinal(output);
//
//                                                    // Converting decrypted byte array to string
//                                                    String u = new String(textDecrypted1);
//                                                    System.out.println(u);


                                                    try
                                                    {

                                                        desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

                                                        CipherInputStream cipt=new CipherInputStream(new FileInputStream("ico.jpg"), desCipher);

                                                        FileOutputStream fileip=new FileOutputStream("encrypt.jpg");

                                                        int i;
                                                        while((i=cipt.read())!=-1)
                                                        {
                                                            fileip.write(i);

                                                        }
                                                        fileip.close();
                                                    }
                                                    catch(Exception Ignore)
                                                    {
                                                    }

                                                    check_equal();
                                                    new Frame1pop();
                                                    this.dispose();
                                                } catch (Exception ignored) {
                                                    check_equal();
                                                    new Frame1pop();
                                                    this.dispose();
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Check You Entered Consultation Date", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                            ///validate cousulDAte
                                        } catch (Exception ignored) {
                                            JOptionPane.showMessageDialog(null, "Check You Entered Date!!", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } catch (Exception ignored) {
                                        JOptionPane.showMessageDialog(null, "Check You Entered Time and Duration!!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (Exception ignored) {
                                    JOptionPane.showMessageDialog(null, "Invalid Patient ID Number. Please check it.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }catch (Exception ignored){
                                JOptionPane.showMessageDialog(null, "Invalid Mobile Number.Please check it", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Check Your Phone Number!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }catch (Exception ignored){
                        JOptionPane.showMessageDialog(null, "Check Your Birthday!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "Check Your First Name And Surname!!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (Exception ignored){
                JOptionPane.showMessageDialog(null,"SOMETHING WRONG!!","Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource()== Picture){
            try {

                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                String filename = f.getAbsolutePath();
                addphopath_label.setText(filename);
                ImageIcon MyImage = new ImageIcon(filename);
               // Image img = MyImage.getImage();
            }catch(Exception ignored){}
        }else{
            this.dispose();
            new GUI();
        }
    }

    @Override
    //Buttons
    public void button() {
        JButton back_button = new JButton();
        back_button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button_set(back_button,"Back",30,20,65,30);
        back_button.setBackground(new Color(212,197,180));

        Picture = new JButton();
        button_set(Picture,"Pic",1080, 300, 80, 30);
        Picture.setBackground(new Color(212,197,180));

        Submit_button = new JButton();
        button_set(Submit_button,"Submit",1050,550,80,30);
        Submit_button.setBackground(new Color(212,197,180));

        JButton cancel_button = new JButton();
        button_set(cancel_button,"Cancel",850,550,80,30);
        cancel_button.setBackground(new Color(212,197,180));
    }
}
