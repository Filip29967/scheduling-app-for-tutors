package pl.kalendarz.kalendarz;


import java.time.LocalTime;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String dayOfWeek;
    private LocalTime time;

    public Student(int id, String firstName, String lastName, String dayOfWeek, LocalTime time) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
