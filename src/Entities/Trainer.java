package Entities;

import Database.DatabaseConnection;
import Interfaces.CRUDInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Trainer implements CRUDInterface {
    private String name;
    private int id;
    private int age;
    private ArrayList<TrainedPokemon> pokemons;

    public Trainer(int id, String name, int age, ArrayList<TrainedPokemon> pokemons) {
        this.name = name;
        this.age = age;
        this.pokemons = pokemons;
        this.id = id;
    }

    public Trainer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Trainer() {
    }

    public Trainer(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public Trainer(String name, int age, ArrayList<TrainedPokemon> pokemons) {
        this.name = name;
        this.pokemons = pokemons;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<TrainedPokemon> getPokemons() {
        return pokemons;
    }

    public void create() {
        String createQuery = "INSERT INTO trainer(name, age)" +
                "VALUES('"+this.name+"', "+this.age+");";
        DatabaseConnection.executeQuery(createQuery);
        if(this.pokemons != null){
            TrainedPokemon.createTrainer_PokemonList(this,this.pokemons);
        }
    }

    public ArrayList read() {
        ArrayList trainersList = new ArrayList();
        try {
            String query = "SELECT * FROM trainer;";
            ResultSet rs = DatabaseConnection.selectValues(query);
            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                int age = rs.getInt("age");

                ArrayList pokemons = new ArrayList();
                pokemons = TrainedPokemon.selectTrainerPokemon(id);

                Trainer trainer = new Trainer(id, name, age, pokemons);
                trainersList.add(trainer);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return trainersList;
    }
    public Trainer selectOne(int id) {
        Trainer trainer = new Trainer();
        try {
            String query = "SELECT * FROM trainer where id = "+id+";";
            ResultSet rs = DatabaseConnection.selectValues(query);
            while (rs.next()) {
                String name = rs.getString("name");
                int id_result = rs.getInt("id");
                int age = rs.getInt("age");

                trainer = new Trainer(id_result, name, age, pokemons);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return trainer;
    }
    public void updateTrainer(int id, String name, int age) {
        String createQuery = "UPDATE trainer" +
                "    SET name = '"+name+"', age = "+age+
                "    WHERE id="+id+";";
        DatabaseConnection.executeQuery(createQuery);
    }

    public void delete(String id) {
        int id_delete = Integer.parseInt(id);
        String createQuery = "DELETE FROM trainer where id = "+id_delete+" ;";
        DatabaseConnection.executeQuery(createQuery);
    }
}
