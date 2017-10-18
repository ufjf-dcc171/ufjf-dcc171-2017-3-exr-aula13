package br.ufjf.dcc171;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppModoTexto {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void rodaAplicacaoDeEscrita() {
        ObjectOutputStream saida = null;
        try {
            saida = new ObjectOutputStream(
                    Files.newOutputStream(Paths.get("lancamentos.db"))
            );

            Scanner entrada = new Scanner(System.in);
            System.out.println("Digite um lançamento no formato: descrição_curta dd/mm/yyyy NNN,NN");
            while (true) {
                Lancamento l = new Lancamento();
                l.setDescricao(entrada.next());
                l.setData(sdf.parse(entrada.next()));
                l.setValor(entrada.nextDouble());
                saida.writeObject(l);
                System.out.println("Deseja inserir outro? (S/n)");
                if (entrada.next().equalsIgnoreCase("n")) {
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AppModoTexto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                saida.close();
            } catch (Exception ex) {
                Logger.getLogger(AppModoTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void rodaAplicacaoLeitura() {
        ObjectInputStream entrada = null;
        try {
            entrada = new ObjectInputStream(
                    Files.newInputStream(
                            Paths.get("lancamentos.db")
                    )
            );
            while(true){
                Lancamento l = (Lancamento) entrada.readObject();
                System.out.println(String.format("%s\t%s\tR$%.2f\n", l.getDescricao(), sdf.format(l.getData()), l.getValor()));
            }
                      
            
        } catch (IOException ex) {
            Logger.getLogger(AppModoTexto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppModoTexto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                entrada.close();
            } catch (IOException ex) {
                Logger.getLogger(AppModoTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
