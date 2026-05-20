package pl.kalendarz.kalendarz;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataBaseMen {
    String url;
    public String getUrl(){
        return url;
    }
    private void setUrl(String url){
        this.url = url;
    }

    public DataBaseMen(){

        setUrl("jdbc:sqlite:listaUczniow.db");
    }
    public Connection getConnection(){
        Connection dataBaseLink = null;
        try{
            dataBaseLink = DriverManager.getConnection(getUrl());
            System.out.println("Connected!");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return dataBaseLink;
    }
    public void deleteTable() throws SQLException{
        String query = "DROP TABLE IF EXISTS uczniowie;";
        Connection cone = getConnection();
        Statement sta = cone.createStatement();
        sta.execute(query);
    }
    public void deleteStudent(int id){
        String querry = "DELETE FROM uczniowie WHERE id = ?";
        try(Connection con = getConnection(); PreparedStatement stm = con.prepareStatement(querry)) {
            stm.setInt(1,id);
            stm.executeUpdate();
            System.out.println("Usunięto rekord");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void createTable() throws SQLException {
        String stworzTabele = "CREATE TABLE IF NOT EXISTS uczniowie(" +
                "id INTEGER PRIMARY KEY,"+
                "Imie TEXT NOT NULL, " +
                "Nazwisko TEXT NOT NULL," +
                "Dzien TEXT NOT NULL," +
                "Godzina TIME);";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(stworzTabele);
            System.out.println("Table creasted!");

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertUczniowie( String imie, String nazwisko, String dzien, LocalTime godzina){
        String addTable = "INSERT INTO uczniowie(Imie , Nazwisko , Dzien ,Godzina)" +
                "VALUES (?,?,?,?);";
        Connection connection = getConnection();
        try(PreparedStatement statement = connection.prepareStatement(addTable)){
            statement.setString(1,imie);
            statement.setString(2,nazwisko);
            statement.setString(3,dzien);
            statement.setString(4,godzina.toString());
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public List<Uczen> pobierzUczniow(){
        String query = "SELECT * FROM uczniowie;";
        List<Uczen> uczniowiee = null;
        try(Connection conect = getConnection();
        PreparedStatement statement = conect.prepareStatement(query)
        ){
            uczniowiee = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Uczen uczen = new Uczen(
                rs.getInt("id"),
                rs.getString("Imie"),
                rs.getString("Nazwisko"),
                rs.getString("Dzien"),
               LocalTime.parse(rs.getString("Godzina"))
                );
                uczniowiee.add(uczen);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return uczniowiee;
    }
}
