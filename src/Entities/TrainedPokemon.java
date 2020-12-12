package Entities;

import Database.DatabaseConnection;
import Exceptions.PokemonNameException;
import Interfaces.CRUDInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainedPokemon extends Pokemon{
    private int id;
    private String nickname;
    private int level;
    private boolean isArchived;

    public TrainedPokemon(int id, String name, String habitat, float weight, boolean isAggressive, String nickname, int level, boolean isArchived) throws PokemonNameException {
        super(name, habitat, weight, isAggressive);
        this.nickname = nickname;
        this.level = level;
        this.isArchived = isArchived;
        this.id = id;
    }

    public TrainedPokemon(String name, String habitat, float weight, boolean isAggressive, String nickname, int level, boolean isArchived) throws PokemonNameException {
        super(name, habitat, weight, isAggressive);
        this.nickname = nickname;
        this.level = level;
        this.isArchived = isArchived;
    }

    public TrainedPokemon() {
    }

    public String getNickname() {
        return nickname;
    }

    public int getLevel() {
        return level;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public int getId() {
        return id;
    }

    @Override
    public void create() {
        String createQuery = "INSERT INTO trainedpokemon(name, habitat, weight, isAggressive, nickname, level, isArchived)" +
                "VALUES('"+this.getName()+"', '"+this.getHabitat()+"', "+this.getWeight()+", "+this.isAggressive()+
                ", '"+this.nickname+"', "+this.level+", "+this.isArchived+");";
        DatabaseConnection.executeQuery(createQuery);
    }
    public static ArrayList selectTrainerPokemon(int id){
        ArrayList trainedPokemons = new ArrayList();
        try {
            String query = "SELECT * FROM trainer_pokemon where trainer_id = "+id+";";
            ResultSet rs = DatabaseConnection.selectValues(query);
            while (rs.next()) {
                int pokemon_id = rs.getInt("pokemon_id");

                query = "SELECT * FROM trainedpokemon where id = "+pokemon_id+";";
                ResultSet rs_pokemon = DatabaseConnection.selectValues(query);

                while (rs_pokemon.next()) {
                    int id_result = rs_pokemon.getInt("id");
                    String name = rs_pokemon.getString("name");
                    String habitat = rs_pokemon.getString("habitat");
                    float weight = rs_pokemon.getFloat("weight");
                    boolean isAggressive = rs_pokemon.getBoolean("isAggressive");
                    String nickname = rs_pokemon.getString("nickname");
                    int level = rs_pokemon.getInt("level");
                    boolean isArchived = rs_pokemon.getBoolean("isArchived");
                    TrainedPokemon pokemon = new TrainedPokemon(id_result,name,habitat,weight,isAggressive,nickname,
                            level,isArchived);
                    trainedPokemons.add(pokemon);
                }
            }

        } catch (SQLException | PokemonNameException e){
            e.printStackTrace();
        }
        return trainedPokemons;
    }
    public static void createTrainer_Pokemon(int trainer_id, int pokemon_id){
        String createQuery = "INSERT INTO trainer_pokemon(trainer_id, pokemon_id)" +
                "VALUES("+trainer_id+", "+pokemon_id+");";
        DatabaseConnection.executeQuery(createQuery);
    }
    public static TrainedPokemon createAntoher(TrainedPokemon pokemon) {
        String createQuery = "INSERT INTO trainedpokemon(name, habitat, weight, isAggressive, nickname, level, isArchived)" +
                "VALUES('"+pokemon.getName()+"', '"+pokemon.getHabitat()+"', "+pokemon.getWeight()+", "+pokemon.isAggressive()+
                ", '"+pokemon.nickname+"', "+pokemon.level+", "+pokemon.isArchived+");";
        DatabaseConnection.executeQuery(createQuery);
        return readLast();
    }
    public static void createTrainer_PokemonList(Trainer trainer, ArrayList<TrainedPokemon> pokemons){
 ;
        for(TrainedPokemon pokemon : pokemons) {
            TrainedPokemon created_pokemon = TrainedPokemon.createAntoher(pokemon);

            System.out.println(created_pokemon.toString());
            createTrainer_Pokemon(trainer.getId(), created_pokemon.getId());
        }
    }

    public static TrainedPokemon readLast() {
        TrainedPokemon pokemon = null;
        try {
            String query = "SELECT * FROM trainedpokemon where id = (select max(id) from trainedpokemon);";
            ResultSet rs = DatabaseConnection.selectValues(query);
            while (rs.next()) {
                int id_result = rs.getInt("id");
                String name = rs.getString("name");
                String habitat = rs.getString("habitat");
                float weight = rs.getFloat("weight");
                boolean isAggressive = rs.getBoolean("isAggressive");
                String nickname = rs.getString("nickname");
                int level = rs.getInt("level");
                boolean isArchived = rs.getBoolean("isArchived");
                pokemon = new TrainedPokemon(id_result,name,habitat,weight,isAggressive,nickname,
                        level,isArchived);
            }

            return pokemon;

        } catch (SQLException | PokemonNameException e){
            e.printStackTrace();
        }
        return pokemon;
    }

    public void updatePokemon(int id, String name, float weight, String habitat, boolean isAggressive,
    String nickname, int level, boolean isArchived) {
        System.out.println("chegou aqui ao menos");
        String createQuery = "UPDATE trainedpokemon" +
                "    SET name = '"+name+"', weight = "+weight+", habitat = '"+habitat+"', isAggressive = "+isAggressive+", " +
                "    nickname = '"+nickname+"', level = "+level+", isArchived = "+isArchived+
                "    WHERE id = "+id+";";
        DatabaseConnection.executeQuery(createQuery);
        System.out.println(createQuery);
    }

    @Override
    public String toString() {
        return "TrainedPokemon{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", level=" + level +
                ", isArchived=" + isArchived +
                '}';
    }
    @Override
    public ArrayList read() {
        ArrayList<TrainedPokemon> trainedPokemons = new ArrayList();
        try {
            String query = "SELECT * FROM trainedpokemon;";
            ResultSet rs = DatabaseConnection.selectValues(query);
            while (rs.next()) {
                int id_result = rs.getInt("id");
                String name = rs.getString("name");
                String habitat = rs.getString("habitat");
                float weight = rs.getFloat("weight");
                boolean isAggressive = rs.getBoolean("isAggressive");
                String nickname = rs.getString("nickname");
                int level = rs.getInt("level");
                boolean isArchived = rs.getBoolean("isArchived");
                TrainedPokemon pokemon = new TrainedPokemon(id_result,name,habitat,weight,isAggressive,nickname,
                        level,isArchived);
                trainedPokemons.add(pokemon);
            }

            return trainedPokemons;

        } catch (SQLException | PokemonNameException e){
            e.printStackTrace();
        }
        return trainedPokemons;
    }

    public TrainedPokemon selectOne(int id){
        TrainedPokemon pokemon = new TrainedPokemon();
        try {
            String query = "SELECT * FROM trainedpokemon where id="+id+";";
            ResultSet rs = DatabaseConnection.selectValues(query);
            while (rs.next()) {
                int id_result = rs.getInt("id");
                String name = rs.getString("name");
                String habitat = rs.getString("habitat");
                float weight = rs.getFloat("weight");
                boolean isAggressive = rs.getBoolean("isAggressive");
                String nickname = rs.getString("nickname");
                int level = rs.getInt("level");
                boolean isArchived = rs.getBoolean("isArchived");
                pokemon = new TrainedPokemon(id_result,name,habitat,weight,isAggressive,nickname,
                        level,isArchived);
            }
            return pokemon;
        } catch (SQLException | PokemonNameException e){
            e.printStackTrace();
        }
        return pokemon;
    }
    @Override
    public void delete(String name) {
        int id = Integer.parseInt(name);
        System.out.println(id);
        String createQuery = "DELETE FROM trainedpokemon where id = "+id+";";
        DatabaseConnection.executeQuery(createQuery);
    }
}
