package Entities;

import Exceptions.PokemonNameException;
import Interfaces.CRUDInterface;
import Database.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pokemon implements CRUDInterface {
    private String name;
    private String habitat;
    private float weight;
    private boolean isAggressive;

    public Pokemon(String name, String habitat, float weight, boolean isAggressive) throws PokemonNameException {
        if(!validate(name))
            throw new PokemonNameException(name);
        this.name = name;
        this.habitat = habitat;
        this.weight = weight;
        this.isAggressive = isAggressive;
    }
    public boolean validate(String name){
        if(name.matches(".*\\d.*")){
            return false;
        }
        return true;
    }
    public Pokemon(){}

    public String getName() {
        return name;
    }


    public String getHabitat() {
        return habitat;
    }


    public float getWeight() {
        return weight;
    }


    public boolean isAggressive() {
        return isAggressive;
    }

    public void create() {
        String createQuery = "INSERT INTO pokemon(name, habitat, weight, isAggressive)" +
                "VALUES('"+this.name+"', '"+this.habitat+"', "+this.weight+", "+this.isAggressive+");";
        DatabaseConnection.executeQuery(createQuery);
    }

    public ArrayList read() {
        ArrayList<Pokemon> pokemons = new ArrayList();
        try {
            String query = "SELECT * FROM pokemon;";
            ResultSet rs = DatabaseConnection.selectValues(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String habitat = rs.getString("habitat");
                float weight = rs.getFloat("weight");
                boolean isAggressive = rs.getBoolean("isAggressive");

                Pokemon pokemon = new Pokemon(name,habitat,weight,isAggressive);
                pokemons.add(pokemon);
            }

            return pokemons;

        } catch (SQLException | PokemonNameException e){
            e.printStackTrace();
        }
        return pokemons;
    }
    public void updatePokemon(String name, float weight, String habitat, boolean isAggressive) {

        String createQuery = "UPDATE pokemon" +
                "    SET habitat = '"+habitat+"', weight = "+weight+", isAggressive = "+isAggressive+"" +
                "    WHERE name='"+name+"';";
        DatabaseConnection.executeQuery(createQuery);
    }
    public Pokemon selectOne(String name){
        Pokemon pokemon = null;
        try {
            String query = "SELECT * FROM pokemon where name ='"+name+"';";
            ResultSet rs = DatabaseConnection.selectValues(query);
            while (rs.next()) {
                String namePokemon = rs.getString("name");
                String habitat = rs.getString("habitat");
                float weight = rs.getFloat("weight");
                boolean isAggressive = rs.getBoolean("isAggressive");

                pokemon = new Pokemon(namePokemon,habitat,weight,isAggressive);
            }

            return pokemon;
        } catch (SQLException | PokemonNameException e){
            e.printStackTrace();
        }
        return pokemon;
    }

    public void delete(String name) {
        String createQuery = "DELETE FROM pokemon where name = '"+name+"' ;";
        DatabaseConnection.executeQuery(createQuery);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", habitat='" + habitat + '\'' +
                ", weight=" + weight +
                ", isAggressive=" + isAggressive +
                '}';
    }
}
