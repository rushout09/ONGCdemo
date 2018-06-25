package ml.rushabh.rushapp;

public class Complain {
    public String Username;
    public String Email;
    public String Title;
    public String Department;
    public String ONGCId;
    public String Query;
    public Complain(){

    }
    public Complain(String Username,String Email,String Title, String Department,String ONGCId, String Query){
        this.Username = Username;
        this.Email = Email;
        this.Title = Title;
        this.Department = Department;
        this.ONGCId = ONGCId;
        this.Query = Query;
    }
}
