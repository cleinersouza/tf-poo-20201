package GraphicInterface;

import Entities.TrainedPokemon;
import Entities.Trainer;
import Interfaces.EntityListJPanelInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TrainedPokemonListJPanel implements EntityListJPanelInterface {
    private JFrame window;

    public TrainedPokemonListJPanel(JFrame window){
        this.window = window;
    }
    public JScrollPane createScrollPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        panel = namesButton(panel);

        JScrollPane jsp = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(700, 70, 145, 200);
        jsp.getVerticalScrollBar().setUnitIncrement(16);

        return jsp;
    }

    public JPanel namesButton(JPanel panel){
        TrainedPokemon trainedPokemonRead = new TrainedPokemon();
        ArrayList<TrainedPokemon> trainedPokemons = trainedPokemonRead.read();

        for(TrainedPokemon trainedPokemon : trainedPokemons){
            panel.add(createButton(trainedPokemon));
        }
        return panel;
    }
    public JButton createButton(Object object){
        TrainedPokemon trainedPokemon= (TrainedPokemon) object;
        JButton newButton = new JButton(trainedPokemon.getName());
        newButton.setForeground(Color.WHITE);
        newButton.setBackground(Color.GRAY);
        ActionListener trainedPokemonName = new TrainedPokemonNameActionListener(trainedPokemon, this.window);
        newButton.addActionListener(trainedPokemonName);

        return newButton;
    }
}
