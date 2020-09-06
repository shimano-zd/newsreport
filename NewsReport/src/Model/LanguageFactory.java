package Model;

public class LanguageFactory implements ILanguageFactory{

	@Override
	public ILanguage createLanguage(LanguageType type) {
		
		switch(type) {
			case Croatian: 	return new CroatianLanguage();
			case English: return new EnglishLanguage();
			default: return new EnglishLanguage();
		}
	}

}
