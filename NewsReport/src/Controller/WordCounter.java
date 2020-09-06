package Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import Model.EnglishLanguage;
import Model.ILanguage;
import Model.ILanguageFactory;
import Model.NewsModel;

public class WordCounter {

	private List<String> listOfWords;

	private ILanguage language;

	public WordCounter(List<String> incomingWords, ILanguage language) {

		this.listOfWords = incomingWords;

		this.language = language;

	}

	public ArrayList<NewsModel> getMostFrequentWords() {

		ArrayList<String> tempArray = new ArrayList<String>();

		for (String word : listOfWords) {

			String[] innerArray = word.split(" ");
			for (String innerWord : innerArray) {
				String w = innerWord.toLowerCase();

				if (w.length() > 2 && !language.getVerbs().contains(w) && !language.getConjunctions().contains(w)
						&& !language.getPrepositions().contains(w) && !language.getPronouns().contains(w)
						&& !language.getCommonWords().contains(w)) {

					tempArray.add(w);
				}
			}
		}

		Map<String, Long> occurrences = tempArray.stream()
				.collect(Collectors.groupingBy(word -> word, Collectors.counting()));
		Map<String, Long> result = sortByValue(occurrences);

		List<String> resultTopics = new ArrayList<String>();
		List<Long> resultOccurrences = new ArrayList<Long>();
		ArrayList<NewsModel> topFive = new ArrayList<>();

		for (Entry<String, Long> entry : result.entrySet()) {
			resultTopics.add(entry.getKey());
			resultOccurrences.add(entry.getValue());

		}

		int lastIndex = resultTopics.size() - 1;

		for (int i = 0; i < 5; i++) {

			String topic = resultTopics.get(lastIndex - i);
			long count = resultOccurrences.get(lastIndex - i);

			NewsModel nm = new NewsModel(topic, count);
			topFive.add(nm);

		}

		return topFive;

	}

	private <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort(Entry.comparingByValue());

		Map<K, V> result = new LinkedHashMap<>();
		for (Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

}
