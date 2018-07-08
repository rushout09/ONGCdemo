package ml.rushabh.rushapp;

public class Complain {
    public String username;
    public String email;
    public String title;
    public String department;
    public String ongcid;
    public String query;
    public Complain(){

    }
    public Complain(String username,String email,String title, String department,String ongcid, String query){
        this.username = username;
        this.email = email;
        this.title = title;
        this.department = department;
        this.ongcid = ongcid;
        this.query = query;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getQuery() {
        return query;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public String getOngcid() {
        return ongcid;
    }

    public String getUsername() {
        return username;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setOngcid(String ongcid) {
        this.ongcid = ongcid;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
