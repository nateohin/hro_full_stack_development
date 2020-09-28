package nl.caladus.hro.service;

import nl.caladus.hro.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StringService {

    private WordRepository wordRepository;

    @Autowired
    public StringService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public String reverse(String input) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (checkMemory(input)) return wordRepository.getMemory().get(input);

            for (int i = input.length() - 1; i >= 0; i--) {
               stringBuilder.append(input.charAt(i));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

        String reversedString = stringBuilder.toString();
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
