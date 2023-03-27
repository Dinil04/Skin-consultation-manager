package GUI;

import Console.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Console.WestminsterSkinConsultationManager.doctorArrayList;

public class Frame2 extends GUI_frame implements ActionListener {

    private JButton back;
    private JButton reset;
    private JButton sort;
 

    public  Frame2( boolean cat){
        getContentPane().setBackground(new Color(237,192,158));
        JLabel topic = new JLabel();
        //Adding the heading
        topic.setText("ALL DOCTORS DETAILS");
        topic.setBounds(290,20,550,20);
        topic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
        topic.setForeground(new Color(17, 17, 17));

        //create the doctor detail table
        JLabel colum = new JLabel();
        colum.setText("|    Name     |   SurName   |   Birthday    |   Phone-NO   |   Licence   |Specialisation|");
        colum.setBounds(33,-70,850,300);
        colum.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));

        String[] colom = {"Name","SurName","Birthday","Phone-NO","Licence","Specialisation"};
        DefaultTableModel tableModel = new DefaultTableModel(colom,0);
        JTable table = new JTable(tableModel);
        table.setBounds(35,100,800,300);
       if (cat){
           for (Console.Doctor doctor : doctorArrayList) {
               String[] details = {doctor.getName(), doctor.getSurname(), String.valueOf(doctor.getDateOfBirth()), doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation()};
               tableModel.addRow(details);
           }
       }else{
           int i = doctorArrayList.size();
           String [] sort = new String[i];
           for (int j = 0; j < i;j++) {
               sort[j]= doctorArrayList.get(j).getName();
           }
           for (int k = 0 ; k < sort.length ;k++) {
               for(int l =k+1 ;l<sort.length; l++ ){
                  if(sort[k].compareTo(sort[l])>0){
                      String tempery = sort[k];
                      sort[k] = sort[l];
                      sort[l]=tempery;
                  }
               }
           }for (String s : sort) {
               for (Doctor doctor : doctorArrayList) {
                   if (s.equals(doctor.getName())) {
                       String[] details = {doctor.getName(), doctor.getSurname(), String.valueOf(doctor.getDateOfBirth()),doctor.getMobileNo(), doctor.getMedicalLicence(), doctor.getSpecialisation()};
                       tableModel.addRow(details);
                   }
               }
           }
       }
        this.add(topic);
        this.add(colum);
        this.add(table);
        button();
        window("ALL DOCTOR DETAILS",900,500);
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
        if (e.getSource()==back) {
            this.dispose();
            //animus class call
            new GUI();
        } else if (e.getSource()==reset) {
            this.dispose();
            new Frame2(true);

        } else if (e.getSource()==sort){
            this.dispose();
            new Frame2(false);
        }
    }

    @Override
    //adding the buttons
    public void button() {
        back = new JButton();
        back.setFont(new Font("SansSerif", Font.BOLD, 12));
        back.setBackground(new Color(212,197,180));
        button_set(back,"Back",30,20,65,30);

        reset = new JButton();
        button_set(reset,"reset",40,410,65,30);
        reset.setBackground(new Color(212,197,180));

        sort = new JButton();
        button_set(sort,"sort",750,410,80,30);
        sort.setBackground(new Color(212,197,180));
    }
}
