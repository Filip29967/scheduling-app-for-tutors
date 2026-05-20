package pl.kalendarz.kalendarz;


import java.time.LocalTime;

public class Uczen {
     private String imie;
    public String getImie(){
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }
    private String nazwisko;

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    private String dzien;
    public String getDzien(){
        return dzien;
    }

    public void setDzienTygodnia(String dzienTygodnia) {
        this.dzien = dzienTygodnia;
    }
    private LocalTime godzina;

    public LocalTime getGodzina() {
        return godzina;
    }
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public void setGodzina(LocalTime godzina) {
        this.godzina = godzina;
    }
    public Uczen(int id,String imie,String nazwisko,String dzien,LocalTime godzina){
       this.id = id;
       this.imie = imie;
       this.nazwisko = nazwisko;
       this.dzien = dzien;
       this.godzina = godzina;
    }
}
