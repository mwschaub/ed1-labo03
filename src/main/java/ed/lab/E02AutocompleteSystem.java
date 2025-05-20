package ed.lab;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E02AutocompleteSystem {
    private final Map<String, Integer> sentenceFrequency;
    private final StringBuilder currentInput;

    public E02AutocompleteSystem(String[] sentences, int[] times) {
        sentenceFrequency = new HashMap<>();
        currentInput = new StringBuilder();

        for (int i = 0; i < sentences.length; i++) {
            sentenceFrequency.put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String completedSentence = currentInput.toString();
            sentenceFrequency.put(completedSentence, sentenceFrequency.getOrDefault(completedSentence, 0) + 1);
            currentInput.setLength(0); // Limpiar la entrada actual
            return new ArrayList<>();
        }
        currentInput.append(c);
        String prefix = currentInput.toString();
        List<String> matchingSentences = new ArrayList<>();

        for (String sentence : sentenceFrequency.keySet()) {
            if (sentence.startsWith(prefix)) {
                matchingSentences.add(sentence);
            }
        }

        matchingSentences.sort((s1, s2) -> {
            int freq1 = sentenceFrequency.get(s1);
            int freq2 = sentenceFrequency.get(s2);
            if (freq1 != freq2) {
                return Integer.compare(freq2, freq1); // Frecuencia más alta primero
            }
            return s1.compareTo(s2); // Orden alfabético si hay empate
        });
        List<String> suggestions = new ArrayList<>();
        for (int i = 0; i < Math.min(3, matchingSentences.size()); i++) {
            suggestions.add(matchingSentences.get(i));
        }

        return suggestions;
    }

}

