package lab04;

import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FuncoesData {
    /* Dada uma string no formato "dd/MM/yyy", retorna o objeto Date equivalente.
	 * Levanta uma excecão parseException se a string não estiver no formato especificado. */
	public static Date parseDate(String data) {
        try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(data);
        } catch (ParseException e) {
            return null;
        }
	}
	/* Dado um objeto Date, retorna a String equivalente no formato dd/MM/yyyy */
	public static String dateToString(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String str = dateFormat.format(data);
		return str;
	}
    public static int calcularIdade(Date dataNascimento) {
        int idade;
        Calendar calendarNascimento = Calendar.getInstance();
        Calendar calendarAtual = Calendar.getInstance();
        calendarNascimento.setTime(dataNascimento);
        calendarAtual.setTime(new Date());
        // calcula idade que completa esse ano
        idade = calendarAtual.get(Calendar.YEAR) - calendarNascimento.get(Calendar.YEAR);
        // se ainda nao fez aniversario no ano atual, subtrai 1 da idade
        if (calendarAtual.get(Calendar.DAY_OF_YEAR) < calendarNascimento.get(Calendar.DAY_OF_YEAR))
            idade--;
        return idade;
    }
}
