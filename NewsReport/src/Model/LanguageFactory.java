package Model;

/**
 * The class that provides specific languages.
 * @author Sime
 *
 */
public class LanguageFactory implements ILanguageFactory {

	/**
	 * Creates a new language from a given type.
	 */
	@Override
	public ILanguage createLanguage(LanguageType type) {

		switch (type) {
		case Croatian:
			return new CroatianLanguage();
		case English:
			return new EnglishLanguage();
		default:
			return new EnglishLanguage();
		}
	}

}
