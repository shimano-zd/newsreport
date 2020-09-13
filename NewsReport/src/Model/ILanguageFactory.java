package Model;

/**
 * The interface that should be implemented by a specific language factory.
 * @author Sime
 *
 */
public interface ILanguageFactory {

	ILanguage createLanguage(LanguageType type);
}
