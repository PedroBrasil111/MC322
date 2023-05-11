package lab04;

import java.util.Scanner;

public class AppMain {




	public static void main(String[] args) {
		for (int i = 0; i < MenuOperacoes.values().length; i++)
			System.out.println(String.valueOf(i) + ". " + MenuOperacoes.values()[i].getOperacao());
	}

}
