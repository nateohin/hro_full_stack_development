package nl.caladus.hro.service;

import nl.caladus.hro.repository.WordRepository;
import org.springframework.stereotype.Service;


@Service
public class StringService {

    private static WordRepository wordRepository = new WordRepository();

    public String reverse(String input) {

        if (checkMemory(input)) return wordRepository.getMemory().get(input);


        StringBuilder stringBuilder = new StringBuilder();
        try {
           for (int i = input.length() - 1; i >= 0; i--) {
               stringBuilder.append(input.charAt(i));
           }
        } catch (Exception e) {
            return "";
        }

        String reversedString = stringBuilder.toString();
        wordRepository.setMemory(input, reversedString);

        return reversedString;
    }


    public int length(String input) {

        if (checkMemory(input)) return Integer.parseInt(wordRepository.getMemory().get(input));

        int length = input.split(" ").length;

        wordRepository.setMemory(input, String.valueOf(length));

        return length;

    }

    private boolean checkMemory(String input) {
        return wordRepository.getMemory().containsKey(input);
    }

}
