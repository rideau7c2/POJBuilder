package zaslonad.builderPatern.exceptions;

public class NotReadyStateException extends IllegalStateException {

	private static final long serialVersionUID = -5746372400809717180L;

	public NotReadyStateException(){
		super("Dodaj treść lub podrozdział do ostatnio utworzonego rozdziału!");
	}
}
