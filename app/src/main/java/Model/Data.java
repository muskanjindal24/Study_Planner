package Model;

public class Data {

    private String subname;
    private String note;
    private String date;
    private String id;

    public Data(String subname, String note, String date, String id) {
        this.subname = subname;
        this.note = note;
        this.date = date;
        this.id = id;
    }
    public String getsubname() {
        return subname;
    }

    public void setsubname(String subname) {
        this.subname = subname;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Data(){

    }
}
