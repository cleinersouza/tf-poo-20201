package GraphicInterface;

import Entities.TrainedPokemon;
import Entities.Trainer;

import javax.swing.*;
import java.util.ArrayList;

public class PokemonTrainerReference extends JFrame {
    private Trainer trainer;
    public PokemonTrainerReference(Trainer trainer) {
        this.trainer = trainer;

        JFrame popUp = new JFrame();
        popUp.setTitle("Visualizar Pokemons treinados");
        popUp.setBounds(430, 200, 450, 310);
        popUp.getContentPane().setLayout(null);
        int x=6,y=-31,width=300, height=50;
        ArrayList<TrainedPokemon> trainedPokemons = TrainedPokemon.selectTrainerPokemon(trainer.getId());
        for(TrainedPokemon pokemon : trainedPokemons){
            y+=32;
            JLabel id = createJLabel("Id: "+ pokemon.getId(), x,y);
            JLabel name = createJLabel("Nome: "+ pokemon.getName(), x+200,y);
            y+=22;
            JLabel nickname = createJLabel("Apelido: "+ pokemon.getNickname(), x,y);
            JLabel level = createJLabel("Nível: "+ pokemon.getLevel(), x+200,y);
            popUp.add(id);
            popUp.add(name);
            popUp.add(nickname);
            popUp.add(level);
        }

        JButton create = createButton();
        popUp.add(create);

        popUp.setVisible(true);
    }
    public JButton createButton() {
        JButton newButton = new JButton("Adicionar");
        newButton.setBounds(300, 130, 100, 30);

        newButton.addActionListener(ActionEvent -> {
            addTrainerWindow();
        });
        return newButton;
    }
    public JLabel createJLabel(String nome, int x, int y) {
        JLabel label = new JLabel(nome);
        label.setBounds(x, y, 300,50);
        return label;
    }
    public void addTrainerWindow(){
        JFrame popUp = new JFrame();
        popUp.setTitle("Adicionar Pokemon");
        popUp.setBounds(320, 210, 600, 250);
        popUp.getContentPane().setLayout(null);

        JLabel id = createLabel("Id: ",6, 39);
        JTextField idInput = createInput(60,48);
        popUp.getContentPane().add(id);
        popUp.getContentPane().add(idInput);


        JButton submit = submitButton(idInput);
        popUp.getContentPane().add(submit);
        popUp.setVisible(true);
    }
    public JLabel createLabel(String title, int x, int y) {
        JLabel label = new JLabel(title);
        label.setBounds(x, y, 100,50);
        return label;
    }
    public JTextField createInput(int x, int y) {
        JTextField input = new JTextField();
        input.setBounds(x,y,126,26);
        return input;
    }
    public JButton submitButton(JTextField idInput){
        JButton submit = new JButton("Criar");
        submit.setBounds(316, 120, 160,30);

        submit.addActionListener(actionEvent -> {
            int id = Integer.parseInt(idInput.getText());
            TrainedPokemon.createTrainer_Pokemon(this.trainer.getId(), id);

        });
        return submit;
    }
}
