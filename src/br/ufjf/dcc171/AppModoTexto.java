package br.ufjf.dcc171;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppModoTexto {

    public static void rodaAplicacao() {
        ObjectOutputStream saida = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            saida = new ObjectOutputStream(
                    Files.newOutputStream(Paths.get("lancamentos.db"))
            );
            
            
            Scanner entrada = new Scanner(System.in);
            System.out.println("Digite um lançamento no formato: descrição_curta dd/mm/yyyy NNN,NN");
            while(true){
                Lancamento l = new Lancamento();
                l.setDescricao(entrada.next());
                l.setData(sdf.parse(entrada.next()));
                l.setValor(entrada.nextDouble());
                saida.writeObject(l);
                System.out.println("Deseja inserir outro? (S/n)");
                if(entrada.next().equalsIgnoreCase("n")){
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
}
