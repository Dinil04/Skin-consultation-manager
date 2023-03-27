package Console;

import GUI.GUI;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{
    //Creating a array list
    public static  ArrayList<Doctor> doctorArrayList = new ArrayList<>();
    public static ArrayList <Consultation> consult = new ArrayList <>();
    public static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        WestminsterSkinConsultationManager west = new WestminsterSkinConsultationManager();
        west.loaddata();
        boolean start_1 = true;
        //Use the while loop
        while (start_1) {
            try {
                System.out.print("""
                           \n
                        ----------------------------------------------------------------------------------
                        -                                                                                -
                        -   +----------------------------------MENU----------------------------------+   -                                    
                        -   +                  (Dinil Hansindu - 20200516 - w1830180 )               +   -                
                        -   +------------------------------------------------------------------------+   -
                        -   +                                                                        +   - 
                        -   +          [1] Add a doctor                [5] Read the file             +   -   
                        -   +          [2] Delete a doctor             [6] Load the GUI              +   -
                        -   +          [3] Print the list of doctor    [7] Exit from system          +   -      
                        -   +          [4] Save all to a file                                        +   -
                        -   +------------------------------------------------------------------------+   -
                          What is your choice ?\s""");

                //Printing the menu & getting the user input for the menu
                //Used the switch and case to check the condition
                String input = scanner.next();
                switch (input) {
                    case "1" -> west.AddDoctor();
                    case "2" -> west.DeleteDoctor();
                    case "3" -> west.PrintListOfDoctors();
                    case "4" -> west.SaveFile();
                    case "5" -> west.ReadFile();
                    case "6" -> new GUI();
                    case "7" -> {
                        //Using a try catch
                        try {
                            //Patient details save to this file
                            BufferedWriter temp = new BufferedWriter(new FileWriter("src/patient.txt"));
                            for (Consultation con : consult) {
                                temp.write(con.getNo() + "\n" + con.getName() + "\n" + con.getSurname() + "\n" + con.getDateOfBirth() + "\n" + con.getMobileNo() + "\n" + con.getPatientId() + "\n" + con.getDoctor_consultation_ID() + "\n" + con.getConsultation_Start_Time() + "\n" + con.getConsultation_End_Time() + "\n" + con.getConsultation_Date() + "\n" + con.getAdditional_Note() + "\n" + con.getCost() + "\n" + con.getSecurity_Key() + "\n\n");
                            }
                            temp.close();
                        } catch (IOException e) {
                            System.out.println("Something Wrong !!!!! ");
                        }
                        start_1 = false;
                    }
                    //If you input the invalid number for above part after print this
                    default -> System.out.println("\nInvalid input.Please input the valid number.\n");
                }
                //Methods erros
            }catch(Exception e){
                System.out.println("Invalid input");
            }
        }
    }
//All the doctor detail
    public String All_details() {
        String run = "Stop";
        //Using for loop and if else conditions
        for (int i = 0; i < doctorArrayList.size(); i++) {
            if (1 == (i + 1)) {
                //Use the "print" to print the table
                System.out.println("""
                                        
                        --------------------------------------------------------------------
                        +                        1st DOCTOR'S DETAILS                      +
                        --------------------------------------------------------------------
                                        
                        """);
                System.out.println(doctorArrayList.get(i));
                run = "start";
            } else if (2 == (i + 1)) {
                System.out.println("""
                                        
                        --------------------------------------------------------------------
                        +                        2nd DOCTOR'S DETAILS                      +
                        --------------------------------------------------------------------
                                        
                        """);
                System.out.println(doctorArrayList.get(i));
                run = "start";
            } else if (3 == (i + 1)) {
                System.out.println("""
                                        
                        --------------------------------------------------------------------
                        +                        3rd DOCTOR'S DETAILS                      +
                        --------------------------------------------------------------------
                                        
                        """);
                System.out.println(doctorArrayList.get(i));
                run = "start";

            } else {
                System.out.println("""
                                                  
                        --------------------------------------------------------------------
                                    \t\t\t""" + (i + 1) + "th DOCTOR'S DETAILS\n" + """   
                        --------------------------------------------------------------------
                         
                        """);
                System.out.println(doctorArrayList.get(i));
                run = "start";
            }
        }
        return run;
    }
    @Override
    //Add doctor
    public void AddDoctor() {
        boolean Duplicate = true;
        String regex = "^[A-Za-z]\\w{2,29}$";
        Pattern p = Pattern.compile(regex);
        //Using for loop,if else conditions for this part
        if(10> doctorArrayList.size()) {
            System.out.println("------------------------ADD DOCTORS------------------------");
            System.out.print("Enter first name : ");
            String name = scanner.next().trim();
            System.out.print("Enter surname : ");
            String surname = scanner.next().trim();
            Matcher f = p.matcher(name);
            Matcher s = p.matcher(surname);
            if(f.matches() && s.matches()){
                System.out.print("Enter date of birth(YYYY-MM-DD) : ");
                LocalDate dateOfBirth = LocalDate.parse(scanner.next());
                System.out.print("Enter mobile number: ");
                String mobile = scanner.next().trim();
                // Returns a validate mobile number having 10 numbers
                if ( 10 == mobile.length()){
                    Integer.parseInt(mobile);
                    System.out.print("Enter the licence : ");
                    String licence = scanner.next().trim();
                    for (Doctor doctor : doctorArrayList) {
                        if (licence.equals(doctor.getMedicalLicence())) {
                            System.out.println("\n-Medical licence already exists-");
                            System.out.println("-Please enter the correct details-\n");
                            Duplicate = false;
                            break;
                        }
                    }
                    if (Duplicate) {
                        System.out.print("Specialisation : ");
                        String spec = scanner.next().trim();

                        doctorArrayList.add(new Doctor(name, surname, dateOfBirth, mobile, licence, spec));
                    }
                }else{
                    System.out.println("Check entered phone number!!!");
                }
            }else {
                System.out.println("Check entered  First name or Sure name !!!!");
            }
        }
    }
    @Override
    //Delete doctor detail
    public void DeleteDoctor() {
        //Use the "print" to print the delete table
        System.out.print("""
                -----------------------------------------------------------------------------------------
                +                                     DELETE DOCTORS                                    +
                +                           (All doctors details can see bellow)   \s                   +
                -----------------------------------------------------------------------------------------
                """);
        //using for,if else conditions
        if(All_details().equals("start")){
            System.out.print("Enter the medical Licence that what you want remove : ");
            String delete = scanner.next().trim();
            for(int i = 0; i < doctorArrayList.size() ; i++){
                if (delete.equals(doctorArrayList.get(i).getMedicalLicence())){
                    System.out.print("""
                            -----------------------------------------------------------------------------------------
                            +                                DELETED DOCTOR'S DETAILS                               +
                            -----------------------------------------------------------------------------------------
                            """);
                    System.out.println(doctorArrayList.get(i));
                    doctorArrayList.remove(i);
                    break;
                }else{
                    //If you enter the wrong licence after using this part
                    if(i == doctorArrayList.size()-1){
                        System.out.println("Not exist any doctor using this licence ");
                    }
                }
            }

        }else{ System.out.println("Can't find any doctor details.");}
    }

    @Override
    //Print the all doctor details
     public void PrintListOfDoctors() {
        //Use the "print" to print the doctor detail table
        System.out.print("""
                -----------------------------------------------------------------------------------------
                +                                    ALL DOCTOR'S DETAILS                               +
                -----------------------------------------------------------------------------------------
                """);
        if(All_details().equals("Stop")){
            System.out.println("Can't find any doctor details to find.");
        }
    }

    @Override
    //doctor detail save this file
    public void SaveFile() {
        try {
            BufferedWriter writer  = new BufferedWriter(new FileWriter("src/doctor.txt"));
            BufferedWriter temp  = new BufferedWriter(new FileWriter("src/cache.txt"));
            //using for,if else conditions
            for (Doctor doctor : doctorArrayList) {
                temp.write(doctor.getName() + "\n" + doctor.getSurname() + "\n" + doctor.getDateOfBirth() + "\n" + doctor.getMobileNo() + "\n" + doctor.getMedicalLicence() + "\n" + doctor.getSpecialisation() + "\n\n");
            }
            for(int i = 0; i< doctorArrayList.size(); i++){
                if(1 == (i+1)){
                    writer.write("""
                
                --------------------------------------------------------------------
                +                        1st DOCTOR'S DETAILS                      +
                --------------------------------------------------------------------
                
                """);
                    writer.write (String.valueOf(doctorArrayList.get(i)));
                } else if (2 == (i+1)) {
                    writer.write("""
                
                --------------------------------------------------------------------
                +                        2nd DOCTOR'S DETAILS                      +
                --------------------------------------------------------------------
                
                """);
                    writer.write (String.valueOf(doctorArrayList.get(i)));
                } else if (3 == (i+1)) {
                    writer.write("""
                
                --------------------------------------------------------------------
                +                        3rd DOCTOR'S DETAILS                      +
                --------------------------------------------------------------------
                
                """);
                    writer.write (String.valueOf(doctorArrayList.get(i)));

                }else {
                    writer.write("""
                          
                          --------------------------------------------------------------------
                                       \t\t\t"""+(i+1)+"th DOCTOR'S DETAILS\n"+ """
                         --------------------------------------------------------------------
                          
                         """);
                    writer.write (String.valueOf(doctorArrayList.get(i)));
                }
            }
            System.out.println("Successfully store data in 'src/doctor.txt' file ");
            writer.close();
            temp.close();
        }catch (IOException e){
            System.out.println("Invalid !!!!! ");
        }
    }
    @Override
    //Read the file
    public void ReadFile() {
        try {
            String line ;
            BufferedReader reader  = new BufferedReader(new FileReader("src/doctor.txt"));
            while ((line= reader.readLine()) != null){
                System.out.println(line);
            }
            reader.close();
        }catch (IOException e){
            System.out.println("Invalid !!!!! ");
        }
    }
    @Override
    //Load the all data
    public void loaddata() {
            try {
                String temperated;
                //create a tempary array
                ArrayList<String> tempArray = new ArrayList<>();
                BufferedReader tempre = new BufferedReader(new FileReader("src/cache.txt"));
                while ((temperated = tempre.readLine()) != null) {
                    if (temperated.equals("")) {
                        continue;
                    } else {
                        tempArray.add(temperated);}
                }
                if(tempArray.size()==0){
                    System.out.println("\nNOTHING OLD FOUND!!!!! ");
                }
                boolean start = true;
                while(start) {
                    if(tempArray.size()==0){
                        start = false;
                    }else{
                        doctorArrayList.add(new Doctor(tempArray.get(0), tempArray.get(1), LocalDate.parse(tempArray.get(2)), tempArray.get(3), tempArray.get(4), tempArray.get(5)));
                        tempArray.subList(0, 6).clear();}
                }
            } catch (Exception e) {
                System.out.println("Invalid!!!!! ");
            }
        }
    }