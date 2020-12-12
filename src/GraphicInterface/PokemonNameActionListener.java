package GraphicInterface;

import Entities.Pokemon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PokemonNameActionListener implements ActionListener {
    private Pokemon pokemon;
    private JFrame window;

    public PokemonNameActionListener(Pokemon pokemon, JFrame window) {
        this.window = window;
        this.pokemon = pokemon;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        PokemonDetailsWindow details = new PokemonDetailsWindow(this.pokemon, window);
    }
}
