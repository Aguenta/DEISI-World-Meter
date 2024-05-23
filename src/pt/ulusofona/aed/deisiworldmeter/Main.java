package pt.ulusofona.aed.deisiworldmeter;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<String> alfas2 = new ArrayList<>();

    static ArrayList<Integer> idPaises = new ArrayList<>();

    static ArrayList<Cidade> cidades = new ArrayList<>();

    static ArrayList<Pais> paises = new ArrayList<>();

    static ArrayList<Populacao> populacao = new ArrayList<>();

    static int[] inputs1 = new int[3];

    static int[] inputs2 = new int[3];

    static int[] inputs3 = new int[3];

    public static ArrayList getObjects(TipoEntidade tipo) {
        if (tipo == TipoEntidade.PAIS) {
            return paises;
        } else if (tipo == TipoEntidade.CIDADE) {
            return cidades;
        } else if (tipo == TipoEntidade.INPUT_INVALIDO) {
            ArrayList<String> inputsInvalidos = new ArrayList<>();
            inputsInvalidos.add("paises.csv | " + inputs1[0] + " | " + inputs1[1] + " | " + inputs1[2]);
            inputsInvalidos.add("cidades.csv | " + inputs2[0] + " | " + inputs2[1] + " | " + inputs2[2]);
            inputsInvalidos.add("populacao.csv | " + inputs3[0] + " | " + inputs3[1] + " | " + inputs3[2]);
            return inputsInvalidos;
        }
        return null;
    }

    public static boolean parsePaises(File Paises){

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(Paises));
        } catch (FileNotFoundException e) {
            return false;
        }

        boolean primeiraLinha = true;

        int linhasIncorretas = 0;
        int linhasCorretas = 0;
        int primeiraLinhaIncorreta = -1;
        int count = 0;
        String linha = null;

        do {
            try {
                linha = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(linha != null) {
                count++;

                if (primeiraLinha) {
                    primeiraLinha = false;
                } else {
                    String[] partes = linha.split(",");

                    if (partes.length == 4) {
                        try {
                            int id = Integer.parseInt(partes[0]);
                            String alfa2 = partes[1];

                            if(!idPaises.contains(id)){

                                String alfa3 = partes[2];
                                String nome = partes[3];
                                Pais pais = new Pais(id, alfa2, alfa3, nome);

                                alfas2.add(alfa2);
                                idPaises.add(id);
                                paises.add(pais);
                                linhasCorretas++;

                            } else {
                                linhasIncorretas++;
                                if (primeiraLinhaIncorreta == -1) {
                                    primeiraLinhaIncorreta = count;
                                }
                            }

                        } catch (NumberFormatException e) {
                            linhasIncorretas++;
                            if (primeiraLinhaIncorreta == -1) {
                                primeiraLinhaIncorreta = count;
                            }
                        }
                    } else {
                        linhasIncorretas++;
                        if (primeiraLinhaIncorreta == -1) {
                            primeiraLinhaIncorreta = count;
                        }
                    }
                }
                inputs1[0] = linhasCorretas;
                inputs1[1] = linhasIncorretas;
                inputs1[2] = primeiraLinhaIncorreta;
            }
        } while(linha != null);

        return true;
    }

    public static boolean parseCidades(File Cidades) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(Cidades));
        } catch (FileNotFoundException e) {
            return false;
        }

        boolean primeiraLinha = true;
        int linhasIncorretas = 0;
        int linhasCorretas = 0;
        int primeiraLinhaIncorreta = -1;
        int count = 0;
        String linha = null;

        do {
            try {
                linha = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(linha != null) {
                count++;

                if (primeiraLinha) {
                    primeiraLinha = false;
                } else {
                    String[] partes = linha.split(",");

                    if (partes.length == 6) {
                        try {
                            String alfa2 = partes[0];
                            if (alfas2.contains(alfa2)){
                                String cidade = partes[1];
                                String regiao = partes[2];
                                double populacao = Double.parseDouble(partes[3]);
                                double latitude = Double.parseDouble(partes[4]);
                                double longitude = Double.parseDouble(partes[5]);
                                Cidade cidades1 = new Cidade(alfa2, cidade, regiao, (int) populacao, latitude, longitude);

                                cidades.add(cidades1);
                                linhasCorretas++;

                            } else {
                                linhasIncorretas++;
                                if (primeiraLinhaIncorreta == -1) {
                                    primeiraLinhaIncorreta = count;
                                }
                            }
                        } catch (NumberFormatException e) {
                            linhasIncorretas++;
                            if (primeiraLinhaIncorreta == -1) {
                                primeiraLinhaIncorreta = count;
                            }
                        }
                    } else {
                        linhasIncorretas++;
                        if (primeiraLinhaIncorreta == -1) {
                            primeiraLinhaIncorreta = count;
                        }
                    }
                }
                inputs2[0] = linhasCorretas;
                inputs2[1] = linhasIncorretas;
                inputs2[2] = primeiraLinhaIncorreta;
            }
        } while(linha != null);

        return true;
    }

    public static boolean parsePopulacao(File populacaoFile) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(populacaoFile));
        } catch (FileNotFoundException e) {
            return false;
        }

        boolean primeiraLinha = true;
        int linhasIncorretas = 0;
        int linhasCorretas = 0;
        int primeiraLinhaIncorreta = -1;
        int count = 0;
        String linha = null;

        do {
            try {
                linha = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(linha != null) {
                count++;

                if (primeiraLinha) {
                    primeiraLinha = false;
                } else {
                    String[] partes = linha.split(",");

                    if (partes.length == 5) {
                        try {
                            int id = Integer.parseInt(partes[0]);

                            if (idPaises.contains(id)) {

                                int ano = Integer.parseInt(partes[1]);
                                int populacaoMasculina = Integer.parseInt(partes[2]);
                                int populacaoFeminina = Integer.parseInt(partes[3]);
                                double densidade = Double.parseDouble(partes[4]);
                                Populacao populacaoObj = new Populacao(id, ano, populacaoMasculina, populacaoFeminina, densidade);

                                populacao.add(populacaoObj);
                                linhasCorretas++;

                                for (Pais pais : paises) {
                                    if (pais.id == id) {
                                        pais.contagemIds++;
                                    }
                                }

                            } else {
                                linhasIncorretas++;
                                if (primeiraLinhaIncorreta == -1) {
                                    primeiraLinhaIncorreta = count;
                                }
                            }

                        } catch (NumberFormatException e) {
                            linhasIncorretas++;
                            if (primeiraLinhaIncorreta == -1) {
                                primeiraLinhaIncorreta = count;
                            }
                        }
                    } else {
                        linhasIncorretas++;
                        if (primeiraLinhaIncorreta == -1) {
                            primeiraLinhaIncorreta = count;
                        }
                    }
                }
            }
        } while(linha != null);

        inputs3[0] = linhasCorretas;
        inputs3[1] = linhasIncorretas;
        inputs3[2] = primeiraLinhaIncorreta;

        return true;
    }

    public static boolean parseFiles(File folder) {
        alfas2.clear();
        idPaises.clear();
        cidades.clear();
        paises.clear();
        populacao.clear();

        return parsePaises(new File(folder, "paises.csv"))
                && parseCidades(new File(folder, "cidades.csv"))
                && parsePopulacao(new File(folder, "populacao.csv"));
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        boolean parseOK = parseFiles(new File("Pasta"));
        if (!parseOK) {
            System.out.println("Erro na leitura");
            return;
        }

        long end = System.currentTimeMillis();

        System.out.println(getObjects(TipoEntidade.PAIS));
        System.out.println(getObjects(TipoEntidade.INPUT_INVALIDO));



        // paises.csv | 193 | 3 | 103, cidades.csv | 47525 | 3607 | 12, populacao.csv | 29138 | 6639 | 1361

        System.out.println("tempo " + (end - start) + "ms");

    }
}