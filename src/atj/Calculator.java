package atj;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;

@Named
@SessionScoped
public class Calculator implements Serializable {
	private static final long serialVersionUID = -3512401082776903678L;

	@PostConstruct
	public void initialize() {
		System.out.println(Calculator.class.getSimpleName() + " was constructed");
	}

	private String arg0 = "0";
	private String arg1 = "0";
	private String result = "0";
	private String action = "";
	private String hide = "false";
	private String pageBackgroundColor = "silver";
	private String resultColor = "#ccffff";
	private String resultFontColor = "black";
	private String btnColor = "";
	private String font = "";

	// gettery i settery
	public String getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(String btnColor) {
		this.btnColor = btnColor;
	}
	
	public String getResultFontColor() {
		return resultFontColor;
	}

	public void setResultFontColor(String resultFontColor) {
		this.resultFontColor = resultFontColor;
	}
	
	public String getResultColor() {
		return resultColor;
	}

	public void setResultColor(String resultColor) {
		this.resultColor = resultColor;
	}

	public String getPageBackgroundColor() {
		return pageBackgroundColor;
	}

	public void setPageBackgroundColor(String pageBackgroundColor) {
		this.pageBackgroundColor = pageBackgroundColor;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getArg0() {
		return arg0;
	}

	public void setArg0(String a) {
		if (this.arg0.length() == 1 && this.arg0.substring(0, 1).equals("0") && !a.equals(".")) {
			this.arg0 = "";
		} // usuwanie pierwszego zera ze Stringa
		if (a.equals(".") && this.arg0.contains(".")) {
			a = "";
		} // zabezpieczenie przed dublowaniem kropki
		if (action.equals("equals")) {
			this.arg0 = "";
			action = "";
		}
		this.arg0 += a;
		result = this.arg0;
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg1(String b) {
		this.arg1 = b;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	// cancel / kasowanie / zerowanie kalkulatora
	public void cancel() {
		arg0 = "0";
		arg1 = "0";
		result = "0";
		action = "";
		hide = "false";
	}

	// dodawanie
	public void dodaj() {
		if (action.equals("equals") || !action.equals("")) {
			arg0 = result;
		}
		action = "dodaj";
		arg1 = arg0;
		result = arg0;
		arg0 = "0";
	}

	// odejmowanie
	public void odejmij() {
		if (action.equals("equals") || !action.equals("")) {
			arg0 = result;
		}
		action = "odejmij";
		arg1 = arg0;
		result = arg0;
		arg0 = "0";
	}

	// mnozenie
	public void pomnoz() {
		if (action.equals("equals") || !action.equals("")) {
			arg0 = result;
		}
		action = "pomnoz";
		arg1 = arg0;
		result = arg0;
		arg0 = "0";
	}

	// dzielenie
	public void podziel() {
		if (action.equals("equals") || !action.equals("")) {
			arg0 = result;
		}
		action = "podziel";
		arg1 = arg0;
		result = arg0;
		arg0 = "0";
	}

	// Pierwiastkowanie
	public void sqrt() {
		if (action.equals("equals") || !action.equals("")) {
			arg0 = result;
		}
		action = "sqrt";
		equals();
	}

	// Zmiana znaku
	public void zmienZnak() {
		if (action.equals("equals")) {
			arg0 = result;
		}
		if (!action.equals("")) {
			float s = Float.parseFloat(arg0) * (-1);
			arg0 = "" + s;
			result = arg0;
		} else {
			action = "zmienZnak";
			equals();
		}
	}

	// Procent
	public void procent() {
		if (action.equals("equals") || !action.equals("")) {
			arg0 = result;
		}
		action = "procent";
		equals();
	}

	// Naciskanie rowna sie / obliczenia
	public void equals() {
		if (action.equals("dodaj")) {
			float dodaj = Float.parseFloat(arg1) + Float.parseFloat(arg0);
			result = "" + dodaj;
		} else if (action.equals("odejmij")) {
			float odejmij = Float.parseFloat(arg1) - Float.parseFloat(arg0);
			result = "" + odejmij;
		} else if (action.equals("pomnoz")) {
			float pomnoz = Float.parseFloat(arg1) * Float.parseFloat(arg0);
			result = "" + pomnoz;
		} else if (action.equals("podziel")) {
			if (arg0.equals("0")) {
				error();
			} else {
				float podziel = Float.parseFloat(arg1) / Float.parseFloat(arg0);
				result = "" + podziel;
			}
		} else if (action.equals("sqrt")) {
			double arg0 = Double.parseDouble(this.arg0);
			if (arg0 < 0) {
				error();
			} else {
				result = "" + Math.sqrt(arg0);
			}
		} else if (action.equals("procent")) {
			double arg0 = Double.parseDouble(this.arg0);
			if (arg0 < 0) {
				result = "" + (arg0) * (0.01);
			} else {
				result = "" + (arg0) * (0.01);
			}
		} else if (action.equals("zmienZnak")) {
			float s = Float.parseFloat(arg0) * (-1);
			result = "" + s;
		}
		action = "equals";
	}

	// Wyszarzenie/blokowanie przyciskow
	public String getHide() {
		return hide;
	}

	public void setHide(String hide) {
		this.hide = hide;
	}

	// Blad - blokowanie/wyszarzanie przyciskow i wyswietlanie
	// napisu ERR na wyswietlaczu kalkulatora
	private void error() {
		hide = "true";
		result = "ERR";
	}
	
	// Przelaczanie stron - ustawienia oraz strona glowna
	public String calcSettings() {
		return "calcSettings";
	}
	
	public String index() {
		return "index";
	}

}
