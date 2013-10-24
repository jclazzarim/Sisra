package br.unioeste.sisra.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Classe criada para armazenar métodos estaticos de manipulação de datas 
 * 
 * @author Charlinho
 */
public class DataUtils {
    /**
     * Método que converte uma string em data. Obedecendo o formato "dd/MM/yyyy"
     */
    public static Date converterStringParaData(String strData) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return new Date(format.parse(strData).getTime());  
    }
    
    /**
     * Método que converte uma data em String. Obedecendo o formato "dd/MM/yyyy"
     */
    public static String converterDataParaString(Date data) throws Exception {
        SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");  
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        return out.format(in.parse(data.toString()));
    }
    
    public static boolean isValidoStringNoFormatoDeData(String strData){
        try {
            converterStringParaData(strData);
        } catch (Exception ex) {
            return false;
        }
        
        return true;
    }
}
