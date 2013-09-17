package br.ufes.importexcel;

import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Iniciando leitura dos dados....");

        String[] municipios = {"Fundao", "Serra", "Cariacica", "VilaVelha", "Viana", "Guarapari"};

        /*if (1 > 0) {
        return;
        }*/

        for (String sMunicipio : municipios) {

            try {

                JFileChooser fileChooser = new JFileChooser("C:\\fcaa\\dados\\planilhas");

                //fileChooser.showOpenDialog(null);

                //File file = fileChooser.getSelectedFile();

                File file = new File("C:\\fcaa\\dados\\planilhas\\" + sMunicipio + ".xlsx");

                //passa qual WorkBook vai ser usada, de acordo com o caminho
                Workbook wb = null;

                if (file.getName().endsWith("xlsx")) {
                    wb = new XSSFWorkbook(new FileInputStream(file.getCanonicalPath()));
                } else {
                    wb = new HSSFWorkbook(new FileInputStream(file.getCanonicalPath()));
                }

                //criando uma nova sheet
                Sheet sheet = null;

                //passa qual WorkSheet vai ser usada. Nesse caso a primera (0)
                sheet = wb.getSheetAt(0);
                //row = sheet.getRow(0);
                //cell = row.getCell(0);

                //imprime item da posicao A1
                //System.out.print(cell.getStringCellValue() + "\n\n");

                //pronto, a partir da célula pega a string
                //return cell.getStringCellValue();

                //Imprime planilha toda
                int contador = 6000;
                for (Row r : sheet) {
                    //if (1>0)break;
                    if (r.getRowNum() == 0) {
                        continue; // pula cabeçalho
                    }

                    //r.getCell(0).getCellType();Cell
                    //Integer pontoGps = new Integer(r.getCell(0).getStringCellValue());
                    Integer x = new Double(r.getCell(1).getNumericCellValue()).intValue();
                    Integer y = new Double(r.getCell(2).getNumericCellValue()).intValue();
                    String nome = r.getCell(3).getStringCellValue();
                    String atrativo = r.getCell(4).getStringCellValue();
                    String equipamento = r.getCell(5).getStringCellValue();
                    String evento = r.getCell(6).getStringCellValue();
                    String pavimento = r.getCell(10).getStringCellValue();
                    Integer faixas = new Double(r.getCell(11).getNumericCellValue()).intValue();
                    String calcada = r.getCell(12).getStringCellValue();
                    String estacionamento = r.getCell(13).getStringCellValue();
                    String municipio = r.getCell(14).getStringCellValue();
                    String categoria = r.getCell(15).getStringCellValue();
                    String tipo = r.getCell(16).getStringCellValue();


                    //System.out.println("GPS: " + pontoGps);
                /*System.out.println("X: " + x);
                    System.out.println("Y: " + y);
                    System.out.println("Nome: " + nome);
                    System.out.println("Atrativo: " + atrativo);
                    System.out.println("Equipamento: " + equipamento);
                    System.out.println("Evento: " + evento);
                    System.out.println("Pavimento: " + pavimento);
                    System.out.println("Faixas: " + faixas);
                    System.out.println("Calçada: " + calcada);
                    System.out.println("Estacionamento: " + estacionamento);
                    System.out.println("Municipio: " + municipio);
                    System.out.println("Categoria: " + categoria);
                    System.out.println("Tipo: " + tipo);*/

                    String sTipo = null;
                    if ("Sim".equalsIgnoreCase(atrativo)) {
                        sTipo = "Atrativo";
                    } else if ("Sim".equalsIgnoreCase(evento)) {
                        sTipo = "Evento";
                    } else if ("Sim".equalsIgnoreCase(equipamento)) {
                        sTipo = "Equipamento";
                    } else {
                        sTipo = "Outro";
                    }

                    if (nome.contains("'")) {
                        //System.out.println(nome + "\n\n\n\n\n\n");
                        nome = nome.replace("'", "\\'");
                    }

                    System.out.println("INSERT INTO `atrativos` (`X_GPS`, `Y_GPS`, `X`, `Y`, `NOME`, `SUBTIPO`, `TIPO`, `INTERVALO_FOTOS`, `PAVIMENTACAO`, `FAIXAS`, `CALCADA`, `ESTACIONAMENTO`, `MUNICIPIO`, `CATEGORIA`, `DATA_LEITURA`) VALUES "
                            + "(" + x + ", " + y + ", 0, 0, '" + nome + "', NULL, '" + sTipo + "', NULL, '" + pavimento.charAt(0) + "', " + faixas + ", '" + calcada.charAt(0) + "', '" + estacionamento.charAt(0) + "', '" + municipio + "', '" + categoria + "', NULL);");

                    contador++;
                }

                System.out.println("--- Leitura finalizada de " + sMunicipio);

            } catch (Exception e) {
                System.err.println("Erro no processamento do arquivo: " + e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
    }
}
