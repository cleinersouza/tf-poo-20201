package Exceptions;

public class PokemonNameException extends Exception {
    private String name;
    public PokemonNameException(String name){
        this.name = name;
    }
    public String getMessage(){
        return "O nome não pode conter números ou caracteres especiais: "+ this.name;
    }
}
