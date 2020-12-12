package GraphicInterface;

import Entities.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PokemonListJPanel{
    private JFrame window;

    public PokemonListJPanel(JFrame window){
        this.window = window;
    }
    public JScrollPane createScrollPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        panel = pokemonNamesButton(panel);

        JScrollPane jsp = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(55, 70, 150, 200);
        jsp.getVerticalScrollBar().setUnitIncrement(16);

        return jsp;
    }

    public JPanel pokemonNamesButton(JPanel panel){
        Pokemon pokemonRead = new Pokemon();
        ArrayList<Pokemon> pokemons = pokemonRead.read();

        for(Pokemon pokemon : pokemons){
            panel.add(createButton(pokemon));
        }
        return panel;
    }
    public JButton createButton(Pokemon pokemon){
        JButton newButton = new JButton(pokemon.getName());
        newButton.setForeground(Color.WHITE);
        newButton.setBackground(Color.GRAY);
        ActionListener pokemonName = new PokemonNameActionListener(pokemon, this.window);
        newButton.addActionListener(pokemonName);

        return newButton;
    }
}
