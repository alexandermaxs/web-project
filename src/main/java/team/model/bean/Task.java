package team.model.bean;

public class Task {
    private int id;
    private int projectId;
    private String info = "";
    private String date = "";
    private int number = 0;
    private String cipher = "";

    public Task() {
    }

    public Task(int id, int projectId, String info, String date, int number, String cipher) {
        this.id = id;
        this.projectId = projectId;
        this.info = info;
        this.date = date;
        this.number = number;
        this.cipher = cipher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }
}
