package Console;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation extends Patient {

    //localDateTime stores both date and time unlike Local date which stores only the date
    private int No;
    private LocalDate Consultation_Date;
    private LocalTime consultation_Start_Time;
    private LocalTime consultation_End_Time;
    private String doctor_consultation_ID;
    private String Additional_Note;
    private String Security_Key;
    private double cost;




    public Consultation(int No, String name, String surname, LocalDate dateOfBirth, String phoneno, int patId, String doctor_consultation_ID,
                        LocalTime consultation_Start_Time, LocalTime consultation_End_Time, LocalDate Consultation_Date, String Additional_Note, double cost, String Security_Key) {
        super(name,surname,dateOfBirth,phoneno,patId);
        this.No =No;
        this.Consultation_Date = Consultation_Date;
        this.consultation_Start_Time = consultation_Start_Time;
        this.consultation_End_Time = consultation_End_Time;
        this.doctor_consultation_ID = doctor_consultation_ID;
        this.Additional_Note = Additional_Note;
        this.cost=cost;
        this.Security_Key =Security_Key;
    }

    //getters and setters
    //consultation date

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        this.No = no;
    }

    public LocalDate getConsultation_Date() {
        return Consultation_Date;
    }

    public void setConsultation_Date(LocalDate consultation_Date) {
        this.Consultation_Date = consultation_Date;
    }
    //consultation time
    public LocalTime getConsultation_Start_Time() {
        return consultation_Start_Time;
    }

    public void setConsultation_Start_Time(LocalTime consultation_Start_Time) {
        this.consultation_Start_Time = consultation_Start_Time;
    }
    //consultation slot
    public String getDoctor_consultation_ID() {
        return doctor_consultation_ID;
    }

    public void setDoctor_consultation_ID(String doctor_consultation_ID) {
        this.doctor_consultation_ID = doctor_consultation_ID;
    }
    //consultation note
    public String getAdditional_Note() {
        return Additional_Note;
    }

    public void setAdditional_Note(String additional_Note) {
        this.Additional_Note = additional_Note;
    }

    public void setConsultation_End_Time(LocalTime consultation_End_Time) {
        this.consultation_End_Time = consultation_End_Time;
    }

    public LocalTime getConsultation_End_Time() {
        return consultation_End_Time;
    }

    public double getCost() {return cost;}

    public String getSecurity_Key() {
        return Security_Key;
    }

    public void setSecurity_Key(String security_Key) {
        this.Security_Key = security_Key;
    }

    @Override
    public String toString() {
        return  "01.Name                : " + this.getName() + '\n' +
                "02.SurName             : " + this.getSurname() + '\n' +
                "03.Date-of-Birth       : " + this.getDateOfBirth() + '\n' +
                "04.Mobile-No           : " + this.getMobileNo() + '\n' +
                "05.Patient-ID          : " + this.getPatientId() + '\n' +
                "06.Consultation-Date   : " + Consultation_Date +'\n'+
                "07.Start-Time          : " + consultation_Start_Time +'\n'+
                "08.End-Time            : " + consultation_End_Time +'\n'+
                "09.Consulted-Doctor    : " + doctor_consultation_ID +'\n'+
                "10.Cost-for-Consulation: " + cost+'\n'+
                "11.Additional-Note  : \n" + Additional_Note +'\n';
    }
}
