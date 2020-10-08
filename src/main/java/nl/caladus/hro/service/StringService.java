package nl.caladus.hro.service;

import nl.caladus.hro.repository.WordRepository;
import org.springframework.stereotype.Service;

@Service
public class StringService {

    private static final WordRepository wordRepository = new WordRepository();

    public String reverse(String input) {
        String reversedString = new StringBuilder(input).reverse().toString();
        wordRepository.setMemory(input, reversedString);
        return reversedString;
    }


    public int count(String input) {

        try {
            if (checkMemory(input)) return Integer.parseInt(wordRepository.getMemory().get(input));

            int length = input.split(" ").length;
            wordRepository.setMemory(input, String.valueOf(length));
            return length;

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private boolean checkMemory(String input) {

        if (input == null || input.isEmpty()) {
            throw new RuntimeException();
        }

        return wordRepository.getMemory().containsKey(input);
    }

}
