package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;

public class TestMain {

    @Test
    public void testIdMenor700() {
        Boolean loadFiles = Main.parseFiles(new File("test-files"));
        ArrayList<Object> paiS = Main.getObjects(TipoEntidade.PAIS);
        if (paiS != null && !paiS.isEmpty()) {
            String resultadoAtual = paiS.get(0).toString();
            String resultadoEsperado = "Afeganistão | 4 | AF | AFG";
            Assertions.assertEquals(resultadoEsperado, resultadoAtual,
                    "Incorreto, devia converter String para ID < 700");
        } else {
            Assertions.fail("Lista de países vazia ou nula.");
        }
    }

    @Test
    public void testIdMaior700() {
        Boolean loadFiles = Main.parseFiles(new File("test-files"));
        ArrayList<Object> paiS = Main.getObjects(TipoEntidade.PAIS);
        if (paiS != null && !paiS.isEmpty()) {
            String resultadoAtual = paiS.get(16).toString();
            String resultadoEsperado = "Burquina Fasso | 854 | BF | BFA | 151";
            Assertions.assertEquals(resultadoEsperado, resultadoAtual,
                    "Incorreto, devia converter String para ID > 700");
        } else {
            Assertions.fail("Lista de países vazia ou nula.");
        }
    }

    @Test
    public void testToStringCidades() {
        Boolean loadFiles = Main.parseFiles(new File("test-files"));
        ArrayList<Object> cida = Main.getObjects(TipoEntidade.CIDADE);
        if (cida != null && !cida.isEmpty()) {
            String resultadoAtual = cida.get(5).toString();
            String resultadoEsperado = "ordino | AD | 05 | 2553 | (42.55,1.5333333)";
            Assertions.assertEquals(resultadoEsperado, resultadoAtual,
                    "Teste converter para String de Cidade incorreto");
        } else {
            Assertions.fail("Lista de cidades vazia ou nula.");
        }
    }
}