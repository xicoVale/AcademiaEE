package interfaces;

import javax.ejb.Local;

import entities.Inqueries;

@Local
public interface InqueriesBeanLocal {
	
	public String registerInquerie(Inqueries inquery);
}
